package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.api.IExaminationApi;
import com.sumit.api.ITestApi;
import com.sumit.convert.ConvertUtils;
import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.dto.ExaminationDTO;
import com.sumit.model.Exam;
import com.sumit.model.Examination;
import com.sumit.model.Group;
import com.sumit.model.StudentGroup;
import com.sumit.model.TestSet;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.repository.GroupRepository;
import com.sumit.repository.StudentGroupRepository;
import com.sumit.repository.UserRepository;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	@Autowired
	IExaminationApi examinationApi;
	
	@Resource
	GroupRepository groupRepository;
	
	@Resource
	UserRepository studentRepository;
	@Resource
	StudentGroupRepository  studentGroupRepository;
	
	@Autowired
	ITestApi testApi;
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

	@Override
	public List<ExaminationDTO> findAllExaminations() {
		List<Exam> exams = examinationApi.findAllExaminations();

		return convertToExaminationDTOs(exams);
	}

	private List<ExaminationDTO> convertToExaminationDTOs(List<Exam> exams) {
		List<ExaminationDTO> dtos = new ArrayList<ExaminationDTO>();

		for (Exam exam : exams) {
			ExaminationDTO dto = new ExaminationDTO();
			
			List<Examination> allExaminations = examinationApi
					.findGroupsByExaminationId(exam.getId());
			dto.setExam(exam);
//			dto.setTest(exam.getTest());
			List<Group> groups = new ArrayList<Group>();
			for (Examination examination : allExaminations) {
				if (examination.getGroup() != null) {
					groups.add(examination.getGroup());
				}
			}
			dto.setGroups(groups);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public void addStudentsToGroup(ExaminationAssignDTO dto) {
		StudentGroup sg= new StudentGroup();
		Group group=groupRepository.findOne(dto.getGroup().getId());
//		for (int studentId : dto.getListOfStudents()) {
//			User student=studentRepository.findOne(studentId);
//			sg.setGroup(group);
//			sg.setStudent(student);
//			studentGroupRepository.save(sg);
//		}
		 
			User student=studentRepository.findOne(dto.getStudent().getId());
			sg.setGroup(group);
			sg.setStudent(student);
			studentGroupRepository.save(sg);
		
	}
//	@Override
//	public void addStudentsToGroup(ExaminationAssignDTO dto) {
//		StudentGroup sg= new StudentGroup();
//		Group group=groupRepository.findOne(dto.getGroup().getId());
////		for (int studentId : dto.getListOfStudents()) {
////			User student=studentRepository.findOne(studentId);
////			sg.setGroup(group);
////			sg.setStudent(student);
////			studentGroupRepository.save(sg);
////		}
//		 
//			User student=studentRepository.findOne(dto.getStudent().getId());
//			sg.setGroup(group);
//			sg.setStudent(student);
//			studentGroupRepository.save(sg);
//		
//	}

	@Override
	public void deleteStudentsFromGroup(int groupId, int studentId) {
		StudentGroup sg=studentGroupRepository.findSStudentGroupByGroupIdAndStudentId(groupId,studentId);
		studentGroupRepository.delete(sg);
	}

	@Override
	public List<Group> findAllGroups() {
		return groupRepository.findAll();
	}

	@Override
	public List<UserDTO> findStudentsByGroupId(int groupId) {
		List<User> groupStudents=groupRepository.findStudentsByGroupId(groupId);
		return ConvertUtils.convertToUserDTOsss(groupStudents);
	}

	 
	

}
