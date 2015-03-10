package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.StudentExaminationInfo;

public interface StudentExaminationInfoRepository extends JpaRepository<StudentExaminationInfo, Integer> {

	
	@Query("SELECT sei FROM StudentExaminationInfo sei WHERE sei.exam.id=?1")
	List<StudentExaminationInfo> findStudentExaminationInfoByExamId(int examId);


}