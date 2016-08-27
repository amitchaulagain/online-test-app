package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.Role;

public interface RoleRepository extends  JpaRepository<Role, Integer> {
	 @Query("select u from Role u where u.id=?1")
	 List<Role> findRollByUserName(int id);
	
	
}
