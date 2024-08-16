package com.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customer;
@Repository
public interface CustomerPaginationRepository extends PagingAndSortingRepository<Customer, Integer> {

	
}
