package com.sumit.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestRequestStatus;
import com.sumit.model.TestSet;


@Component
public interface ITestApi {
	

	public List<TestSet> listOfAllTest();
	
	public TestSet findTestbyId(int id);
	
	public void create(TestSet test);
	
	int countNumberOfDatas();
	
	void createTestRequest(TestRequestDTO dto);

	public List<TestRequest> findTestRequestAccordingToStatus(TestRequestStatus status);

	public void setStausOfTestRequest(TestRequestDTO dto);


}