package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.binding.CustomerDetails;
import com.demo.entity.Customer;

public interface CustomerService {

	public Boolean saveCustomer(CustomerDetails customerDetails);
	
	public List<CustomerDetails> getAllCustomerDetails();
	
	public CustomerDetails getCustomerDetailsById(Integer id);
	
	public Boolean updateCustomer(CustomerDetails customerDetails);
	
	public Boolean deleteCustomerById(Integer id);
	
	public Page<Customer> getCustomerPageByPage(Integer pageNumber,Integer pageSize);
	
	
}
