package com.example.domain;

import java.util.Scanner;

public class ATM {

	//private BankDB bank;
	private int nominal;
	private Scanner scan;		

	/**
	 * Construct object interface
	 * 
	 * @param scan : scanner input
	 */
	public ATM(Scanner aScan) {
		scan = aScan;
	}

	public void printOutput(String output) {
		System.out.println(output);
	}
	
	public int custInput()
	{
		int input = scan.nextInt();
		return input;
	}	
	
	public String custInputStr()
	{
		String input = scan.nextLine();
		return input;
	}

}
