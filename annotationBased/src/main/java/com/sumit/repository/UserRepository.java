package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sumit.model.Role;
import com.sumit.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.username=?1")
	User findUser(String username);
	
	
	@Query("select r from Role r where r.role='ROLE_STUDENT'")
	List<Role> findAllStudents();

	@Query("select u from User u where u.userInfo.firstName LIKE :searchParameter% OR u.userInfo.lastName LIKE :searchParameter%  OR u.userInfo.email LIKE :searchParameter%")
	List<User> searchStudentByFirstNameLastNameAndEmail(@Param("searchParameter") String searchParameter );




}
