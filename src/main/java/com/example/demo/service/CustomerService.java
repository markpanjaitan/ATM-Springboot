package com.example.demo.service;

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
	public void updateBalance(Customer c, int mode, int nominal) {
		Integer balance = c.getBalance();
		// 1 = withdraw
		if (mode == withdraw)
			balance = balance - nominal;
		else
			// 2 = deposit
			balance = balance + nominal;
		c.setBalance(balance);
		custRepo.save(c);
	}
	
}
