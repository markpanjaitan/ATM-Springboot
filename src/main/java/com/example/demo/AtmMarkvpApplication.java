package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.model.ATM;
import com.example.model.Customer;
import com.example.service.ATMService;
import com.example.service.CustomerService;

@SpringBootApplication
public class AtmMarkvpApplication implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired(required = true)
    private ATMService atmService;

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
		Customer c = atmService.findByName(strSearch);
		
		// begin interface
		atmService.process(a);
		
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
