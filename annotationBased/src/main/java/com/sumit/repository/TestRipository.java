package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.model.TestSet;

public interface TestRipository extends  JpaRepository<TestSet, Integer> {
	
	
}