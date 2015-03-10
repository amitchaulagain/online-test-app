package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

//	@Query("select  from User u where u.username=?1")
//	List<Group> findGroupsByExamId(int examId);

}