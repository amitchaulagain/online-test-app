package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sumit.model.User;
import com.sumit.model.UserVerification;

public interface UserVerificationRepository extends  JpaRepository<UserVerification, Integer> {
	
	@Query("SELECT uv FROM UserVerification uv WHERE uv.verificationToken=:verificationToken")
	UserVerification findUserByVerificationToken(@Param("verificationToken")String verificationToken);
	@Query("SELECT uv.user FROM UserVerification uv WHERE uv.verificationToken=:verificationToken")
	User findUserByVerificationTokenTest(@Param("verificationToken") String verificationToken);


	
}