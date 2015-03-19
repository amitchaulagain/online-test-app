package com.sumit.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.convert.ConvertUtils;
import com.sumit.model.Authorities;
import com.sumit.model.Group;
import com.sumit.model.Role;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.model.UserInfo;
import com.sumit.model.UserVerification;
import com.sumit.repository.RoleRepository;
import com.sumit.repository.StudentGroupRepository;
import com.sumit.repository.UserInfoRepository;
import com.sumit.repository.UserRepository;
import com.sumit.repository.UserVerificationRepository;

@Component
public class UserApi implements IUserApi {
	@Resource
	UserRepository userRepository;
	@Resource
	RoleRepository roleRepo;
	@Resource
	UserInfoRepository userInfoRepository;
	@Resource
	UserVerificationRepository userVerificationRepository;
	@Resource
	StudentGroupRepository studentGroupRepository;

	@Override
	public List<User> findAllUser() {

		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void createNewUser(User user, UserInfo info) {
		Role role = new Role();
		User userToSave = userRepository.save(user);
		role.setUser(userToSave);
		role.setRole("ROLE_USER");
		roleRepo.save(role);
		info.setUser(userToSave);
		userInfoRepository.save(info);

	}

	@Override
	public int countNumberOfDatas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findUserByUserName(String username) {
		User name = userRepository.findUser(username);
		return name;
	}
	@Transactional
	@Override
	public UserDTO createNewUser(UserDTO userDTO) {
		User user = new User();
		user.setEnabled(false);
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());

		List<Role> userRoles = new ArrayList<Role>();
		Role role = new Role();
		role.setRole(Authorities.ROLE_USER.toString());
		userRoles.add(role);
		User savedUser = userRepository.save(user);
		role.setUser(savedUser);
		List<Role> savedRoles=roleRepo.save(userRoles);

		UserVerification uv = new UserVerification();
		uv.setAutoVerificationToken();
		uv.setUser(savedUser);
		UserVerification savedUv=userVerificationRepository.save(uv);

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(userDTO.getFirstName());
		userInfo.setLastName(userDTO.getLastName());
		userInfo.setEmail(userDTO.getEmail());
		userInfo.setRegisterDate(new Date());
		userInfo.setDob(new Date());
		userInfo.setGender(userDTO.isMale());
		userInfo.setUser(savedUser);
		UserInfo savedUserInfo=userInfoRepository.save(userInfo);
		return ConvertUtils.convertToUserDTO(user,savedRoles,savedUserInfo,savedUv);

	}
	
	@Override
	public User findUserByVerificationToken(String verificationToken) {
		// UserVerification
		// userVerification=userVerificationRepository.findUserByVerificationToken(verificationToken);
		// return userVerification.getUser();
//		These are the two awesome methods .....use whatever you like
		
		User user = userVerificationRepository.findUserByVerificationTokenTest(verificationToken);
		return user;
	}

	@Override
	public void createNewStudent(UserDTO userDTO) {
		if(userDTO.getStudentId()==0){
			User user = new User();
			user.setEnabled(false);
			user.setUsername(userDTO.getEmail());
			user.setPassword(userDTO.getFirstName()+userDTO.getLastName()+'@'+123);
			user.setEnabled(true);

			List<Role> userRoles = new ArrayList<Role>();
			Role role = new Role();
			role.setRole(Authorities.ROLE_USER.toString());
			userRoles.add(role);
			role.setRole(Authorities.ROLE_STUDENT.toString());
			userRoles.add(role);
			User savedUser = userRepository.save(user);
			role.setUser(savedUser);
			roleRepo.save(userRoles);

			UserInfo userInfo = new UserInfo();
			userInfo.setFirstName(userDTO.getFirstName());
			userInfo.setLastName(userDTO.getLastName());
			userInfo.setEmail(userDTO.getEmail());
			userInfo.setRegisterDate(new Date());
			userInfo.setDob(new Date());
			userInfo.setGender(userDTO.isMale());
			userInfo.setAddress(userDTO.getAddress());
			userInfo.setCountry(userDTO.getCountry());
			userInfo.setUser(savedUser);
			userInfoRepository.save(userInfo); 
		}
		else{
			UserInfo userInfo =userInfoRepository.findUserInfoByUserId(userDTO.getStudentId());
			userInfo.setFirstName(userDTO.getFirstName());
			userInfo.setLastName(userDTO.getLastName());
			userInfo.setEmail(userDTO.getEmail());
			userInfo.setRegisterDate(new Date());
			userInfo.setDob(new Date());
			userInfo.setGender(userDTO.isMale());
			userInfo.setAddress(userDTO.getAddress());
			userInfo.setCountry(userDTO.getCountry());
			userInfoRepository.save(userInfo); 

		}
		
	}

	@Override
	public List<Role> findAllStudents() {
		
		return  userRepository.findAllStudents();
	}

	@Override
	public List<Group> findAllGroupsThatAParticularStudentIsAssociated(int id) {
		List<Group> groups=studentGroupRepository.findGroupsByStudentId(id);
		return groups;
	}

	@Override
	public List<User> searchStudent(String value) {
		 
		return userRepository.searchStudentByFirstNameLastNameAndEmail(value);
	}

}
