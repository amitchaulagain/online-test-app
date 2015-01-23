package com.sumit.myapp;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sumit.model.Role;
import com.sumit.model.User;
import com.sumit.model.UserInfo;
import com.sumit.repository.RoleRepository;
import com.sumit.repository.UserRepository;
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

}
