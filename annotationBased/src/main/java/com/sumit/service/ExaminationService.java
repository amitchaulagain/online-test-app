package com.sumit.service;

import com.sumit.dto.ExaminationAssignDTO;


public interface ExaminationService {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	void createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);


}
