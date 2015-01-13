package com.sumit.api;

import java.util.List;

import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;

public interface IUserApi {
	

	public List<User> findAllUser();
	
	int countNumberOfDatas();

	public User findUserByUserName(String username);

	void createNewUser(User user, UserInfo info);

	public UserDTO createNewUser(UserDTO userDTO);

	public User findUserByVerificationToken(String verificationToken);


	

}
