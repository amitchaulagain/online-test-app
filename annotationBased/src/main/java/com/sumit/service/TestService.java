package com.sumit.service;

import java.util.List;

import com.sumit.dto.TestJsonDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionDTO;
import com.sumit.model.TestDTO;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestSet;

public interface TestService {
	public void save(TestSet test);

	public List<TestSet> listOfAllTest();

	public TestDTO findTestbyId(int id);

	public TestSet update(MainQuestion question, QuestionDTO listOfQuestionId,TestSet test ,int id);

	public void delete(int id);

	int countNumberOfDatas();

	public List<TestRequestDTO> getAllTestRequestsAccordingToTheirStatus(String status);

	public void setTestRequestStatusCompletedOrRejected(TestRequestDTO dto);

	void createTestRequest(int testId);

	public TestRequestDTO findTestRequestById(int testRequestId);

	TestSet findTestbyTheirId(int id);

	public void createOrEditTest(TestSet test);

	public List<TestJsonDTO> getAllTestJsonDTOs(List<TestSet> allTest);

}
