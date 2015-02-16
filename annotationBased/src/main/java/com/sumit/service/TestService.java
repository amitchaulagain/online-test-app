package com.sumit.service;

import java.util.List;

import com.sumit.dto.SectionDTO;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionDTO;
import com.sumit.model.Sections;
import com.sumit.model.TestDTO;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestSet;
import com.sumit.model.User;

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

	public List<TestJsonDTO> getAllTestJsonDTOs(List<TestSet> allTest);

	void createOrEditTest(TestDTO dto);

	public void createOrEditSection(SectionDTO sectionDTO);

	public List<Sections> findAllSectionsByTestId(int testId);

	public void createOrEditGroup(SectionDTO sectionDTO);

	public void findStudentsByGroupId(SectionDTO sectionDTO);

	public void deleteGroup(SectionDTO sectionDTO);


}
