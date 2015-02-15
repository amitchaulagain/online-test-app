package com.sumit.myapp;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.sumit.model.TestDTO;
import com.sumit.model.TestSet;
import com.sumit.repository.TestRipository;
import com.sumit.service.OptionService;
import com.sumit.service.QuestionService;
import com.sumit.service.TestService;

//import com.sumit.service.UserInfoService;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/student")
public class StudentController {
	@Autowired
	OptionService optionService;
	@Autowired
	QuestionService questionService;
	@Autowired
	TestService testService;
	@Resource
	TestRipository testRipo;
	private Map<Integer, List<Integer>> myQuestionAnsMap;
	long start;
	long end;
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView studentViewPage(Principal principle) {
		ModelAndView mav = new ModelAndView("Student");
		String username = principle.getName();
		mav.addObject("username", username);
		
		return mav;
	}

	@RequestMapping(value = "/viewTests", method = RequestMethod.GET)
	public ModelAndView studentViewTest() {
		ModelAndView mav = new ModelAndView("Student-ViewTest");
		List<TestSet> testForStudent = testRipo.findAll();
		mav.addObject("testForStudent", testForStudent);

		return mav;
	}

	@RequestMapping(value = "/takeTest/{id}", method = RequestMethod.GET)
	public ModelAndView studenTakeTest(@PathVariable Integer id,
			@Valid QuestionDTO listOfAnswers) {
        start = System.currentTimeMillis( );

		ModelAndView mav = new ModelAndView("Student-TestQuestion");
		
		Map<MainQuestion, List<Options>>  questionAnsMap = new HashMap<MainQuestion, List<Options>>();

		List<MainQuestion> QuestionInTest = questionService
				.findQuestionByTEst(id);
		List<Integer> anslist= new ArrayList<Integer>();
		  Map<Integer, List<Integer>> qqqMap= new HashMap<Integer, List<Integer>>();
		for (MainQuestion question : QuestionInTest) {
			List<QuestionAnswer> options = question.getQuestionAnswers();
				
			
			for(QuestionAnswer qas:question.getQuestionAnswers()){
				anslist.add(qas.getOptionId());
			}
			qqqMap.put(question.getId(), anslist);
			anslist=new ArrayList<Integer>();
			questionAnsMap.put(question, question.getOptions());
		}
		this.myQuestionAnsMap=qqqMap;
		// listOfAnswers.setQuestionAnsMap(questionAnsMap);
		mav.addObject("questionAnsMap", questionAnsMap);
		return mav;
	}

	@RequestMapping(value = "/checkTest", method = RequestMethod.POST)
	public ModelAndView checkTests(@Valid QuestionDTO questionDTO,
			@Valid MainQuestion question, @Valid QuestionAnswer questionans) {
		end = System.currentTimeMillis( );


		Map<Integer, List<Integer>> questionOptionMap = new HashMap<Integer, List<Integer>>();
		List<Integer> optList = new ArrayList<Integer>();
		 Map<Integer, List<Integer>> getQuestionOptionMap=getQuestionMap(questionDTO, questionOptionMap, optList);
		 questionOptionMap=myQuestionAnsMap;
		
		// questionDTO.setQuestionAnswers(listOfns);
		ModelAndView mav = new ModelAndView("success");
		long diff = end - start;
		float f = (float)diff/234f;
		

	
		mav.addObject("totaltime",f);

		mav.addObject("totalRightAnswers",checkAnswer(getQuestionOptionMap,questionOptionMap));
		return mav;
	}

	private int checkAnswer(Map<Integer, List<Integer>> questionOptionMap,
			Map<Integer, List<Integer>> questionAnswerMap) {

				int count=0;
		
			for(Map.Entry<Integer, List<Integer>> mm:questionOptionMap.entrySet()){
				int key=mm.getKey();
				List<Integer> ooo=mm.getValue();
				List<Integer> ttt=questionAnswerMap.get(key);
				
				 if(ooo.equals(ttt)){
					 
					count++;
					 System.out.println("RIght Answer");
				 }
				
			}
			return count;
		
		
	}

	private Map<Integer, List<Integer>> getQuestionMap(QuestionDTO questionDTO,
			Map<Integer, List<Integer>> questionOptionMap, List<Integer> optList) {
		int puranoQuestionId = 99;
		boolean old = false;
		int optionId = 0;
		int last=questionDTO.getListOfAnswer().length;
		 int count=0;

		for (String val : questionDTO.getListOfAnswer()) {

			int myvalue = 0;

			if (val.contains("a")) {
				for (int i = 0; i < val.length(); i++) {
					if (val.charAt(i) == 'a') {
						myvalue = i + 1;
						break;
					}
				}

					//String ques = val.substring(0, myvalue - 1);

					 

					int questionId = Integer.parseInt(val.substring(0,
							myvalue - 1));
					if(count==0){
						puranoQuestionId=questionId;
					}
					optionId = Integer.parseInt(val.substring(myvalue + 1,
							val.length()));


						optionId = Integer.parseInt(val.substring(myvalue + 1,
								val.length()));
						
						if(questionId!=puranoQuestionId){
							//optList.add(optionId);
							questionOptionMap.put(puranoQuestionId, optList);
							optList= new ArrayList<Integer>();
						}

						optList.add(optionId);
						++count;
						puranoQuestionId=questionId;
						
						if(count==last){
							questionOptionMap.put(questionId, optList);
						}
					} 

					 
				}
			return questionOptionMap;
	}
	
	@RequestMapping(value = "/allTests", method = RequestMethod.GET)
	public @ResponseBody List<TestDTO> getAllTestInJSON(){
		List<TestDTO> allTests = questionService.getAllTests();

		return allTests;

	}
	@RequestMapping(value = "/send/testRequest/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public String getTestRequest(@PathVariable int testId) throws JsonProcessingException {
		testService.createTestRequest(testId);
		ObjectMapper mapper =new ObjectMapper();
		String val = mapper.writeValueAsString("Test Request sent successfully !! Be prepared for the test. Best Of Luck .");
		
		return val;
	}

}
