package com.tcs.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.model.User;
import com.tcs.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService userService;

    //create API for save user details to DB table
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user){
		
		//user service
	  User result= 	userService.createUser(user);
	     
	 return new ResponseEntity<String>("user is created with id "+user.getId(),HttpStatus.CREATED);
	}
	
	
	//create an API for getting all user from DB table
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser(){
		
		//user service
		List<User>list= userService.getAllUser();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	
	
	//create an API for getting user by using id
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) {
		
		//user service
	     User user= userService.getUserById(id);
	     
	     return new ResponseEntity<User>(user,HttpStatus.OK);
	       
	}
	
	
	//create API for saving List of User in DB Table
	@PostMapping("/save/list")
	public ResponseEntity<List<User>> createUser(@RequestBody List<User> user){
		
		//use service
	
		List<User> list= userService.createUser(user);
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	
	
	
	//create an API for getting user details page by page
    @GetMapping("/get/{pageNumber}/{pageSize}")
	public ResponseEntity<Page<User>> getUserByPage(
			@PathVariable int pageNumber, @PathVariable int pageSize){

    	//user service
    	Page<User> page=userService.getAllUSerByPaging(pageNumber, pageSize);
    	return new ResponseEntity<Page<User>>(page, HttpStatus.OK);
    	
    }
	
}
