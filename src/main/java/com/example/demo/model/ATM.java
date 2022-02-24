package com.example.demo.model;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ATM {

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

	public ATM() {

	}
}
