package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.binding.CustomerDetails;
import com.demo.constant.CrudOperationConstant;
import com.demo.entity.Customer;
import com.demo.props.AppProps;
import com.demo.repo.CustomerRepository;
import com.demo.service.CustomerService;

@RestController
@RequestMapping("/Customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CrudOperationConstant constant;
	
	@Autowired
	private AppProps props;

	@PostMapping("/save")
	public ResponseEntity<String> saveCustomerDetails(@RequestBody CustomerDetails customerDetails) {

		
		Customer customerData = customerRepository.findByEmail(customerDetails.getEmail());

		if (customerData == null) {
			Boolean saveCustomer = customerService.saveCustomer(customerDetails);

			if (saveCustomer) {
				return new ResponseEntity<>(props.getMessage().get("saveSuccess"), HttpStatus.CREATED);
			}
			return new ResponseEntity<>(props.getMessage().get("saveError"), HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity<String>(props.getMessage().get("saveUserExists"), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<CustomerDetails>> getAllCustomerDetails() {
		List<CustomerDetails> list = customerService.getAllCustomerDetails();

		return new ResponseEntity<List<CustomerDetails>>(list, HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<CustomerDetails> getCustomerById(@PathVariable(name = "id") Integer id) {
		CustomerDetails customerDetails = customerService.getCustomerDetailsById(id);

		return new ResponseEntity<CustomerDetails>(customerDetails, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerDetails customerDetails) {
		Boolean status = customerService.updateCustomer(customerDetails);

		if (status) {
			return new ResponseEntity<String>(props.getMessage().get("updateSuccess"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(props.getMessage().get("updateError"), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id) {
		Boolean status = customerService.deleteCustomerById(id);
		if (status) {
			return new ResponseEntity<String>(props.getMessage().get("deleteSuccess"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(props.getMessage().get("deleteError"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getByPage/{pageNumber}/{pageSize}")
	public ResponseEntity<Page<Customer>> getCustomerPageByPage(@PathVariable Integer pageNumber,
			@PathVariable Integer pageSize) {
		Page<Customer> data = customerService.getCustomerPageByPage(pageNumber, pageSize);

		return new ResponseEntity<Page<Customer>>(data, HttpStatus.OK);
	}
}
