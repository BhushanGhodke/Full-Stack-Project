package com.tcs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tcs.model.User;

public interface IUserPagingRepository extends PagingAndSortingRepository<User, Integer> {

}
