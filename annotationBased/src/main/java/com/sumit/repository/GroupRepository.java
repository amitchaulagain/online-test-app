package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.Group;
import com.sumit.model.User;

public interface GroupRepository extends JpaRepository<Group, Integer> {
	
@Query("SELECT sg.student FROM StudentGroup sg WHERE sg.group.id=?1")
List<User> findStudentsByGroupId(int groupId);

}