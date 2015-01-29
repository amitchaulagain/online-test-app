package com.sumit.myapp;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionDTO;
import com.sumit.model.QuestionJSONDTO;
import com.sumit.model.QuestionType;
import com.sumit.model.TestDTO;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestSet;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;
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
	QuestionRepository questionRipo;
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

	@RequestMapping(value = "/setQuestion", method = RequestMethod.GET)
	public ModelAndView setQuestionPage(Principal principal ) {
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
		QuestionType[] allQuestionTypes =QuestionType.values();
		return allQuestionTypes;
	}
	
	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
	public @ResponseBody String check(@RequestBody QuestionJSONDTO questionJSONDto) throws JsonProcessingException, IOException{
		questionService.save_Question_Option_Answer(questionJSONDto);
		ObjectMapper mapper =new ObjectMapper();
		String val = mapper.writeValueAsString("Saved");
		
		return val;
	}

	 @RequestMapping(value = "/questionOption/{id}", method = RequestMethod.GET)
		
		public @ResponseBody List<Options> getOptionOfQuestion(@PathVariable int id) throws JsonProcessingException, IOException{
			List<Options> opt = optionService.findOptionsByQuestion(id);
		return opt;
		}
	 @RequestMapping(value = "/questionAnswer/{id}", method = RequestMethod.GET)
		
	   	public @ResponseBody List<QuestionAnswer> getAnsOfQuestion(@PathVariable int id) throws JsonProcessingException, IOException{
	   		List<QuestionAnswer> ans = ansService.findAnsByQuestion(id);
	   	return ans;
	   	}
	 @RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.DELETE)
		public @ResponseBody String deletQuestionPage(@PathVariable int id,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			ObjectMapper mapper =new ObjectMapper();
			String val = mapper.writeValueAsString(question);
			
			return val;
			
		}
	 @RequestMapping(value = "/search/{parameter}", method = RequestMethod.GET)
		@ResponseBody public List<MainQuestion> viewSearchResult(@PathVariable String parameter) {
			List<MainQuestion> searchedReasult = questionService.findInQuestions(parameter);

			
			
			return searchedReasult;
		}

	  

	 
	@RequestMapping(value = "/createTestAndQuestion", method = RequestMethod.GET)
	public ModelAndView createTestWithQuestion() {
		List<TestSet> tests = testRipo.findAll();
		ModelAndView mav = new ModelAndView("CreateTest");
		mav.addObject("tests", tests);
		List<MainQuestion> questionList = questionRipo.findAll();
		mav.addObject("questionList", questionList);
		return mav;
	}

	@RequestMapping(value = "/createTestAndQuestion", method = RequestMethod.POST)
	public ModelAndView createTestPage(@Valid TestSet test,
			@Valid QuestionDTO questionOpt) {
		// testSet.setQuestionInTest();

		ModelAndView mav = new ModelAndView("CreateTest");
		List<MainQuestion> questions = new ArrayList<MainQuestion>();
		TestSet populateTestWithQuestions = testRipo.save(test);
		int currentTestId = populateTestWithQuestions.getId();
		test = testRipo.findOne(currentTestId);
		for (int var : questionOpt.getListOfTestQuestion()) {
			MainQuestion question = questionRipo.findOne(var);
			questions.add(question);

		}

		test.setQuestionInTest(questions);

		List<MainQuestion> questionList = questionRipo.findAll();
		mav.addObject("questionList", questionList);
		return mav;
	}

	@RequestMapping(value = "/createTest", method = RequestMethod.GET)
	public ModelAndView createTestPage(@Valid TestSet test) {
		ModelAndView mav = new ModelAndView("CreateTest");
		List<MainQuestion> listOfQuestions = questionService.getAllQuestion();
		String message = "ALL QUESTION TABLE";
		mav.addObject("message", message);
		mav.addObject("listofquestions", listOfQuestions);

		return mav;
	}

	@RequestMapping(value = "/createTest", method = RequestMethod.POST)
	  @ResponseBody public void createTest(TestSet test) {
		 testService.createOrEditTest(test);

	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public ModelAndView getQuestionsInTest(@Valid TestSet test,
			@PathVariable Integer id) {
		TestSet testForQuestion = testService.findTestbyTheirId(id);
		ModelAndView mav = new ModelAndView("TestQuestion");

		List<MainQuestion> questionsInTest = questionService
				.findQuestionByTEst(id);

		mav.addObject("questionsInTestToSelect", questionsInTest);
		mav.addObject("t", testForQuestion);
		return mav;

	}

	@RequestMapping(value = "/updateTest/{id}", method = RequestMethod.GET)
	public ModelAndView updateTest(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("update-test");
		TestSet testToUpdate = testRipo.findOne(id);

		List<MainQuestion> listOfQuestions = questionRipo.findAll();
		List<MainQuestion> questionsInTest = questionService
				.findQuestionByTEst(id);
		mav.addObject("message", testToUpdate.getName());
		mav.addObject("testId", testToUpdate.getId());
		mav.addObject("listOfQuestions", listOfQuestions);
		mav.addObject("questionsintest", questionsInTest);
		return mav;
	}

	@RequestMapping(value = "/updateTest/{id}", method = RequestMethod.POST)
	public ModelAndView updateTest(@Valid TestSet test,
			@Valid QuestionDTO listOfQuestionId, @PathVariable Integer id,
			MainQuestion question) {
		ModelAndView mav = new ModelAndView("update-test");
		testService.update(question, listOfQuestionId, test, id);

		mav.setViewName("TestQuestion");

		TestSet t = testRipo.findOne(id);
		List<MainQuestion> questionsInTestToSelect = questionRipo
				.FindQuestionInTest(t);
		mav.addObject("t", t);
		mav.addObject("questionsInTestToSelect", questionsInTestToSelect);

		return mav;
	}

	@RequestMapping(value = "/deleteTest/{id}", method = RequestMethod.GET)
	public String deleteTest(@PathVariable Integer id) throws JsonProcessingException {
		 
		testService.delete(id);

		 ObjectMapper mapper =new ObjectMapper();
			String val = mapper.writeValueAsString("Done");
			return val;
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
	public List<TestRequestDTO> viewTestRequests( @PathVariable String status) {
		List<TestRequestDTO> testRequestDTOs= new ArrayList<TestRequestDTO>();
			testRequestDTOs=testService.getAllTestRequestsAccordingToTheirStatus(status);
		 
		return testRequestDTOs;
	}
	@RequestMapping(value = "/testRequest/{testRequestId}", method = RequestMethod.GET)
	@ResponseBody
	public   TestRequestDTO viewTestRequestById( @PathVariable int testRequestId) {
		 TestRequestDTO dto = new TestRequestDTO();
		 dto=testService.findTestRequestById(testRequestId);
		 return dto;
	}
	@RequestMapping(value = "/testRequests/setStatus", method = RequestMethod.POST)
	@ResponseBody public String setTestRequestsStatus( @RequestBody TestRequestDTO dto) throws JsonProcessingException {
		 testService.setTestRequestStatusCompletedOrRejected(dto);
		 ObjectMapper mapper =new ObjectMapper();
			String val = mapper.writeValueAsString("DOne");
			return val;
	}
	@RequestMapping(value = "/testRequests", method = RequestMethod.GET)
	public String viewTestRequests() {
		 return "test-requests";
	}

	@RequestMapping(value = "/testResults", method = RequestMethod.GET)
	public String viewTestResult() {

		return "testResults";
	}
	@RequestMapping(value = "/allTests", method = RequestMethod.GET)
	public @ResponseBody List<TestDTO> getAllTestInJSON(){
		List<TestDTO> allTests = questionService.getAllTests();

		return allTests;

	}
	@RequestMapping(value = "/viewTests", method = RequestMethod.GET)
	public String getAllTestsView(){
		 

		return "all-tests";

	}
	@RequestMapping(value = "/viewTest/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TestDTO viewTest(@PathVariable int id){
		 TestDTO dto=testService.findTestbyId(id);

		return dto;

	}
	@RequestMapping(value = "/alltest", method = RequestMethod.GET)
	public @ResponseBody List<TestJsonDTO> viewallTest() {

		List<TestJsonDTO> listOfTestQuestion = new ArrayList<TestJsonDTO>();
		List<TestSet> allTest = testService.listOfAllTest();

		for (TestSet testSet : allTest) {
			List<Integer> listquestionId = new ArrayList<Integer>();
			TestJsonDTO dto = new TestJsonDTO();
			dto.setId(testSet.getId());
			dto.setName(testSet.getName());
			dto.setFullmark(testSet.getFullmark());
			dto.setPassmark(testSet.getPassmark());
			List<TestQuestions> tq = tquestionRipo.searchByTestId(testSet
					.getId());
			for (TestQuestions testQuestions : tq) {
				listquestionId.add(testQuestions.getId());
			}
			dto.setListOfQuestions(listquestionId);
			;
			listOfTestQuestion.add(dto);
		}

		return listOfTestQuestion;
	}

}
