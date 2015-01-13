package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestStatus;

public interface TestRequestRepository extends  JpaRepository<TestRequest, Integer> {

	@Query("SELECT tr from TestRequest tr where tr.testRequestStatus=:status")
	List<TestRequest> findTestRequestAccordingToTheirStatus(@Param("status")TestRequestStatus status);
 
	
}