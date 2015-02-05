package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sumit.model.Sections;

public interface SectionsRepository extends  JpaRepository<Sections, Integer> {
	 
	 @Query("select s from Sections s where s.test.id=:testId")
	 public List<Sections> findSectionByTestId(@Param("testId") Integer testId); 


	
}