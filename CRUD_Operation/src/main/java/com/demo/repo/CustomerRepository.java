package com.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	public Customer findByEmail(String email);
}
