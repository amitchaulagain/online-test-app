package com.sumit.convert;

import java.util.ArrayList;
import java.util.List;

import com.sumit.dto.ExaminationDTO;
import com.sumit.dto.TestJsonDTO;
import com.sumit.model.Exam;
import com.sumit.model.Role;
import com.sumit.model.TestDTO;
import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.TestSet;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;
import com.sumit.model.UserSupportDTO;
import com.sumit.model.UserVerification;

public   class ConvertUtils {

	public static UserDTO convertToUserDTO(User user,List<Role> savedRoles,UserInfo userInfo, UserVerification uv ) {
		UserDTO userDTO= new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setRoles(savedRoles);
		userDTO.setFirstName(userInfo.getFirstName());
		userDTO.setLastName(userInfo.getLastName());
		userDTO.setAddress(userInfo.getAddress());
		userDTO.setCountry(userInfo.getCountry());
		userDTO.setEmail(userInfo.getEmail());
		userDTO.setPhoneNumber(userInfo.getPhoneNumber());
		userDTO.setVerificationToken(uv.getVerificationToken());
		userDTO.setDateOfBirth(userInfo.getDob().toString());
		userDTO.setMale(userInfo.getGender());
		return userDTO;
	}

	public static List<TestRequestDTO> convertToTestRequestDTOs(List<TestRequest> testRequests) {
		List<TestRequestDTO> dtos= new ArrayList<TestRequestDTO>();
		for (TestRequest tr : testRequests) {
			dtos.add(ConvertUtils.convertToTestRequestDTO(tr));
		}
		return dtos;
	}

	public static TestRequestDTO convertToTestRequestDTO(TestRequest tr) {
		TestRequestDTO dto= new TestRequestDTO();
		dto.setId(tr.getId());
		dto.setTestRequestId(tr.getId());
		dto.setInitiatedBy(tr.getInitiatedBy().getUsername());
		dto.setRequestedDate(tr.getRequestedDate());
		dto.setTestDate(tr.getTest().getCreateDate());
		dto.setTestId(tr.getTest().getId());
		dto.setTestName(tr.getTest().getName());
		dto.setStatus(tr.getTestRequestStatus());
		if(tr.getVerifiedBy()!=null){
			dto.setVerifiedBy(tr.getVerifiedBy().getUsername());
		}
		if(tr.getVerifiedDate()!=null){
			dto.setVerifiedDate(tr.getVerifiedDate()); 
		}
		if(tr.getRejectedReason()!=null){
			
			dto.setRejectedReason(tr.getRejectedReason());
		}
		return dto;
	}

	public static List<TestDTO> convertToTestDTOs(List<TestSet> allTests) {
		List<TestDTO> dtos= new ArrayList<TestDTO>();
		for (TestSet tr : allTests) {
			dtos.add(ConvertUtils.convertToTestDTO(tr));
		}
		return dtos;
	}

	public static TestDTO convertToTestDTO(TestSet tr) {
		TestDTO dto = new TestDTO();
		dto.setId(tr.getId());
		dto.setName(tr.getName());
		dto.setFullmark(tr.getFullmark());
		dto.setPassmark(tr.getPassmark());
		if(tr.getType()!=null){
			dto.setTestType(tr.getType().toString());
		}
		dto.setDuration(tr.getDuration());
		return dto;
	}

	public static List<TestJsonDTO> convertToTestJSONDTOs() {
	 
		return null;
	}

	public static List<ExaminationDTO> convertToExaminationDTOs(List<Exam> exams) {
		List<ExaminationDTO> dtos= new ArrayList<ExaminationDTO>();
		for (Exam ex : exams) {
			 
					 
			dtos.add(ConvertUtils.convertToExaminationDTO(ex));
		}
		return dtos;
	}

	private static ExaminationDTO convertToExaminationDTO(Exam exam) {
		ExaminationDTO dto = new ExaminationDTO();
		dto.setExam(exam);
		return dto;
	}

	public static List<UserDTO> convertToUserDTOsss(List<User> students) {
			List<UserDTO> dtos= new ArrayList<UserDTO>();
			for (User user : students) {
				dtos.add(ConvertUtils.convertToUserDTO(user));
			}
			return dtos;
	}

	private static UserDTO convertToUserDTO(User user) {
		UserDTO userDTO= new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getUserInfo().getFirstName());
		userDTO.setLastName(user.getUserInfo().getLastName());
		userDTO.setAddress(user.getUserInfo().getAddress());
		userDTO.setCountry(user.getUserInfo().getCountry());
		userDTO.setEmail(user.getUserInfo().getEmail());
		userDTO.setPhoneNumber(user.getUserInfo().getPhoneNumber());
		userDTO.setDateOfBirth(user.getUserInfo().getDob().toString());
		userDTO.setMale(user.getUserInfo().isMale());
		return userDTO;
	}

	public static List<UserDTO> convertToUserDTOs(List<UserSupportDTO> userGroups) {
		List<UserDTO> dtos= new ArrayList<UserDTO>();
		for (UserSupportDTO userSupport : userGroups) {
			dtos.add(ConvertUtils.convertUserSupportDTOToUserDTO(userSupport));
		}
		return dtos;
	}

	private static UserDTO convertUserSupportDTOToUserDTO(UserSupportDTO userSupport) {
		UserDTO userDTO= new UserDTO();
		userDTO.setUsername(userSupport.getUser().getUsername());
		userDTO.setPassword(userSupport.getUser().getPassword());
		userDTO.setFirstName(userSupport.getUser().getUserInfo().getFirstName());
		userDTO.setLastName(userSupport.getUser().getUserInfo().getLastName());
		userDTO.setAddress(userSupport.getUser().getUserInfo().getAddress());
		userDTO.setCountry(userSupport.getUser().getUserInfo().getCountry());
		userDTO.setEmail(userSupport.getUser().getUserInfo().getEmail());
		userDTO.setPhoneNumber(userSupport.getUser().getUserInfo().getPhoneNumber());
		userDTO.setDateOfBirth(userSupport.getUser().getUserInfo().getDob().toString());
		userDTO.setMale(userSupport.getUser().getUserInfo().isMale());
		userDTO.setAssociatedGroups(userSupport.getGroups());
		return userDTO;
	}

	 

	 


}
