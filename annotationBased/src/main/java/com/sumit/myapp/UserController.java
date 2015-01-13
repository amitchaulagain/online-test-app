package com.sumit.myapp;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sumit.model.EmailDTO;
import com.sumit.model.UserDTO;
import com.sumit.service.MailService;
import com.sumit.service.UserService;

@Controller
@RequestMapping("/common")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	private String COMPANY_EMAIL_ADDRESS = "jha.gokul05@gmail.com";

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String viewOpAns() {

		System.out.println("hello");

		return "View-All-Question";
	}

	@Transactional
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute @Valid UserDTO userDTO) {

		ModelAndView mav = new ModelAndView("home");
		UserDTO dto = userService.createUser(userDTO);

		try {
			configureMailServerAndSendEmail(dto);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mav;
	}

	private void configureMailServerAndSendEmail(UserDTO userDTO) throws MessagingException {

		mailService.configureMailServer("jha.gokul05@gmail.com",
				"lovingworld12345");
		String userLinkTOVerify = "http://localhost:8085/annotationBased/common/send/"
				+ userDTO.getVerificationToken();
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setFromEmailAddress(COMPANY_EMAIL_ADDRESS);
		emailDTO.setToEmailAddress(userDTO.getEmail());
		emailDTO.setSubject("About verification request of User in SOFTRONIX ONline-Test-Application");
		emailDTO.setContent("Hello Mr." + userDTO.getFirstName() + " "
				+ userDTO.getLastName() + "."
				+ "Click in the link shown to verify" + "  " + userLinkTOVerify);
		emailDTO.setToken(userDTO.getVerificationToken());


		mailService.sendMail(emailDTO);

	}

	@RequestMapping(value = "/send/{verificationToken}", method = RequestMethod.GET)
	public String check(@PathVariable String verificationToken) {
		userService.setVerifiedUser(verificationToken);

		return "home";
	}

}
