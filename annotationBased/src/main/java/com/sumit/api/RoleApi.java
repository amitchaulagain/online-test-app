package com.sumit.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sumit.model.Role;
import com.sumit.model.User;
import com.sumit.repository.RoleRepository;
import com.sumit.repository.UserRepository;

@Component
public class RoleApi implements IRoleApi{
	@Resource
	private RoleRepository roleRepository;
	@Resource
	private UserRepository userRepository;
	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int countNumberOfDatas() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Role> findRollBy(User user) {
		return null;
		
	}


	
	



}
