package com.sumit.myapp;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.krams.response.JqgridResponse;
import org.krams.util.ResultDTO;
import org.krams.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.Utility.ClientUtil;
import com.sumit.convert.ConvertUtils;
import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.dto.ExaminationDTO;
import com.sumit.dto.RandomDTO;
import com.sumit.dto.SeatPlan;
import com.sumit.dto.SeatPlanningDTO;
import com.sumit.dto.SectionDTO;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.DynamicOption;
import com.sumit.model.Exam;
import com.sumit.model.Group;
import com.sumit.model.MainQuestion;
import com.sumit.model.OptionType;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionJSONDTO;
import com.sumit.model.Sections;
import com.sumit.model.StudentExaminationInfo;
import com.sumit.model.StudentResultInfo;
import com.sumit.model.TestDTO;
import com.sumit.model.TestQuestions;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestSet;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.DynamicOptionRepository;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.SetsRepository;
import com.sumit.repository.TestQuestionRepository;
import com.sumit.repository.TestRipository;
import com.sumit.service.AnsService;
import com.sumit.service.ExaminationService;
import com.sumit.service.OptionService;
import com.sumit.service.QuestionService;
import com.sumit.service.TestService;
import com.sumit.service.UserService;

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
	SetsRepository setsRipo;
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
	@Autowired
	ExaminationService examinationService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public @ResponseBody String userInfo(Principal principal,
			HttpServletResponse response) {
		return principal.getName();

	}

	@RequestMapping(value = "/setQuestion", method = RequestMethod.GET)
	public ModelAndView setQuestionPage(Principal principal,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("Question");
		String name = principal.getName();
		mav.addObject("name", name);
		return mav;
	}

	@RequestMapping(value = "/allQuestions", method = RequestMethod.GET)
	public @ResponseBody List<QuestionJSONDTO> getQuestionInJSON() {
		List<QuestionJSONDTO> dto = new ArrayList<QuestionJSONDTO>();
		List<MainQuestion> questions = questionService.getAllQuestion();
		for (MainQuestion mainQuestion : questions) {
			QuestionJSONDTO qDto = new QuestionJSONDTO();
			qDto.setMainquestion(mainQuestion);
			List<Options> options = optionRipo
					.findOptionByquestion(mainQuestion.getId());
			qDto.setOptions(options);
			List<QuestionAnswer> answers = ansRipo
					.findAnsByQuestion(mainQuestion);
			qDto.setAnswers(answers);

			dto.add(qDto);
		}

		return dto;

	}

	@RequestMapping(value = "/allOptionTypes", method = RequestMethod.GET)
	public @ResponseBody OptionType[] questionTypesInJSON() {
		OptionType[] allOptionType = OptionType.values();
		return allOptionType;
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
				MainQuestion question = testQuestions
						.getQuestionInTestquestion();
				listquestion.add(question);
			}

			dto.setQuestionsInTest(listquestion);

			listOfTestWithQuestion.add(dto);
		}

		return listOfTestWithQuestion;
	}

	// test.setQuestionInTest(questions);

	@RequestMapping(value = "/createTest", method = RequestMethod.GET)
	public ModelAndView createTestPages(@Valid TestSet test) {
		ModelAndView mav = new ModelAndView("CreateTest");

		return mav;
	}

	@RequestMapping(value = "/viewAllTests", method = RequestMethod.GET)
	public String createTestPage(@Valid TestSet test) {

		return "all-tests";
	}

	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public @ResponseBody String createTest(@RequestBody TestJsonDTO testJson)
			throws JsonProcessingException, IOException {
		TestSet test = new TestSet();
		test.setName(testJson.getTestSet().getName());
		test.setFullmark(testJson.getTestSet().getFullmark());
		test.setPassmark(testJson.getTestSet().getPassmark());
		TestSet testId = testRipo.save(test);
		if (testJson.getQuestionsInTest() != null) {
			List<MainQuestion> listquestionId = testJson.getQuestionsInTest();
			for (MainQuestion questions : listquestionId) {
				// MainQuestion questions = questionRipo.findOne(id);

				TestQuestions t = new TestQuestions();
				t.setQuestionInTestquestion(questions);
				t.setTestInTestquestion(testId);
				tquestionRipo.save(t);

			}
		}
		if (testJson.getDynamicOptions() != null) {
			for (DynamicOption dynamicOption : testJson.getDynamicOptions()) {
				if (dynamicOption.getDynamicOptionKey() != null) {
					dynamicOption.setTestInDynamicOption(testId);
					dOptionRipo.save(dynamicOption);
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString(test.getName());

		return val;
	}

	@RequestMapping(value = "/updateTest/{tId}/{qId}", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public void addQuestionToTest(@PathVariable int tId, @PathVariable int qId,
			@RequestBody TestJsonDTO testJson) throws JsonProcessingException,
			IOException {
		TestSet testToUpdate = testService.findTestbyTheirId(tId);
		testToUpdate.setName(testJson.getTestSet().getName());
		testToUpdate.setFullmark(testJson.getTestSet().getFullmark());
		testToUpdate.setPassmark(testJson.getTestSet().getPassmark());
		TestSet testSet = testRipo.save(testToUpdate);

		MainQuestion mainQuestion = questionService.findQuestionById(qId);
		MainQuestion questionToUpdate = new MainQuestion();
		questionToUpdate.setId(mainQuestion.getId());
		// List<TestQuestions> testQuestionToUpdate =
		// tquestionRipo.searchByTestId(tId);
		TestQuestions testQuestions = new TestQuestions();
		testQuestions.setTestInTestquestion(testSet);
		testQuestions.setQuestionInTestquestion(questionToUpdate);

		tquestionRipo.save(testQuestions);

	}

	@RequestMapping(value = "/examination", method = RequestMethod.GET)
	public ModelAndView getExaminationPage() {

		ModelAndView mav = new ModelAndView("examination");

		return mav;

	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ModelAndView getStudentPage() {

		ModelAndView mav = new ModelAndView("all-students");

		return mav;

	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public ModelAndView getGroupPage() {

		ModelAndView mav = new ModelAndView("all-groups");

		return mav;

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

	@RequestMapping(value = "/deleteTest/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTest(@PathVariable Integer id)
			throws JsonProcessingException {
		List<TestQuestions> testToDelet = tquestionRipo.searchByTestId(id);
		for (TestQuestions testQuestions : testToDelet) {
			tquestionRipo.delete(testQuestions.getId());
		}
		testService.delete(id);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Deleted");
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

	@RequestMapping(value = "/deleteQuestionFromTest/{tId}/{qId}", method = RequestMethod.DELETE)
	@ResponseBody
	// @Transactional
	public void deletQuestionToTest(@PathVariable int tId, @PathVariable int qId)
			throws JsonProcessingException, IOException {
		TestSet testToUpdate = testService.findTestbyTheirId(tId);
		List<TestQuestions> testQuestions = tquestionRipo.searchByTestId(tId);
		MainQuestion questionToDelete = questionService.findQuestionById(qId);
		for (TestQuestions testQuestions2 : testQuestions) {
			if (testQuestions2.getQuestionInTestquestion().getId() == qId) {
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
	public List<TestRequestDTO> viewTestRequests(@PathVariable String status) {
		List<TestRequestDTO> testRequestDTOs = new ArrayList<TestRequestDTO>();
		testRequestDTOs = testService
				.getAllTestRequestsAccordingToTheirStatus(status);

		return testRequestDTOs;
	}

	@RequestMapping(value = "/testRequest/{testRequestId}", method = RequestMethod.GET)
	@ResponseBody
	public TestRequestDTO viewTestRequestById(@PathVariable int testRequestId) {
		TestRequestDTO dto = new TestRequestDTO();
		dto = testService.findTestRequestById(testRequestId);
		return dto;
	}

	@RequestMapping(value = "/testRequests/setStatus", method = RequestMethod.POST, produces = "Application/json")
	public @ResponseBody String setTestRequestsStatus(
			@RequestBody TestRequestDTO dto) throws JsonProcessingException {
		testService.setTestRequestStatusCompletedOrRejected(dto);
		ObjectMapper mapper = new ObjectMapper();
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
	public @ResponseBody List<TestDTO> getAllTestInJSON() {
		List<TestDTO> allTests = questionService.getAllTests();

		return allTests;

	}

	// This request mapping is just to load the test-questions.jsp through
	// jquery done in allTests.js file
	@RequestMapping(value = "/testquestions", method = RequestMethod.GET)
	public String viewQuestionsInTest() {

		return "testquestions";
	}

	@RequestMapping(value = "/saveSet", method = RequestMethod.GET)
	@ResponseBody
	public String createSetInTest(@PathVariable Integer id)
			throws JsonProcessingException {

		testService.delete(id);

		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Created");
		return val;
	}

	@RequestMapping(value = "/viewSet/{setId}", method = RequestMethod.GET)
	@ResponseBody
	public String viewAllSetsInTest(@PathVariable Integer setId)
			throws JsonProcessingException {

		testService.delete(setId);

		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Created");
		return val;
	}

	@RequestMapping(value = "/deleteSet/{setId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSet(@PathVariable Integer setId)
			throws JsonProcessingException {

		testService.delete(setId);

		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Set deleted");
		return val;
	}

	@RequestMapping(value = "/saveSection", method = RequestMethod.POST)
	@ResponseBody
	public String createSectionInTest(@RequestBody SectionDTO sectionDTO)
			throws JsonProcessingException {
		testService.createOrEditSection(sectionDTO);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Created");
		return val;
	}

	@RequestMapping(value = "/viewSection/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Sections> viewAllSectionsInTest(@PathVariable int testId)
			throws JsonProcessingException {
		List<Sections> testSections = testService
				.findAllSectionsByTestId(testId);
		return testSections;
	}

	@RequestMapping(value = "/deleteSection/{sectionId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSection(@PathVariable Integer sectionId)
			throws JsonProcessingException {

		testService.delete(sectionId);

		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Section deleted");
		return val;
	}

	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
	@ResponseBody
	public String saveGroup(@RequestBody ExaminationAssignDTO dto)
			throws JsonProcessingException {
		examinationService.createOrEditGroup(dto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Group created");
		return val;
	}

	@RequestMapping(value = "/viewAllGroups", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> viewAllGroup() throws JsonProcessingException {
		List<Group> allGroups = examinationService.findAllGroups();
		return allGroups;
	}

	@RequestMapping(value = "/deleteGroup/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteGroup(@PathVariable int groupId)
			throws JsonProcessingException {
		examinationService.deleteGroup(groupId);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Group deleted");
		return val;
	}

	@RequestMapping(value = "/viewGroupStudents/{groupId}", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> viewGroupStudents(@PathVariable int groupId)
			throws JsonProcessingException {
		List<UserDTO> studentDTOs = examinationService
				.findStudentsByGroupId(groupId);

		return studentDTOs;
	}

	@RequestMapping(value = "/saveExam", method = RequestMethod.POST)
	@ResponseBody
	public String saveExam(@RequestBody ExaminationAssignDTO dto)
			throws JsonProcessingException {
		Exam exam=examinationService.createOrEditExam(dto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString(exam.getId());
		return val;
	}

	@RequestMapping(value = "/allExaminations", method = RequestMethod.GET)
	@ResponseBody
	public List<ExaminationDTO> showAllExaminations()
			throws JsonProcessingException {
		List<ExaminationDTO> ss = examinationService.findAllExaminations();
		return ss;
	}

	@RequestMapping(value = "/deleteExam/{examId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteExam(@PathVariable Integer examId)
			throws JsonProcessingException {

		examinationService.deleteExam(examId);

		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Exam deleted");
		return val;
	}

	public List<Sections> viewExam(@PathVariable int examId)
			throws JsonProcessingException {
		List<Sections> testSections = testService
				.findAllSectionsByTestId(examId);
		return testSections;
	}

	@RequestMapping(value = "/createTestForm", method = RequestMethod.GET)
	public String createTestFormPage(@Valid TestSet test) {

		return "create-test-form";
	}

	@RequestMapping(value = "/createSetTab", method = RequestMethod.GET)
	public String createSetFormPage(@Valid TestSet test) {

		return "Create_sets";
	}

	@RequestMapping(value = "/loadQuestionsPage", method = RequestMethod.GET)
	public String loadQuestionPage(@Valid TestSet test) {

		return "listofquestion";
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	@ResponseBody
	public String saveCategory(@RequestBody QuestionJSONDTO dto)
			throws JsonProcessingException {
		questionService.createOrEditCategory(dto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Category of question created");
		return val;
	}

	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	@ResponseBody
	public String createStudent(@RequestBody UserDTO userDTO)
			throws JsonProcessingException {
		userService.createStudent(userDTO);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Student created");
		return val;
	}

	@RequestMapping(value = "/viewAllStudents", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> viewAllStudents(UserDTO userDTO)
			throws JsonProcessingException {
		List<UserDTO> userDTOs = userService.viewAllStudents();
		return userDTOs;
	}

	@RequestMapping(value = "/addStudentsToGroup", method = RequestMethod.POST)
	@ResponseBody
	public String addStudentsToGroup(@RequestBody ExaminationAssignDTO dto)
			throws JsonProcessingException {
		examinationService.addStudentsToGroup(dto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Group created");
		return val;
	}

	@RequestMapping(value = "deleteStudentsFromGroup/{groupId}/{studentId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteStudentsFromGroup(@PathVariable int groupId,
			@PathVariable int studentId) throws JsonProcessingException {
		examinationService.deleteStudentsFromGroup(groupId, studentId);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Group deleted");
		return val;
	}

	@RequestMapping(value = "/viewStudent/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO createStudent(@PathVariable int id)
			throws JsonProcessingException {
		UserDTO dto = userService.findStudentByTheirId(id);
		return dto;
	}

	@RequestMapping(value = "/searchStudent", method = RequestMethod.POST)
	@ResponseBody
	public List<UserDTO> searchStudent(@RequestBody RandomDTO dto)
			throws JsonProcessingException {
		List<User> students = userService.searchStudent(dto.getSearchString());
		return ConvertUtils.convertToUserDTOsss(students);
	}

	@RequestMapping(value = "/exam/{examId}", method = RequestMethod.GET)
	@ResponseBody
	public ExaminationAssignDTO viewExamination(@PathVariable int examId)
			throws JsonProcessingException {
		ExaminationAssignDTO dto = examinationService
				.findExaminationByExamId(examId);
		return dto;
	}
	@RequestMapping(value = "/examination/addGroup/{examId}/{groupId}", method = RequestMethod.GET)
	@ResponseBody
	public String addGroupToExamination(@PathVariable int examId,@PathVariable int groupId)
			throws JsonProcessingException {
		examinationService.addGroupToExamination(examId,groupId);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString(ClientUtil.getMap().get("msg"));
		return val;

	}

	@RequestMapping(value = "/examination/removeGroup/{examId}/{groupId}", method = RequestMethod.GET)
	@ResponseBody
	public String removeGroupFromExamination(@PathVariable int examId,@PathVariable int groupId)
			throws JsonProcessingException {
		 examinationService.removeGroupFromExamination(examId,groupId);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString("Group removed");
		return val;
	}
	@RequestMapping(value = "/seatPlan/{examId}", method = RequestMethod.GET)
	@ResponseBody
	public  List<SeatPlanningDTO> getSeatPlan(@PathVariable int examId)
			throws JsonProcessingException {
		  List<SeatPlanningDTO> dto=examinationService.getSeatPlanByExamId(examId);
//		ObjectMapper mapper = new ObjectMapper();
//		String val = mapper.writeValueAsString("Group removed");
		return dto;
	}
	@RequestMapping(value = "/assignExam", method = RequestMethod.POST)
	@ResponseBody
	public String assignExam(@RequestBody ExaminationAssignDTO dto)
			throws JsonProcessingException {
		Exam exam=examinationService.assignExam(dto);
		ObjectMapper mapper = new ObjectMapper();
		String val = mapper.writeValueAsString(exam.getId() );
		return val;
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	  
	public String hello() {
		return "users";
	}
	@RequestMapping(value = "/examinationSeatPlan", method = RequestMethod.GET)
	  
	public String getExaminationSeatPlanPage() {
		return "examination-seat-plan";
	}
	
	
	@RequestMapping(value = "/allRecords", produces = "application/json")
	public @ResponseBody JqgridResponse<SeatPlan> records( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "examId", required = false) Integer examId) {
		
		Pageable pageRequest = new PageRequest(page - 1, rows);
		
		examinationService.getSeatPlanByExamId(examId);
		
		Page<StudentExaminationInfo> infos = examinationService.getSeatPlansByExamId(examId,pageRequest);
		List<SeatPlan> seatPlans = UserMapper.mapToSeatPlan(infos.getContent());
		
		JqgridResponse<SeatPlan> response = new JqgridResponse<SeatPlan>();
		response.setRows(seatPlans);
		response.setRecords(Long.valueOf(infos.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(infos.getTotalPages()).toString());
		response.setPage(Integer.valueOf(infos.getNumber() + 1).toString());
		
		return response;
	}
	@RequestMapping(value = "/showResult", method = RequestMethod.GET)
	  
	public String getResultsPage() {
		return "examination-result";
	}
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	  
	public String getAnotherResultsPage() {
		return "results";
	}
	@RequestMapping(value = "/exam/result", produces = "application/json")
	public @ResponseBody JqgridResponse<ResultDTO> getExaminationResult( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "examId", required = false) Integer examId) {

		Pageable pageRequest = new PageRequest(page - 1, rows);
		

		Page<StudentResultInfo> infos = examinationService.getExaminationResultByExamId(examId,pageRequest);
//		Exam exam=examinationService.findExaminationByExamId(examId);
		List<ResultDTO> resultDatas = UserMapper.mapToResultDTO(infos.getContent());

		JqgridResponse<ResultDTO> response = new JqgridResponse<ResultDTO>();
		response.setRows(resultDatas);
		response.setRecords(Long.valueOf(infos.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(infos.getTotalPages()).toString());
		response.setPage(Integer.valueOf(infos.getNumber() + 1).toString());

		return response;
	}
}