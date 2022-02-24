package service;

import java.sql.SQLException;

import com.example.domain.ATM;
import com.example.domain.Customer;

public interface ATMService {

	public void process(ATM a);
	public Customer findByName(String nama) throws SQLException;	
	public boolean checkUser(String namaCustomer);
}
