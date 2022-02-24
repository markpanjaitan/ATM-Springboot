package com.example.demo.service;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.ATM;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class ATMService {
	
	ATM atm;
	
	@Autowired
	CustomerRepository custRepository;	

	/**
	 * Initial process
	 * 
	 * @param ATM
	 * @return ATM
	 */
	public void process(ATM a) {
//		Scanner scan = new Scanner(System.in);
//		String input = "";
//		// Initial
//		ui.printOutput("Halo! Ketik `login [nama]` untuk login.");
//		// flag jika login completed
//		boolean persist = true;
//		// loop untuk memastikan inputan sesuai
//		while (persist) {
//			input += scan.nextLine();
//			input = input.replaceAll("\\*", "");
//
//			String[] arrOfStr = input.split("\\s+");
//			Boolean isLogin = arrOfStr[0].toLowerCase().equals("login");
//			Integer arrSize = arrOfStr.length;
//			if((arrSize != 2) || !isLogin) {
//				input = "";
//				ui.printOutput("Mohon cek kembali perintah yang diinput.");
//			}else if(isLogin) {
//				if(a.checkUser(arrOfStr[1])) {
//					persist = false;
//					input = "";					
//					a.custProc(null);
//				}else {
//					//Create new customer
//					persist = false;
//					input = "";						
//					Customer newCust = new Customer(arrOfStr[1], 0, null,null);
//					printOutput("Nasabah " + arrOfStr[1] + " telah ditambahkan.");
//					input = "";					
//					//a.custProc(newCust);
//				}
//			}
//		}
	}	

	/**
	 * Checks passed user account on BankDB
	 * 
	 * @param account
	 * @return boolean : if matches
	 */
	public boolean checkUser(String namaCustomer) {
		//return bank.findAccount(namaCustomer);
		return false; //masi ngaco
	}
	
	/**
	 * Customer processor
	 */
	public void custProc(Customer cust) {
//		// flag input ulang
//		boolean lanjut = true;
//
//		Customer c = new Customer();
//		if (cust != null) {
//			c = cust;
//		}else {
//			
//		}
//		interf.printOutput("Hello, " + c.getNama() + "!");
//		interf.printOutput("Your balance is $" + c.getBalance());
//
//		while (lanjut) {
//			lanjut = false;
//			//interf.printOutput("\nKetik `deposit [nominal]` untuk Deposit" + "\n`withdraw [nominal]` untuk Withdraw"
//			//		+ "\n`transfer [nama] [nominal]` untuk Withdraw" + "\n`logout` untuk Exit");
//			String input = interf.custInputStr();
//
//			try {
//				String[] arrayStr = input.split("\\s+");
//				String strCmd = arrayStr[0].toLowerCase();
//				Integer nominal = Integer.parseInt(arrayStr[1]);
//
//				int arrSize = arrayStr.length;
//				Boolean isValidCommand = false;
//				isValidCommand = strCmd.equals("deposit") || strCmd.equals("withdraw") || strCmd.equals("transfer")
//						|| strCmd.equals("logout");
//
//				if ((arrSize != 2) || !isValidCommand) {
//					interf.printOutput("Invalid Command.."
//							+ "\n\nKetik `deposit [nominal]` untuk Deposit\" + \"\\n`withdraw [nominal]` untuk Withdraw"
//							+ "\n`transfer [nama] [nominal]` untuk Withdraw\" + \"\\n`logout` untuk Exit");
//					input = interf.custInputStr();
//					lanjut = true;
//				} else {
//					switch (strCmd) {
//					// withdraw
//					case "withdraw":
//						transact(c, 1, nominal);
//						break;
//					// deposit
//					case "deposit":
//						transact(c, 2, nominal);
//						break;
//					// transfer
//					// case "transer":
//					// transfer(c);
//					// break;
//					// exit
//					case "logout":
//						interf.printOutput("Goodbye, " + c.getNama() + "!");
//						break;
//					default:
//						interf.printOutput("Invalid Command.."
//								+ "\n\nKetik `deposit [nominal]` untuk Deposit\" + \"\\n`withdraw [nominal]` untuk Withdraw"
//								+ "\n`transfer [nama] [nominal]` untuk Withdraw\" + \"\\n`logout` untuk Exit");
//						input = interf.custInputStr();
//						lanjut = true;
//						break;
//					}
//				}				
//			}catch(Exception e){
//				input = "";
//				interf.printOutput("Error : " + e.toString()
//						+ "\n\nKetik `deposit [nominal]` untuk Deposit\" + \"\n`withdraw [nominal]` untuk Withdraw"
//						+ "\n`transfer [nama] [nominal]` untuk Withdraw\" + \"\n`logout` untuk Exit");
//				//input = interf.custInputStr();
//				lanjut = true;				
//			}
//
//		}
	}	
	
	/**
	 * Processor for a transaction phase of ATM
	 * 
	 * @param c    customer account
	 * @param mode indicates dep or withd
	 */
	private void transact(Customer c, int mode, Integer nominal) {
		boolean persist = true;

//		// mode 1 = withdraw, 2 = deposit
//		if(mode == 1) {
//			if (c.getBalance() < nominal) {
//				interf.printOutput("Not enough balance.");
//				nominal = interf.custInput();
//			}
//		}else {
//			c.updateBalance(mode, nominal);
//		}
//		
//		interf.printOutput("\nYour balance is $" + c.getBalance());
	}

	public Customer findByName(String nama) throws SQLException {
		// TODO Auto-generated method stub
		return custRepository.findByNama(nama);
	}		
}
