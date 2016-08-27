package com.sumit.myapp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sumit.dto.SectionDTO;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.Role;
import com.sumit.model.TestQuestions;
import com.sumit.model.TestSet;
import com.sumit.model.User;
import com.sumit.model.UserInfo;
import com.sumit.repository.RoleRepository;
import com.sumit.repository.TestQuestionRepository;
import com.sumit.repository.UserRepository;
import com.sumit.service.TestService;
//import com.sumit.service.UserInfoService;
import com.sumit.service.UserService;

@Controller
@RequestMapping("/nonSecured")
public class HomeController {
    
	@Resource
	UserRepository userRepository;
	@Autowired
	//private UserInfoService detailService;
	UserService userService;

	@Resource
	RoleRepository roleRepo;
	@Autowired
	private TestService testService;
	
	@Resource
	private TestQuestionRepository tquestionRipo;
	
	 @RequestMapping(value = "/register", method = RequestMethod.GET)
	 public String getcreateurl(){
		 
		 
		 return "register";
	 }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewShop(@ModelAttribute @Valid UserInfo info,User user,Role role,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("shop-new");
		ModelAndView mav = new ModelAndView();
		
		
	
	
		userService.create(user,info);
		/*detailService.addInfo(userDetails);*/
		//detailService.(userDetails,user);
		mav.setViewName("success");


		
		return mav;
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
	
	
	
	@RequestMapping(value = "/alltest/{sectionId} ", method = RequestMethod.GET)
	public @ResponseBody String viewSingleTest(@PathVariable int sectionId) {
		System.out.println(sectionId);
		return "hero";
 
	}
	@RequestMapping(value = "/alltest", method = RequestMethod.POST)
	public @ResponseBody String createTest(@RequestBody SectionDTO dto) {
		
		System.out.println(dto.getSectionId()+"    "+dto.getSectionName()+ "   "+dto.getTestId());
		return "hero";
 
	}
	@RequestMapping(value = "/alltest/{sectionId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTest(@PathVariable String sectionId) {
		System.out.println(sectionId);
		return "hero";
		
	}
	
	
	
	
	
	
	


}
