package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.Group;
import com.sumit.model.StudentGroup;
import com.sumit.model.User;

public interface StudentGroupRepository extends  JpaRepository<StudentGroup, Integer> {
	@Query("SELECT sg FROM StudentGroup sg  WHERE sg.student.id=?2 AND sg.group.id=?1")
	StudentGroup findSStudentGroupByGroupIdAndStudentId(int groupId,int studentId);
	
	@Query("SELECT sg.group FROM StudentGroup sg  WHERE sg.student.id=?1")

	List<Group> findGroupsByStudentId(int id);

	@Query("SELECT sg.student FROM StudentGroup sg  WHERE sg.group.id=?1")
	List<User> findStudentsByGroupId(int id);
	
	
}