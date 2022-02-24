package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;

@Repository
@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT * FROM customer c WHERE c.nama = :nama", nativeQuery = true)
	Customer findByNama(String nama);
}
