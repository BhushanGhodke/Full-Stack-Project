package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.model.User;


public interface IUserRepository extends JpaRepository<User, Integer> {

}
