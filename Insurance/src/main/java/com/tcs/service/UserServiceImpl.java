package com.tcs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcs.exception.UserNotFoundException;
import com.tcs.model.User;
import com.tcs.repository.IUserPagingRepository;
import com.tcs.repository.IUserRepository;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private IUserPagingRepository userPagingRepository;
	
	
	@Override
	public User createUser(User user) {
		
		return userRepo.save(user);
	}


	@Override
	public List<User> getAllUser() {
		
		return userRepo.findAll();
	}


	@Override
	public User getUserById(Integer id) {
		
     Optional<User>opt=  userRepo.findById(id);
      
     if(opt.isPresent()) {
    	return opt.get();
     }else {
    	 throw new UserNotFoundException("User with id "+id+ " is not found");
     }
     
	}


	@Override
	public List<User> createUser(List<User> User) {
		
		return userRepo.saveAll(User);
	}


	@Override
	public Page<User> getAllUSerByPaging( int pageNumber,int pageSize) {
		
		//get total record
		long count=userRepo.count();  //no. of record 10 pageSize=3
		
		//get page count
		long pagesCount=count/pageSize; //page count=4 
		
		pagesCount=(count%pageSize==0)?pagesCount:pagesCount++;
	
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			Page<User> page= userRepo.findAll(pageable);
		
			page.forEach(System.out::println);
		
		
		return page;
		
	}


}
