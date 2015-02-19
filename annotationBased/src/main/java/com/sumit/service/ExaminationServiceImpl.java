package com.sumit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sumit.api.IExaminationApi;
import com.sumit.dto.ExaminationAssignDTO;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	
	@Autowired
	IExaminationApi examinationApi;
	@Override
	public void createOrEditGroup(ExaminationAssignDTO dto) {
		examinationApi.createOrEditGroup(dto);
		
	}
	@Override
	public void deleteGroup(int groupId) {
		examinationApi.deleteGroup(groupId);
		
	}
	@Override
	public void createOrEditExam(ExaminationAssignDTO dto) {
		examinationApi.createOrEditExam(dto);
		
	}
	@Override
	public void deleteExam(Integer examId) {
		examinationApi.deleteExam(examId);
		
	}
	 
}
