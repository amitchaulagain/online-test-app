package com.sumit.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.model.Exam;
import com.sumit.model.Examination;


@Component
public interface IExaminationApi {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	void createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);

	List<Exam> findAllExaminations();

	List<Examination> findGroupsByExaminationId(int id);
	

}