package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.ATM;
import com.example.domain.ATMInterface;
import com.example.domain.Customer;

import service.ATMService;
import service.CustomerService;

@SpringBootApplication
public class AtmMarkvpApplication implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private ATMService service;

	public static void main(String[] args) {
		SpringApplication.run(AtmMarkvpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/* TEST
		String sql = "INSERT INTO customer(nama,balance,owed_to_id,owed_nominal) VALUES (?,?,?,?)";
		int result = jdbcTemplate.update(sql,"valentino",5000000,null,null);
		
		if(result > 0) {
			System.out.println("inserted");
		}
		*/
		
		// set up ATM system		
		Scanner scan = new Scanner(System.in);
		ATM a = new ATM(scan);
		
		String strSearch = "";
		Customer c = service.findByName(strSearch);
		
		// begin interface
		service.process(a);
		
		/*
		int state;
		i.printOutput("Ketik 1 untuk lanjut, 2 untuk exit program");
		state = scan.nextInt();
		scan.nextLine();
		while (state != 2) {
			i.process(a);
			i.printOutput("Ketik 1 untuk lanjut, 2 untuk exit.");
			state = scan.nextInt();
			scan.nextLine();
		}
		*/		

	}

}
