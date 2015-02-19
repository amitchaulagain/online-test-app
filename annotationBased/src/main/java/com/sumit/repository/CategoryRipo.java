package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.model.Category;

public interface CategoryRipo extends  JpaRepository<Category, Integer> {
	 
}