package com.sumit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.StudentResultInfo;

public interface StudentResultRepository extends JpaRepository<StudentResultInfo, Integer> {
	
	@Query("select  sri from StudentResultInfo sri JOIN sri.studentExaminationInfo sei where sei.id=sri.studentExaminationInfo.id AND sei.exam.id=?1")
	Page<StudentResultInfo> findAllResultByExamId(Integer examId,Pageable pageRequest);
	
	@Query("select  sri from StudentResultInfo sri JOIN sri.studentExaminationInfo sei where sei.id=sri.studentExaminationInfo.id AND sei.exam.id=?1")
	List<StudentResultInfo> findAllResultByExamId(Integer examId);


}