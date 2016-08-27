package com.sumit.api;

import java.util.List;

import com.sumit.model.Role;
import com.sumit.model.User;

public interface IRoleApi {
	

	public List<Role> findAllRole();

	int countNumberOfDatas();

	public List<Role> findRollBy(User user);
	
	


}