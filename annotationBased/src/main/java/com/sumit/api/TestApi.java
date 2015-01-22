package com.sumit.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sumit.Utility.AuthenticationUtil;
import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestRequestStatus;
import com.sumit.model.TestSet;
import com.sumit.repository.TestRequestRepository;
import com.sumit.repository.TestRipository;

@Component
public class TestApi implements ITestApi {
	@Resource
	TestRipository testRipo;
	@Resource
	TestRequestRepository testRequestRepository;

	@Override
	public List<TestSet> listOfAllTest() {
		List<TestSet> allTest = testRipo.findAll();

		return allTest;
	}

	@Override
	public TestSet findTestbyId(int id) {
		TestSet test = testRipo.findOne(id);
		return test;
	}

	@Override
	public int countNumberOfDatas() {

		return 0;
	}

	@Override
	public void create(TestSet test) {
		testRipo.save(test);
	}

	public TestSet delete(int id) {
		TestSet deletedTest = testRipo.findOne(id);
		testRipo.delete(deletedTest);
		return null;
	}

	@Override
	public void createTestRequest(TestRequestDTO dto) {
		TestRequest tr = new TestRequest();
		tr.setTestRequestStatus(TestRequestStatus.PENDING);
		tr.setInitiatedBy(AuthenticationUtil.getCurrentUser());
		tr.setRequestedDate(new Date());
		tr.setTest(testRipo.findOne(dto.getTestId()));
		testRequestRepository.save(tr);

	}

	public List<TestRequest> findTestRequestAccordingToStatus(
			TestRequestStatus status) {
		return testRequestRepository.findTestRequestAccordingToTheirStatus(status);

	}

	public void setStausOfTestRequest(TestRequestDTO dto) {
		TestRequest tr = testRequestRepository.findOne(dto.getTestRequestId());
		tr.setTestRequestStatus(dto.getStatus());
		tr.setVerifiedBy(AuthenticationUtil.getCurrentUser());
		tr.setVerifiedDate(new Date());
		if (dto.getStatus() == TestRequestStatus.REJECTED) {
			tr.setRejectedReason(dto.getRejectedReason());
		}
		testRequestRepository.save(tr);
	}

	@Override
	public void createTestRequest(int testId) {
		TestRequest tr = new TestRequest();
		tr.setTestRequestStatus(TestRequestStatus.PENDING);
		tr.setInitiatedBy(AuthenticationUtil.getCurrentUser());
		tr.setRequestedDate(new Date());
		tr.setTest(testRipo.findOne(testId));
		testRequestRepository.save(tr);

		
	}

}
