package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.IUserApi;
import com.sumit.convert.ConvertUtils;
import com.sumit.model.Role;
//import com.sumit.api.UserInfoApi;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;
import com.sumit.model.UserSupportDTO;
//import com.sumit.model.UserInfo;
import com.sumit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private IUserApi userApi;
	List<User> allUser;
	@Autowired
	UserRepository userRepository;

	// UserInfoApi userDetailsApi;
	@Override
	@Transactional
	public void create(User user, UserInfo info) {
		// TODO Auto-generated method stub

		User createdUser = user;
		userApi.createNewUser(createdUser, info);

	}

	public List<User> findAll() {
		allUser = userApi.findAllUser();
		return allUser;
	}

	@Override
	public User findUserByUserName(String username) {
		User user = userApi.findUserByUserName(username);
		return user;
	}

	/*
	 * public static void main(String[] args) { UserServiceImpl i = new
	 * UserServiceImpl(); System.out.println(i.allUser);
	 * 
	 * }
	 */

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		UserDTO anotherUserDTO =userApi.createNewUser(userDTO);
		return anotherUserDTO;

	}
	

	@Override
	public boolean checkIfVerified(String verificationToken) {
		User user = userApi.findUserByVerificationToken(verificationToken);
		if (user != null) {
			user.setEnabled(true);
			User savedUser = userRepository.save(user);
			return savedUser.isEnabled();
		} else {
			return false;
		}
	}

	@Override
	public void setVerifiedUser(String verificationToken) {
		User user = userApi.findUserByVerificationToken(verificationToken);
		if (user != null) {
			user.setEnabled(true);
			userRepository.save(user);
		}
	}

	@Override
	public void createStudent(UserDTO userDTO) {
		 userApi.createNewStudent(userDTO);
		 
		
	}

	@Override
	public List<UserDTO> viewAllStudents() {
		 List<Role> rolestudents=userApi.findAllStudents();
		 List<UserSupportDTO> usersWithGroup=new ArrayList<UserSupportDTO>();
		 for (Role role : rolestudents) {
			 UserSupportDTO userSupportDTO= new UserSupportDTO();
			 userSupportDTO.setUser(role.getUser());
			 userSupportDTO.setGroups(userApi.findAllGroupsThatAParticularStudentIsAssociated(role.getUser().getId()));
			 usersWithGroup.add(userSupportDTO);
			
		}
		return ConvertUtils.convertToUserDTOs(usersWithGroup);
	}

	@Override
	public UserDTO findStudentByTheirId(int id) {
		 User user=userRepository.findOne(id);
		return ConvertUtils.convertToUserDTO(user, null, user.getUserInfo(), null);
	}

	@Override
	public List<User> searchStudent(String value) {
		return userApi.searchStudent(value);
	}

	 
}
