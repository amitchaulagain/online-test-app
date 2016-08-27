package com.sumit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.Utility.ClientException;
import com.sumit.Utility.ClientUtil;
import com.sumit.api.IExaminationApi;
import com.sumit.api.ITestApi;
import com.sumit.convert.ConvertUtils;
import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.dto.ExaminationDTO;
import com.sumit.dto.SeatPlanningDTO;
import com.sumit.model.Exam;
import com.sumit.model.Examination;
import com.sumit.model.Group;
import com.sumit.model.StudentExaminationInfo;
import com.sumit.model.StudentGroup;
import com.sumit.model.StudentResultInfo;
import com.sumit.model.TestSet;
import com.sumit.model.User;
import com.sumit.model.UserDTO;
import com.sumit.repository.ExaminationRepository;
import com.sumit.repository.GroupRepository;
import com.sumit.repository.RoleRepository;
import com.sumit.repository.StudentExaminationInfoRepository;
import com.sumit.repository.StudentGroupRepository;
import com.sumit.repository.StudentResultRepository;
import com.sumit.repository.UserRepository;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	@Autowired
	IExaminationApi examinationApi;
	
	@Resource
	ExaminationRepository examinationRepository;
	
	@Resource
	StudentExaminationInfoRepository studentExaminationInfoRepository;

	@Resource
	GroupRepository groupRepository;

	@Resource
	UserRepository studentRepository;
	@Resource
	StudentGroupRepository studentGroupRepository;
	@Resource
	StudentResultRepository studentResultRepository;
	@Autowired
	ITestApi testApi;
	//TODO
	@Resource
	RoleRepository roleRipo;

	@Override
	public void createOrEditGroup(ExaminationAssignDTO dto) {
		examinationApi.createOrEditGroup(dto);

	}

	@Override
	public void deleteGroup(int groupId) {
		examinationApi.deleteGroup(groupId);

	}

	@Override
	public Exam createOrEditExam(ExaminationAssignDTO dto) {
		return examinationApi.createOrEditExam(dto);

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
			// dto.setTest(exam.getTest());
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
		StudentGroup sg = new StudentGroup();
		Group group = groupRepository.findOne(dto.getGroup().getId());
		// for (int studentId : dto.getListOfStudents()) {
		// User student=studentRepository.findOne(studentId);
		// sg.setGroup(group);
		// sg.setStudent(student);
		// studentGroupRepository.save(sg);
		// }

		User student = studentRepository.findOne(dto.getStudent().getId());
		sg.setGroup(group);
		sg.setStudent(student);
		studentGroupRepository.save(sg);

	}

	// @Override
	// public void addStudentsToGroup(ExaminationAssignDTO dto) {
	// StudentGroup sg= new StudentGroup();
	// Group group=groupRepository.findOne(dto.getGroup().getId());
	// // for (int studentId : dto.getListOfStudents()) {
	// // User student=studentRepository.findOne(studentId);
	// // sg.setGroup(group);
	// // sg.setStudent(student);
	// // studentGroupRepository.save(sg);
	// // }
	//
	// User student=studentRepository.findOne(dto.getStudent().getId());
	// sg.setGroup(group);
	// sg.setStudent(student);
	// studentGroupRepository.save(sg);
	//
	// }

	@Override
	public void deleteStudentsFromGroup(int groupId, int studentId) {
		StudentGroup sg = studentGroupRepository
				.findSStudentGroupByGroupIdAndStudentId(groupId, studentId);
		studentGroupRepository.delete(sg);
	}

	@Override
	public List<Group> findAllGroups() {
		return groupRepository.findAll();
	}

	@Override
	public List<UserDTO> findStudentsByGroupId(int groupId) {
		List<User> groupStudents = groupRepository
				.findStudentsByGroupId(groupId);
		for (User user : groupStudents) {
			user.setUserRole(roleRipo.findRollByUserName(user.getId()));
		}
		return ConvertUtils.convertToUserDTOsss(groupStudents);
	}

	@Override
	public ExaminationAssignDTO findExaminationByExamId(int examId) {
		ExaminationAssignDTO dto = new ExaminationAssignDTO();
		Exam exam = examinationApi.findExamByExamId(examId);
		List<Group> assignedGroups = examinationApi
				.findAssignedGroupsByExamId(examId);
		dto.setExam(exam);
		dto.setAssignedGroups(assignedGroups);
		return dto;
	}

	@Override
	public void addGroupToExamination(int examId, int groupId) {
		Exam exam = examinationApi.findExamByExamId(examId);
		// exam.setAssigned(false);
		examinationApi.assignStudentToExaminationWithSeatPlan(exam);
		Group group = examinationApi.findGroupByGroupId(groupId);
		Map<String,String> kk=new HashMap<String,String>();
		if (examinationApi.getExaminationByExamIdAndGroupId(examId, groupId) != null) {

			try {
				throw new ClientException("Cannot add !!!!! Group already added");
			} catch (ClientException e) {
				kk.put("msg",e.getMessage());
				ClientUtil.setMap(kk);
				System.out.println("Cannot add !!!!! Group already added");
				e.printStackTrace();
			}
		} else {
			examinationApi.saveGroupToExamination(exam, group);
			kk.put("msg", "Group added successfully");
			ClientUtil.setMap(kk);
		}

	}

	@Override
	public void removeGroupFromExamination(int examId, int groupId) {
		Exam exam = examinationApi.findExamByExamId(examId);

		examinationApi.assignStudentToExaminationWithSeatPlan(exam);
		examinationApi.removeGroupFromExamination(examId, groupId);

	}

	@Override
	public List<SeatPlanningDTO> getSeatPlanByExamId(int examId) {
		List<StudentExaminationInfo> infos = examinationApi
				.findSeatPlanInformation(examId);
		return ConvertUtils.convertToSeatPlanningDTO(infos);
	}

	@Override
	public Exam assignExam(ExaminationAssignDTO dto) {
		Exam exam = examinationApi.assignStudentToExaminationWithSeatPlan(dto
				.getExam());
		return exam;
	}

	@Override
	public Page<StudentExaminationInfo> getSeatPlansByExamId(Integer examId,
			Pageable pageRequest) {
		return studentExaminationInfoRepository.getAllSeatPlanInfoByExamId(examId,pageRequest);
	}

	@Override
	public Page<StudentResultInfo> getExaminationResultByExamId(Integer examId,
			Pageable pageRequest) {
		 
		return studentResultRepository.findAllResultByExamId(examId, pageRequest);
	}
	@Override
	public List<StudentExaminationInfo> getAllSeatPlansByExamId(Integer examId) {
		return studentExaminationInfoRepository.getAllSeatPlanInfoByExamId(examId);
	}
	@Override
	public List<StudentResultInfo> getAllExaminationResultByExamId(Integer examId) {
		 
		return studentResultRepository.findAllResultByExamId(examId);
	}

	@Override
	public StudentResultInfo findStudentResultInformation(
			int studentResultInfoId) {
		 
		return studentResultRepository.findOne(studentResultInfoId);
	}

}
