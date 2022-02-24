package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Component
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;

	private final int withdraw = 1;
	private final int deposit = 2;	
	
	/**
	Updates balance berdasarkan mode (withdraw/deposit) & nominal
	@param mode : fungsi yg diinginkan (1 for withdraw, 2 for deposit)
	@param amount : jumlah yg ditransfer
	*/	
	@Transactional(rollbackOn = Exception.class)
	public String updateBalance(Customer c, int mode, int nominal) {
		Integer balance = c.getBalance();
		Integer hutangKe = c.getOwed_to_id();
		Integer nominalHutang = c.getOwed_to_nominal();
		Integer autoPotong = 0;
		Integer sisaHutang = null;
		String reply="", str1="", str2="", str3 = "";
		
		try {
			// 1 = withdraw
			if (mode == withdraw)
				balance = balance - nominal;
			else
			// 2 = deposit
				balance = balance + nominal;
			c.setBalance(balance);
			
			// jika punya hutang maka otomatis terpotong (bayar hutang)
			if(hutangKe != null) {
				if(c.getBalance() > 0) {
					if((c.getBalance() - nominalHutang) <= 0) {
						autoPotong = nominalHutang - c.getBalance();
						sisaHutang = Math.abs(c.getBalance() - nominalHutang);
						
						c.setBalance(0);
						c.setOwed_to_nominal(sisaHutang);
						//custRepo.save(c);
					}else {
						autoPotong = nominalHutang;
						sisaHutang = 0;	
						
						c.setBalance(c.getBalance() - nominalHutang);
						c.setOwed_to_id(null);
						c.setOwed_to_nominal(null);
						//custRepo.save(c);					
					}			
					
					Customer c2 = custRepo.findById2(hutangKe);
					c2.setBalance(c2.getBalance() + nominalHutang);
					c2.setKeterangan("Owed $" + autoPotong + " from " + c.getNama());
					custRepo.save(c2);
					
					//Transferred $30 to Alice
					str3 = "\nTransferred $" + autoPotong + " to " + c2.getNama();					
				}

			}
			
			//atm.printOutput("\nHello, " + c.getNama() + "!");
			//atm.printOutput("Your balance is now $" + c.getBalance());	
			str1 = "\nHello, " + c.getNama() + "!";
			str2 = "\nYour balance is $" + c.getBalance();
			
			if(str3.length() > 1) {
				reply = str3 + str1 + str2;
			}else {
				reply = str1 + str2;
			}
			custRepo.save(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	@Transactional
	public String transfer(Customer pengirim, Customer penerima, Integer nominal) {
		
		String reply = "";
		Integer saldoPengirim = pengirim.getBalance();		
		Integer saldoPenerima = penerima.getBalance();
		
		String str1 = "", str2 = "", str3 = "", str4 = "";
		Integer hutang = null;
		
		try {
			str1 = "\nTransferred $" + nominal + " to " + penerima.getNama();
			str2 = "\nYour balance is $" + (pengirim.getBalance() - nominal);
			if((saldoPengirim - nominal) <= 0) {
				hutang = Math.abs(saldoPengirim - nominal);
				str1 = "\nTransferred $" + saldoPengirim + " to " + penerima.getNama();
				str2 = "\nYour balance is $0";				
				str3 = "\nOwed $" + hutang + " to " + penerima.getNama();
				str4 = "\nOwed $" + hutang + " from " + pengirim.getNama();
				
				pengirim.setOwed_to_id(penerima.getId());
				pengirim.setOwed_to_nominal(nominal);
				custRepo.save(pengirim);
				
				penerima.setBalance(penerima.getBalance() + (nominal - saldoPengirim));
				penerima.setKeterangan(str4);
				custRepo.save(pengirim);
			}else {
				hutang = 0;
				str1 = "\nTransferred $" + nominal + " to " + penerima.getNama();
				str2 = "\nYour balance is $" + (pengirim.getBalance() - nominal);					
			
				pengirim.setBalance(pengirim.getBalance() - nominal);
				pengirim.setOwed_to_id(null);
				pengirim.setOwed_to_nominal(null);
				custRepo.save(pengirim);
				
				penerima.setBalance(penerima.getBalance() + (nominal));
				penerima.setKeterangan(str4);
				custRepo.save(penerima);				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		reply = str1 + str2 + str3;
		return reply;
	}	
	
	@Transactional
	public String cekHutangPiutang(Customer c) {
		
		String reply = "";
		try {
			if(c.getOwed_to_id() != null){
				Customer ygDihutangi = custRepo.findById2(c.getOwed_to_id());
				reply = "Owed $" + c.getOwed_to_nominal() + " to " + ygDihutangi.getNama();
			}
			if(c.getKeterangan() != null) {
				reply = c.getKeterangan();
				c.setKeterangan(null);
				custRepo.save(c);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return reply;
	}
	
}
