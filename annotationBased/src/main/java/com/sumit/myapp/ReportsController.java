package com.sumit.myapp;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;

import org.krams.response.JqgridResponse;
import org.krams.response.StatusResponse;
import org.krams.response.UserDto;
import org.krams.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.repository.UserRepository;
//import com.sumit.service.UserInfoService;
import com.sumit.service.DownloadService;
import com.sumit.service.JasperDatasourceService;
import com.sumit.service.TokenService;
import com.sumit.service.UserService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/users")
public class ReportsController {
	
	
	public static final String SEAT_PLAN_TEMPLATE = "/seatPlan.jrxml";
	public static final String STUDENT_RESULT_TEMPLATE = "/result.jrxml";
	public static final String STUDENT_TEMPLATE = "/users.jrxml";

	@Autowired
	private UserRepository repository;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@Autowired
	private JasperDatasourceService datasource;

	@RequestMapping
	public String getUsersPage() {
		return "users";
	}

	@RequestMapping(value = "/records", produces = "application/json")
	public @ResponseBody JqgridResponse<UserDto> records(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) {

		Pageable pageRequest = new PageRequest(page - 1, rows);

		Page<User> users = repository.findAll(pageRequest);
		List<UserDto> userDtos = UserMapper.map(users);

		JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
		response.setRows(userDtos);
		response.setRecords(Long.valueOf(users.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
		response.setPage(Integer.valueOf(users.getNumber() + 1).toString());

		return response;
	}

	@RequestMapping(value = "/download/progress")
	public @ResponseBody StatusResponse checkDownloadProgress(
			@RequestParam String token) {
		return new StatusResponse(true, tokenService.check(token));
	}

	@RequestMapping(value = "/download/token")
	public @ResponseBody StatusResponse getDownloadToken() {
		return new StatusResponse(true, tokenService.generate());
	}

	@RequestMapping(value = "/download")
	public void download(@RequestParam String type, @RequestParam String token,
			HttpServletResponse response) {
		downloadService.download(type, token, response);
	}

	@RequestMapping(value = "/downloadUsers")
	public void downloadUsers(@RequestParam String type,
			@RequestParam String token, HttpServletResponse response) {
		JRDataSource usersData = datasource.getDataSource();
		String title = "User Report";
		downloadService.download(type, token, response, usersData, title,STUDENT_TEMPLATE);
	}

	@RequestMapping(value = "/downloadSeatPlan")
	public void downloadSeatPlanning(@RequestParam String type,
			@RequestParam String token, @RequestParam int examId,
			HttpServletResponse response) {
		JRDataSource seatPlanDataz = datasource
				.getSeatPlanningDataSource(examId);
		String title = "Seat Plan Report";
		
		downloadService.download(type, token, response, seatPlanDataz, title,SEAT_PLAN_TEMPLATE);
	}

	@RequestMapping(value = "/downloadResult")
	public void downloadResultOfSpecificExam(@RequestParam String type,
			@RequestParam String token, @RequestParam int examId,
			HttpServletResponse response) {
		JRDataSource studentResultzz = datasource.getExaminationResultDataSource(examId);
				 
		String title = "Student Performance Report";
		
		downloadService.download(type, token, response, studentResultzz, title,STUDENT_RESULT_TEMPLATE);
	}
	// @RequestMapping(value="/records", produces="application/json")
	// public @ResponseBody JqgridResponse<UserDto> records(
	// @RequestParam("_search") Boolean search,
	// @RequestParam(value="filters", required=false) String filters,
	// @RequestParam(value="page", required=false) Integer page,
	// @RequestParam(value="rows", required=false) Integer rows,
	// @RequestParam(value="sidx", required=false) String sidx,
	// @RequestParam(value="sord", required=false) String sord) {
	//
	// Pageable pageRequest = new PageRequest(page-1, rows);
	//
	// // if (search == true) {
	// // return getFilteredRecords(filters, pageRequest);
	// //
	// // }
	//
	// Page<User> users = repository.findAll(pageRequest);
	// List<UserDto> userDtos = UserMapper.map(users);
	//
	// JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
	// response.setRows(userDtos);
	// response.setRecords(Long.valueOf(users.getTotalElements()).toString());
	// response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
	// response.setPage(Integer.valueOf(users.getNumber()+1).toString());
	//
	// return response;
	// }

	/**
	 * Helper method for returning filtered records
	 */
	// public JqgridResponse<UserDto> getFilteredRecords(String filters,
	// Pageable pageRequest) {
	// String qUsername = null;
	// String qFirstName = null;
	// String qLastName = null;
	// Integer qRole = null;
	//
	// JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
	// for (JqgridFilter.Rule rule: jqgridFilter.getRules()) {
	// if (rule.getField().equals("username"))
	// qUsername = rule.getData();
	// else if (rule.getField().equals("firstName"))
	// qFirstName = rule.getData();
	// else if (rule.getField().equals("lastName"))
	// qLastName = rule.getData();
	// else if (rule.getField().equals("role"))
	// qRole = Integer.valueOf(rule.getData());
	// }
	//
	// Page<User> users = null;
	// if (qUsername != null)
	// users = repository.findByUsernameLike("%"+qUsername+"%", pageRequest);
	// if (qFirstName != null && qLastName != null)
	// users = repository.findByFirstNameLikeAndLastNameLike("%"+qFirstName+"%",
	// "%"+qLastName+"%", pageRequest);
	// if (qFirstName != null)
	// users = repository.findByFirstNameLike("%"+qFirstName+"%", pageRequest);
	// if (qLastName != null)
	// users = repository.findByLastNameLike("%"+qLastName+"%", pageRequest);
	// if (qRole != null)
	// users = repository.findByRole(qRole, pageRequest);
	//
	// List<UserDto> userDtos = UserMapper.map(users);
	// JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
	// response.setRows(userDtos);
	// response.setRecords(Long.valueOf(users.getTotalElements()).toString());
	// response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
	// response.setPage(Integer.valueOf(users.getNumber()+1).toString());
	// return response;
	// }

	// @RequestMapping(value="/get", produces="application/json")
	// public @ResponseBody UserDto get(@RequestBody UserDto user) {
	// return UserMapper.map(repository.findByUsername(user.getUsername()));
	// }

	@RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse create(@RequestParam String username,
			@RequestParam String password, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam Integer role,
			@RequestParam String emailAddress, @RequestParam String address) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setPassword(password);

		userService.createUser(dto);

		return new StatusResponse(true);
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody StatusResponse update(@RequestParam String username,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam Integer role) {

		return new StatusResponse(true);
	}

	@RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody StatusResponse delete(@RequestParam String username) {

		// User existingUser = new User(username);
		// Boolean result = service.delete(existingUser);
		return new StatusResponse(true);
	}

}
