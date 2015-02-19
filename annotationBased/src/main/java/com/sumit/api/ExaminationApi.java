package com.sumit.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.model.Exam;
import com.sumit.model.Group;
import com.sumit.repository.ExamRepository;
import com.sumit.repository.GroupRepository;


@Component
public class ExaminationApi implements IExaminationApi {
	@Resource
	GroupRepository groupRepository;
	
	@Resource
	ExamRepository examRepository;
	
	@Override
	public void createOrEditGroup(ExaminationAssignDTO dto) {
		Group group = new Group();
		 if(dto.getExam().getId()!=0){
			group=groupRepository.findOne(dto.getGroup().getId());
			 group.setName(dto.getGroup().getName());
			  
		 }
		 else{
			 group.setName(dto.getGroup().getName());
		 }
		 groupRepository.save(group);
	}

	@Override
	public void deleteGroup(int groupId) {
		Group gr= groupRepository.findOne(groupId);
		groupRepository.delete(gr);
		
	}

	@Override
	public void createOrEditExam(ExaminationAssignDTO dto) {
		Exam exam = new Exam();
		 if(dto.getExam().getId()!=0){
			 exam=examRepository.findOne(dto.getExam().getId());
			 exam.setName(dto.getGroup().getName());
			  
		 }
		 else{
			 exam.setName(dto.getExam().getName());
		 }
		 examRepository.save(exam);
		
	}

	@Override
	public void deleteExam(Integer examId) {
		Exam exam = examRepository.findOne(examId);
		examRepository.delete(exam);
	}
	 

}
