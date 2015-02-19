package com.sumit.api;

import org.springframework.stereotype.Component;

import com.sumit.dto.ExaminationAssignDTO;


@Component
public interface IExaminationApi {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	void createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);
	

}