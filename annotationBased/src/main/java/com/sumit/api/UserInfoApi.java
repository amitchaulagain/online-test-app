/*package com.sumit.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import com.sumit.model.UserInfo;
import com.sumit.repository.UserInfoRepository;
import com.sumit.repository.UserRepository;

@Component
public class UserInfoApi implements IUserInfoApi{
	@Resource
	UserInfoRepository userInfoRepository;
	@Resource
	UserRepository userRepoository;

	@Override
	public UserInfo addNewUserDetails(UserInfo userDetails) {
		return userInfoRepository.save(userDetails);
	}

	@Override
	public long countNumberOfDatas() {
		return userInfoRepository.count();
	}

	@Override
	public UserInfo findDetailsByName(String name) {
		
		return userInfoRepository.findDetailsByName(name) ;
	}

	@Override
	public UserInfo findDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		return userInfoRepository.findDetailsByEmail(email) ;
	}

	@Override
	public List<UserInfo> findAllUserInfo() {
		// TODO Auto-generated method stub
		return userInfoRepository.findAll();
	}
	
     
	
	
	
  
   



}
*/