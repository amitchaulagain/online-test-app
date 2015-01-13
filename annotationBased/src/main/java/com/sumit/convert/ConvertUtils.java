package com.sumit.convert;

import java.util.ArrayList;
import java.util.List;

import com.sumit.model.Role;
import com.sumit.model.TestRequest;
import com.sumit.model.TestRequestDTO;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;
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
		userDTO.setGender(userInfo.getGender());
		return userDTO;
	}

	public static List<TestRequestDTO> convertToTestRequestDTOs(List<TestRequest> testRequests) {
		List<TestRequestDTO> dtos= new ArrayList<TestRequestDTO>();
		for (TestRequest tr : testRequests) {
			dtos.add(ConvertUtils.convertToTestRequestDTO(tr));
		}
		return dtos;
	}

	private static TestRequestDTO convertToTestRequestDTO(TestRequest tr) {
		TestRequestDTO dto= new TestRequestDTO();
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

}
