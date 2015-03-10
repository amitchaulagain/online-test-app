package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.Examination;
import com.sumit.model.Group;

public interface ExaminationRepository extends JpaRepository<Examination, Integer> {
	@Query("select e from Examination e where e.exam.id=?1")
	List<Examination> findGroupsByExaminationId(int id);

	@Query("select e.group from Examination e where e.exam.id=?1")
	List<Group> findGroupsByExamId(int examId);
	
	@Query("select e from Examination e where e.exam.id=?1 AND e.group.id=?2")
	Examination findExaminationByExamIdAndGroupId(int examId, int groupId);

}