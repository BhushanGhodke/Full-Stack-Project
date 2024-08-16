package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.binding.CustomerDetails;
import com.demo.entity.Customer;
import com.demo.repo.CustomerPaginationRepository;
import com.demo.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerPaginationRepository customerPaginationRepository;
	
	@Override
	public Boolean saveCustomer(CustomerDetails customerDetails) {

		
		 
			 Customer customer = new Customer();

				BeanUtils.copyProperties(customerDetails, customer);
				System.out.println(customer.toString());

				Customer cust = customerRepository.save(customer);

				return cust.getId() != null;
	 
		 
	}

	@Override
	public List<CustomerDetails> getAllCustomerDetails() {

		Iterable<Customer> list = customerRepository.findAll();

		List<CustomerDetails> customerList = new ArrayList<>();

		list.forEach(x -> {

			CustomerDetails cust = new CustomerDetails();

			BeanUtils.copyProperties(x, cust);

			customerList.add(cust);

		});

		return customerList;
	}

	@Override
	public CustomerDetails getCustomerDetailsById(Integer id) {

		Customer customer = customerRepository.findById(id).orElseThrow();

		CustomerDetails customerDetails = new CustomerDetails();
		
		BeanUtils.copyProperties(customer, customerDetails);
		return customerDetails;
	}

	@Override
	public Boolean updateCustomer(CustomerDetails customerDetails) {

		Customer customer = customerRepository.findById(customerDetails.getId()).orElseThrow();

		BeanUtils.copyProperties(customerDetails, customer);

		Customer savedCustomer = customerRepository.save(customer);

		return savedCustomer.getId() != null;
	}

	@Override
	public Boolean deleteCustomerById(Integer id) {

		customerRepository.deleteById(id);
		return true;
	}
	
	
	@Override
	public Page<Customer> getCustomerPageByPage(Integer pageNumber,Integer pageSize) {
	
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
		Page<Customer> pageData = customerPaginationRepository.findAll(page);

		return pageData;
	}
	

}
