package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.ITestApi;
import com.sumit.api.QuestionApi;
import com.sumit.api.TestApi;
import com.sumit.convert.ConvertUtils;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionDTO;
import com.sumit.model.TestDTO;
import com.sumit.model.TestQuestions;
import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestRequestStatus;
import com.sumit.model.TestSet;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.TestRequestRepository;
import com.sumit.repository.TestRipository;

import freemarker.core.ReturnInstruction.Return;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	ITestApi testApi;
	@Autowired
	QuestionApi questionApi;

	@Resource
	TestRipository testRipo;

	@Resource
	TestRequestRepository testRequestRipo;

	@Resource
	QuestionRepository questionRipo;

	@Override
	public void save(TestSet test) {
		testApi.create(test);

	}

	@Override
	public List<TestSet> listOfAllTest() {

		return testApi.listOfAllTest();
	}

	@Override
	public TestSet findTestbyTheirId(int id) {

		return testApi.findTestbyId(id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		TestSet testToDelet = testApi.findTestbyId(id);

		testRipo.delete(testToDelet);
	}

	@Override
	public int countNumberOfDatas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	@Override
	public TestSet update(MainQuestion question, QuestionDTO listOfQuestionId,
			TestSet test, int id) {
		List<MainQuestion> questionsInThisTest = new ArrayList<MainQuestion>();

		test = testApi.findTestbyId(id);
		questionsInThisTest.addAll(questionApi.findQuestionByTEst(id));
		for (int i : listOfQuestionId.getListOfTestQuestion()) {
			MainQuestion questionInTest = questionApi.findQuestionById(i);
			questionsInThisTest.add(questionInTest);

		}

		// test.setQuestionInTest(questionsInThisTest);
		testApi.create(test);
		return null;
	}

	/*
	 * @Override
	 * 
	 * @Transactional public TestSet update(TestSet test,QuestionOptions
	 * questionOptions,Options options,QuestionAnswer answerS) { MainQuestion
	 * savedQuestion = questionRipo.save(question); int count = 0; for (String
	 * value : questionOptions.getListOfOptions()) { count++;
	 * 
	 * options.setName(value); options.setQuestion(savedQuestion); Options
	 * savedOption = optionRipo.save(options);
	 * 
	 * for (String ansValue : questionOpt.getListOfAnswer()) {
	 * 
	 * if (count == Integer.parseInt(ansValue)) {
	 * 
	 * QuestionAnswer questionAnswers = new QuestionAnswer();
	 * 
	 * questionAnswers.setOptionId(savedOption.getid());
	 * questionAnswers.setQuestion(savedQuestion);
	 * ansRipo.save(questionAnswers);
	 * 
	 * } }
	 * 
	 * //test.setName(name); test.setQuestionInTest(questionInTest);
	 * testRipo.save(test); return null; }
	 */

	@Transactional
	@Override
	public void createTestRequest(int testId) {
		testApi.createTestRequest(testId);

	}

	@Override
	public List<TestRequestDTO> getAllTestRequestsAccordingToTheirStatus(
			String status) {

		List<TestRequest> testRequests = new ArrayList<TestRequest>();

		if (status.equalsIgnoreCase(TestRequestStatus.PENDING.toString())) {
			testRequests = testApi
					.findTestRequestAccordingToStatus(TestRequestStatus.PENDING);

		} else if (status.equalsIgnoreCase(TestRequestStatus.REJECTED
				.toString())) {
			testRequests = testApi
					.findTestRequestAccordingToStatus(TestRequestStatus.REJECTED);

		} else if (status.equalsIgnoreCase(TestRequestStatus.COMPLETED
				.toString())) {
			testRequests = testApi
					.findTestRequestAccordingToStatus(TestRequestStatus.COMPLETED);

		}
		return ConvertUtils.convertToTestRequestDTOs(testRequests);
	}

	@Override
	public void setTestRequestStatusCompletedOrRejected(TestRequestDTO dto) {
		testApi.setStausOfTestRequest(dto);
	}

	@Override
	public TestRequestDTO findTestRequestById(int testRequestId) {

		return ConvertUtils.convertToTestRequestDTO(testRequestRipo
				.findOne(testRequestId));
	}

	@Override
	public TestDTO findTestbyId(int id) {

		return ConvertUtils.convertToTestDTO(testApi.findTestbyId(id));
	}

	@Override
	public void createOrEditTest(TestSet test) {
		if (test.getId() == 0) {
			testRipo.save(test);

		} else {
			TestSet ts = testRipo.findOne(test.getId());
			ts.setName(test.getName());
			ts.setFullmark(test.getFullmark());
			ts.setPassmark(test.getPassmark());
			ts.setDuration(test.getDuration());
			ts.setTestDate(test.getTestDate());
			testRipo.save(ts);
		}

	}

	@Override
	public List<TestJsonDTO> getAllTestJsonDTOs(List<TestSet> allTest) {
		List<TestJsonDTO> listOfTestQuestion = new ArrayList<TestJsonDTO>();

		for (TestSet testSet : allTest) {
			List<Integer> listquestionId = new ArrayList<Integer>();
			TestJsonDTO dto = new TestJsonDTO();
			dto.setId(testSet.getId());
			dto.setName(testSet.getName());
			dto.setFullmark(testSet.getFullmark());
			dto.setPassmark(testSet.getPassmark());
			dto.setTestType(testSet.getType());
			dto.setDuration(testSet.getDuration());
			if(testSet.getTestDate()!=null){
				dto.setTestDate(testSet.getTestDate().toGMTString());
			}
			List<TestQuestions> tq = testApi.searchByTestId(testSet.getId());
			for (TestQuestions testQuestions : tq) {
				listquestionId.add(testQuestions.getId());
			}
			dto.setListOfQuestions(listquestionId);
			listOfTestQuestion.add(dto);
		}
		return listOfTestQuestion;

	}

}
