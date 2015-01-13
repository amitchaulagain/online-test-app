/*package com.sumit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.IUserInfoApi;
import com.sumit.model.User;
import com.sumit.model.UserInfo;
import com.sumit.repository.UserInfoRepository;


@Service
public  class UserInfoServiceImpl implements UserInfoService{
	@Autowired	
	private IUserInfoApi userDetailsApi;

	@Autowired
	UserInfoRepository userDetailsRepository;

	@Override
	@Transactional
	

	public List<UserInfo> findAll() {
		
		return null;
	}

	@Override
	public UserInfo addInfo(UserInfo userDetails) {
    userDetailsApi.addNewUserDetails(userDetails);
		return null;
	}

	@Override
	public UserInfo findDetailByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo findDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
*/