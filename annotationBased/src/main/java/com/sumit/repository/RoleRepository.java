package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.sumit.model.Role;

public interface RoleRepository extends  JpaRepository<Role, Integer> {
	 @Query("select u from Role u where u.id=?1")
	 Role findRollByUserName(int id);
	
	
}
