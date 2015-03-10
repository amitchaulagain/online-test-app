package com.sumit.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sumit.model.Sections;
import com.sumit.model.Sets;
import com.sumit.model.TestQuestions;
import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestRequestStatus;
import com.sumit.model.TestSet;
import com.sumit.model.User;


@Component
public interface ITestApi {
	

	public List<TestSet> listOfAllTest();
	
	public TestSet findTestbyId(int id);
	
	public void create(TestSet test);
	
	int countNumberOfDatas();
	
	void createTestRequest(TestRequestDTO dto);

	public List<TestRequest> findTestRequestAccordingToStatus(TestRequestStatus status);

	public void setStausOfTestRequest(TestRequestDTO dto);

	public void createTestRequest(int testId);

	public List<TestQuestions> searchByTestId(int id);

	public List<Sections> findSectionByTestId(Integer testId);

	public Sections findSectionById(Integer sectionId);
	
	public  List<Sets> findAllSetsBySpecificTestId(int testId);



}