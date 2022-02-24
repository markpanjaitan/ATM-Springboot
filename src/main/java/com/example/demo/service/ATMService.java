package com.example.demo.service;

import java.io.Serializable;
import java.lang.StackWalker.Option;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.ATM;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class ATMService {
	
	@Autowired
	ATM atm;
	
	@Autowired
	CustomerRepository custRepository;	
	
	@Autowired
	CustomerService custService;
	
	EntityManager enmanager;

	/**
	 * Initial process
	 * 
	 * @param ATM
	 * @return ATM
	 */
	public void process(ATM a) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		// Initial
		a.printOutput("\nHalo! Ketik `login [nama]` untuk login.");
		
		// flag
		boolean persist = true;
		// loop untuk memastikan inputan sesuai
		while (persist) {
			input += scan.nextLine();
			input = input.replaceAll("\\*", "");

			String[] arrOfStr = input.split("\\s+");
			Boolean isLogin = arrOfStr[0].toLowerCase().equals("login");
			Integer arrSize = arrOfStr.length;
			if((arrSize != 2) || !isLogin) {
				input = "";
				a.printOutput("Mohon cek kembali perintah yang diinput.");
			}else if(isLogin) {
				try {
					Customer cust = this.findByName(arrOfStr[1]);
					if(cust == null) {
						//Create new customer
						persist = false;
						input = "";						
						Customer newCust = new Customer();
						newCust.setNama(arrOfStr[1]);
						newCust.setBalance(0);
						newCust = custRepository.save(newCust);
						a.printOutput(arrOfStr[1].toString() + " telah ditambahkan.");
						input = "";					
						custProc(newCust, a);
					}else {
						persist = false;
						input = "";
						custProc(cust, a);	
					}					
				}catch(Exception e) {
					atm.printOutput(e.toString());
					input = "";
					persist = false;
				}
			}
		}
	}	

	/**
	 * Checks passed user account on BankDB
	 * 
	 * @param account
	 * @return boolean : if matches
	 */
	public boolean checkUser(String namaCustomer) {
		try {
			if(findByName(namaCustomer) != null) {
				return true;
			}else {
				return false; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	/**
	 * Customer processor
	 */
	public void custProc(Customer cust, ATM atm){	
		Scanner scan = new Scanner(System.in);		
		// flag
		boolean lanjut = true;

		atm.printOutput("Hello, " + cust.getNama() + "!");
		atm.printOutput("Your balance is $" + cust.getBalance());
		String cekHutangPiutang = custService.cekHutangPiutang(cust);
		if(cekHutangPiutang.length() > 1) {
			atm.printOutput(cekHutangPiutang);
		}

		while (lanjut) {
			String input = "";			
			atm.printOutput("\nCommands : `deposit [nominal]`,`withdraw [nominal]`,`transfer [nama] [nominal]`,`logout`");
			input = atm.custInputStr();

			try {
				String[] arrayStr = input.split("\\s+");
				int arrSize = arrayStr.length;
				String strCmd = "";
				Integer nominal = null;
				Boolean isValidCommand = false;				
				if (arrSize == 2){
					strCmd = arrayStr[0].toLowerCase();
					nominal = Integer.parseInt(arrayStr[1]);

					isValidCommand = strCmd.equals("deposit") || strCmd.equals("withdraw") || strCmd.equals("transfer")
							|| strCmd.equals("logout");
				}else if(arrSize == 1){
					strCmd = arrayStr[0].toLowerCase();
					if(strCmd.equals("logout")) {
						isValidCommand = true;					
					}
				}else {
					atm.printOutput("Invalid Command.."
							+ "\nCommands : `deposit [nominal]`,`withdraw [nominal]`,`transfer [nama] [nominal]`,`logout`");
					input = atm.custInputStr();
					lanjut = true;					
				}

				if(isValidCommand) {
					switch (strCmd) {
					// withdraw
					case "withdraw":
						transact(cust, 1, nominal);
						input = "";
						break;
					// deposit
					case "deposit":
						transact(cust, 2, nominal);
						input = "";
						break;
					// transfer
					// case "transer":
					// transfer(c);
					// break;
					// exit
					case "logout":
						lanjut = false;
						input = "";
						atm.printOutput("Goodbye, " + cust.getNama() + "!");
						break;
					default:
						atm.printOutput("Invalid Command.."
								+ "\nCommands : `deposit [nominal]`,`withdraw [nominal]`,`transfer [nama] [nominal]`,`logout`");
						input = atm.custInputStr();
						lanjut = true;
						break;
					}					
				}else {
					atm.printOutput("Invalid Command.."
							+ "\nCommands : `deposit [nominal]`,`withdraw [nominal]`,`transfer [nama] [nominal]`,`logout`");
					input = atm.custInputStr();
					lanjut = true;					
				}			
			}catch(Exception e){
				input = "";
				atm.printOutput("Error : " + e.toString()
						+ "\nCommands : `deposit [nominal]`,`withdraw [nominal]`,`transfer [nama] [nominal]`,`logout`");
				//input = interf.custInputStr();
				lanjut = true;			
			}

		}
	}	
	
	/**
	 * Processor for a transaction phase of ATM
	 * 
	 * @param c    customer account
	 * @param mode indicates dep or withd
	 */
	private void transact(Customer c, int mode, Integer nominal) {
		boolean persist = true;

		// mode 1 = withdraw, 2 = deposit
		if(mode == 1) {
			if (c.getBalance() < nominal) {
				atm.printOutput("Not enough balance.");
			}else {
				custService.updateBalance(c, mode, nominal);
			}
		}else {
			custService.updateBalance(c, mode, nominal);
		}
		atm.printOutput("\nHello, " + c.getNama() + "!");
		atm.printOutput("Your balance is now $" + c.getBalance());
	}

	public Customer findByName(String nama) throws SQLException {
		// TODO Auto-generated method stub
		return custRepository.findByNama(nama);
	}		
	
	public boolean exists(Customer cust, String nama) {
		   try {
		      return enmanager.getReference(Entity.class, nama) != null;
		   } catch (Exception e) {
		      return false;
		   }
	}
	
	public String transfer(Customer pengirim, Customer penerima, int amount, int type) {
		return custService.transfer(pengirim, penerima, type, null);
	}	
}
