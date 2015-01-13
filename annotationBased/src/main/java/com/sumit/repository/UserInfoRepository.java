package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	// @Query("select * from user u where u.name='sumit'")
	// int countAllData();
	//
	@Query("select d from UserInfo d where d.firstName=?1 " )

	UserInfo findDetailsByName(String name);
	
	@Query("select d from UserInfo d where d.email=?1 " )
	UserInfo findDetailsByEmail(String email);

}
