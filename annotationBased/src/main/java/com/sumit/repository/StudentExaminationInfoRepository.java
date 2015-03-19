package com.sumit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.StudentExaminationInfo;

public interface StudentExaminationInfoRepository extends JpaRepository<StudentExaminationInfo, Integer> {

	
	@Query("SELECT sei FROM StudentExaminationInfo sei WHERE sei.exam.id=?1")
	List<StudentExaminationInfo> findStudentExaminationInfoByExamId(int examId);
	
	@Query("select e from StudentExaminationInfo e where e.exam.id=?1")
	Page<StudentExaminationInfo> getAllSeatPlanInfoByExamId(Integer examId, Pageable pageRequest);

	@Query("select e from StudentExaminationInfo e where e.exam.id=?1")
	List<StudentExaminationInfo> getAllSeatPlanInfoByExamId(Integer examId);

}