package com.sumit.service;

import java.util.List;

import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;


public interface UserService {
	

	public List<User> findAll();

	public User findUserByUserName(String username);

	void create(User user, UserInfo info);

	public UserDTO createUser(UserDTO userDTO);

	boolean checkIfVerified(String verificationToken);

	public void setVerifiedUser(String verificationToken);

	public void createStudent(UserDTO userDTO);

	public List<UserDTO> viewAllStudents();

	
	
}
