package service;

import com.example.domain.Customer;

public interface CustomerService {

	public Customer save(Customer cust);
	
    Customer findByName(String name);	
}
