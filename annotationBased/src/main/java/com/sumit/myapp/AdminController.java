package com.sumit.myapp;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.dto.QuestionJSONDTO;
import com.sumit.dto.QuestionsInTestJSON;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.DynamicOption;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionDTO;
import com.sumit.model.QuestionType;
import com.sumit.model.TestQuestions;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestRequestRejectDTO;
import com.sumit.model.TestRequestStatus;
import com.sumit.model.TestSet;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.DynamicOptionRepository;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.TestQuestionRepository;
import com.sumit.repository.TestRipository;
import com.sumit.service.AnsService;
import com.sumit.service.OptionService;
import com.sumit.service.QuestionService;
import com.sumit.service.TestService;

//import com.sumit.service.UserInfoService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {
	@Resource
	DynamicOptionRepository dOptionRipo;
	@Resource
	QuestionRepository questionRipo;
	@Resource
	TestQuestionRepository tquestionRipo;
	@Resource
	OptionsRepository optionRipo;
	@Resource
	AnsRepository ansRipo;
	@Resource
	TestRipository testRipo;
	@Autowired
	TestService testService;
	@Autowired
	QuestionService questionService;
	@Autowired
	OptionService optionService;
	@Autowired
	AnsService ansService;
	
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public @ResponseBody String userInfo(Principal principal,HttpServletResponse response) {
		return principal.getName();
		
		
	}

	@RequestMapping(value = "/setQuestion", method = RequestMethod.GET)
	public ModelAndView setQuestionPage(Principal principal,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("Question");
		String name = principal.getName();
		mav.addObject("name", name);
		return mav;
	}

	@RequestMapping(value = "/allQuestions", method = RequestMethod.GET)
	public @ResponseBody List<MainQuestion> getQuestionInJSON() {
		List<MainQuestion> questions = questionService.getAllQuestion();

		return questions;

	}

	@RequestMapping(value = "/questionType", method = RequestMethod.GET)
	public @ResponseBody QuestionType[] questionTypesInJSON() {
		QuestionType[] allQuestionTypes = QuestionType.values();
		return allQuestionTypes;
	}

	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
	public @ResponseBody String saveQuestion(
			@RequestBody QuestionJSONDTO questionJSONDto)
			throws JsonProcessingException, IOException {
		questionService.save_Question_Option_Answer(questionJSONDto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Saved");

		return val;
	}

	@RequestMapping(value = "/questionOption/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Options> getOptionOfQuestion(@PathVariable int id)
			throws JsonProcessingException, IOException {
		List<Options> opt = optionService.findOptionsByQuestion(id);
		return opt;
	}

	@RequestMapping(value = "/questionAnswer/{id}", method = RequestMethod.GET)
	public @ResponseBody List<QuestionAnswer> getAnsOfQuestion(
			@PathVariable int id) throws JsonProcessingException, IOException {
		List<QuestionAnswer> ans = ansService.findAnsByQuestion(id);
		return ans;
	}

	@RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletQuestionPage(@PathVariable int id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MainQuestion question = questionService.findQuestionById(id);
		List<Options> options = question.getOptions();
		for (Options options2 : options) {
			optionRipo.delete(options2.getId());

		}
		List<QuestionAnswer> ans = question.getQuestionAnswers();
		for (QuestionAnswer questionAnswer : ans) {
			ansRipo.delete(questionAnswer.getId());

		}
		questionRipo.delete(id);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString(question);

		return val;

	}

	@RequestMapping(value = "/search/{parameter}", method = RequestMethod.GET)
	@ResponseBody
	public List<MainQuestion> viewSearchResult(@PathVariable String parameter) {
		List<MainQuestion> searchedReasult = questionService
				.findInQuestions(parameter);

		return searchedReasult;
	}

	@RequestMapping(value = "/alltest", method = RequestMethod.GET)
	public @ResponseBody List<TestJsonDTO> viewallTest() {

		List<TestJsonDTO> listOfTestWithQuestion = new ArrayList<TestJsonDTO>();
		List<TestSet> allTest = testService.listOfAllTest();

		for (TestSet testSet : allTest) {
			List<MainQuestion> listquestion = new ArrayList<MainQuestion>();
			TestJsonDTO dto = new TestJsonDTO();
			dto.setTestSet(testSet);
			List<TestQuestions> tq = tquestionRipo.searchByTestId(testSet
					.getId());
			for (TestQuestions testQuestions : tq) {
				MainQuestion question=testQuestions.getQuestionInTestquestion();
				listquestion.add(question);
			}
			
			dto.setQuestionsInTest(listquestion);
			 
			
			listOfTestWithQuestion.add(dto);
		}

		return listOfTestWithQuestion;
	}

	// test.setQuestionInTest(questions);

	@RequestMapping(value = "/createTest", method = RequestMethod.GET)
	public ModelAndView createTestPage(@Valid TestSet test) {
		ModelAndView mav = new ModelAndView("CreateTest");

		return mav;
	}


	@RequestMapping(value = "/createTest", method = RequestMethod.POST )
	
	public  @ResponseBody  String createTest(@RequestBody TestJsonDTO testJson)
			throws JsonProcessingException, IOException {
		TestSet test = new TestSet();
		test.setName(testJson.getTestSet().getName());
		test.setFullmark(testJson.getTestSet().getFullmark());
		test.setPassmark(testJson.getTestSet().getPassmark());
		TestSet testId = testRipo.save(test);
		List<MainQuestion> listquestionId = testJson.getQuestionsInTest();
		for (MainQuestion questions : listquestionId) {
		//	MainQuestion questions = questionRipo.findOne(id);

			TestQuestions t = new TestQuestions();
			t.setQuestionInTestquestion(questions);
			t.setTestInTestquestion(testId);
			tquestionRipo.save(t);
		
		}
		for (DynamicOption dynamicOption : testJson.getDynamicOptions()) {
			dynamicOption.setTestInDynamicOption(testId);
			dOptionRipo.save(dynamicOption);
		}
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Saved");

		return val;
	}
	


	@RequestMapping(value = "/viewTest/{id}", method = RequestMethod.GET)
	public @ResponseBody QuestionsInTestJSON getTestToUpdate(@PathVariable int id) {
		 QuestionsInTestJSON dto = new QuestionsInTestJSON();
		TestSet test = testService.findTestbyId(id);

	 List<TestQuestions> testQuestions = tquestionRipo.searchByTestId(test.getId());
	 List<MainQuestion> questions = new ArrayList<MainQuestion>();
	 for (TestQuestions tq : testQuestions) {
		 questions.add(tq.getQuestionInTestquestion());
		
	}
		dto.setTestSet(test);
	 dto.setQuestions(questions);
	 	
	return dto;
	}

	@RequestMapping(value = "/updateTest/{tId}/{qId}", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public void addQuestionToTest(@PathVariable int tId,@PathVariable int qId,
			@RequestBody TestJsonDTO testJson) throws JsonProcessingException,
			IOException {
	TestSet testToUpdate=testService.findTestbyId(tId);
	testToUpdate.setName(testJson.getTestSet().getName());
	testToUpdate.setFullmark(testJson.getTestSet().getFullmark());
	testToUpdate.setPassmark(testJson.getTestSet().getPassmark());
	TestSet testSet =  testRipo.save(testToUpdate);
	
	
	MainQuestion mainQuestion = questionService.findQuestionById(qId);
	MainQuestion questionToUpdate = new MainQuestion();
	questionToUpdate.setId(mainQuestion.getId());
	//List<TestQuestions> testQuestionToUpdate = tquestionRipo.searchByTestId(tId);
	TestQuestions testQuestions = new TestQuestions();
	testQuestions.setTestInTestquestion(testSet);
	testQuestions.setQuestionInTestquestion(questionToUpdate);
	
	tquestionRipo.save(testQuestions);
	
	
		
	}

	@RequestMapping(value = "/experiment", method = RequestMethod.GET)
	public ModelAndView getQuestionsInTest() {
		
		ModelAndView mav = new ModelAndView("TestQuestion");

		return mav;

	}

	@RequestMapping(value = "/deleteTest/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTest(@PathVariable Integer id) {
	List<TestQuestions> testToDelet = tquestionRipo.searchByTestId(id);
	for (TestQuestions testQuestions : testToDelet) {
		tquestionRipo.delete(testQuestions.getId());
	}
	testService.delete(id);
	return "deleted";
	}

	@Transactional
	@RequestMapping(value = "/viewOptNAnsOf/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Options> viewOptNAns(@PathVariable Integer id,
			@Valid MainQuestion question, Options option) {

		ModelAndView mav = new ModelAndView("QuestionOptionAnswer");

		MainQuestion questionIoprocess = questionService.findQuestionById(id);

		List<Options> options = optionService.findOptionsByQuestion(id);

		mav.addObject("a", ansService.getAnsName(id));
		mav.addObject("q", questionIoprocess);
		mav.addObject("o", options);

		return options;
	}
	@RequestMapping(value = "/deleteQuestionFromTest/{tId}/{qId}", method = RequestMethod.DELETE)
	@ResponseBody
//	@Transactional
	public void deletQuestionToTest(@PathVariable int tId,@PathVariable int qId
			) throws JsonProcessingException,
			IOException {
	TestSet testToUpdate=testService.findTestbyId(tId);
	List<TestQuestions> testQuestions = tquestionRipo.searchByTestId(tId);
	MainQuestion questionToDelete = questionService.findQuestionById(qId);
	for (TestQuestions testQuestions2 : testQuestions) {
		if(testQuestions2.getQuestionInTestquestion().getId()== qId){
			tquestionRipo.delete(testQuestions2.getId());
		
		}
	}
	
	
	
		
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String viewOpAns() {

		return "home";
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String viewAboutUsPage() {

		return "aboutus";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String viewDashboardPage() {

		return "dashboard";
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String viewCreateUserPage() {

		return "createUser";
	}

	@RequestMapping(value = "/testRequests/{status}", method = RequestMethod.GET)
	@ResponseBody
	public List<TestRequestDTO> viewTestRequests(String status) {
		List<TestRequestDTO> testRequestDTOs = new ArrayList<TestRequestDTO>();
		testRequestDTOs = testService
				.getAllTestRequestsAccordingToTheirStatus(status);

		return testRequestDTOs;
	}

	@RequestMapping(value = "/testRequests/setStatus", method = RequestMethod.GET)
	public void setTestRequestsStatus(@RequestBody TestRequestDTO dto) {
		testService.setTestRequestStatusCompletedOrRejected(dto);
	}

	@RequestMapping(value = "/testRequests", method = RequestMethod.POST)
	@ResponseBody
	public void getTestRequest(TestRequestDTO testRequestDTO) {
		testService.createTestRequest(testRequestDTO);
	}

	@RequestMapping(value = "/testResults", method = RequestMethod.GET)
	public String viewTestResult() {

		return "testResults";
	}

}
