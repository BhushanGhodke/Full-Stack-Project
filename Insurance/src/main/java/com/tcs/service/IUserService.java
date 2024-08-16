package com.tcs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcs.model.User;

public interface IUserService {

	public User createUser(User user);
	
	public List<User> getAllUser();
	
	public User getUserById(Integer id);
	
	public List<User> createUser(List<User>User);
	
	public Page<User> getAllUSerByPaging(int pageNumber,int pageSize);
}
