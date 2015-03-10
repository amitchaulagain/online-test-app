package com.sumit.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.model.Exam;
import com.sumit.model.Examination;
import com.sumit.model.Group;
import com.sumit.model.Sets;
import com.sumit.model.StudentExaminationInfo;
import com.sumit.model.User;
import com.sumit.repository.ExamRepository;
import com.sumit.repository.ExaminationRepository;
import com.sumit.repository.GroupRepository;
import com.sumit.repository.StudentExaminationInfoRepository;
import com.sumit.repository.StudentGroupRepository;

@Component
public class ExaminationApi implements IExaminationApi {
	@Resource
	GroupRepository groupRepository;

	@Resource
	ExamRepository examRepository;
	@Resource
	ExaminationRepository examinationRepository;
	@Resource
	StudentGroupRepository studentGroupRepository;
	@Resource
	StudentExaminationInfoRepository studentExaminationInfoRepository;
	@Autowired
	ITestApi testApi;

	@Override
	public void createOrEditGroup(ExaminationAssignDTO dto) {
		Group group = new Group();
		if (dto.getGroup().getId() != 0) {
			group = groupRepository.findOne(dto.getGroup().getId());
			group.setName(dto.getGroup().getName());

		} else {
			group.setName(dto.getGroup().getName());
		}
		groupRepository.save(group);
	}

	@Override
	public void deleteGroup(int groupId) {
		Group gr = groupRepository.findOne(groupId);
		groupRepository.delete(gr);

	}

	@Override
	public Exam createOrEditExam(ExaminationAssignDTO dto) {
		Exam exam = new Exam();
		if (dto.getExam().getId() != 0) {
			exam = examRepository.findOne(dto.getExam().getId());
			exam.setName(dto.getExam().getName());
			exam.setTest(testApi.findTestbyId(dto.getTestId()));

		} else {
			exam.setTest(testApi.findTestbyId(dto.getTestId()));
			exam.setName(dto.getExam().getName());
		}
//		if (dto.getExam().getIsAssigned()) {
//			exam.setAssigned(true);
//			Exam ex = assignStudentToExaminationWithSeatPlan(dto.getExam()
//					.getId());
//			return examRepository.save(ex);
//		}
		return examRepository.save(exam);

	}
	@Override
	public Exam assignStudentToExaminationWithSeatPlan(Exam examDTO) {
		int examId=examDTO.getId();
		Exam exam = examRepository.findOne(examId);

		List<StudentExaminationInfo> sei = findSeatPlanInformation(examId);
		List<Group> assignedGroups = findAssignedGroupsByExamId(examId);

		if (examDTO.getIsAssigned() && sei.size() < 1 && assignedGroups.size() > 0) {

			List<User> allStudents = new ArrayList<User>();
			if (assignedGroups != null) {

				for (Group group : assignedGroups) {
					List<User> groupStudents = studentGroupRepository
							.findStudentsByGroupId(group.getId());
					allStudents.addAll(groupStudents);
				}
			}
			exam.setAssigned(true);
			arrangeSeatPlanForStudents(allStudents, exam);
			return examRepository.save(exam);
		} else {
			removeSeatPlan(exam.getId());
			exam.setAssigned(false);
			return examRepository.save(exam);

		}
	}

	private void removeSeatPlan(int id) {
		studentExaminationInfoRepository
				.delete(studentExaminationInfoRepository
						.findStudentExaminationInfoByExamId(id));

	}

	private void arrangeSeatPlanForStudents(List<User> allStudents, Exam exam) {
		List<Sets> setsInTest = testApi.findAllSetsBySpecificTestId(exam
				.getTest().getId());
		int count = 0;
		int seatCounter = 0;
		int noOfSets = setsInTest.size();
		for (User student : allStudents) {
			count++;
			seatCounter++;

			if (noOfSets == 0) {
				StudentExaminationInfo sExamInfo = new StudentExaminationInfo();
				sExamInfo.setExam(exam);
				sExamInfo.setSet(null);
				sExamInfo.setSeatNumber(exam.getName() + " 00" + seatCounter);
				sExamInfo.setStudent(student);
				studentExaminationInfoRepository.save(sExamInfo);
			}
			else{
				
				StudentExaminationInfo sExamInfo = new StudentExaminationInfo();
				sExamInfo.setExam(exam);
				sExamInfo.setSet(setsInTest.get(count - 1));
				sExamInfo.setSeatNumber(exam.getName() + " 00" + seatCounter);
				sExamInfo.setStudent(student);
				studentExaminationInfoRepository.save(sExamInfo);
				if (count == noOfSets) {
					count = 0;
				}
			}

		}

	}

	@Override
	public void deleteExam(Integer examId) {
		Exam exam = examRepository.findOne(examId);
		examRepository.delete(exam);
	}

	@Override
	public List<Exam> findAllExaminations() {
		return examRepository.findAll();
	}

	@Override
	public List<Examination> findGroupsByExaminationId(int id) {

		return examinationRepository.findGroupsByExaminationId(id);
	}

	@Override
	public Exam findExamByExamId(int examId) {

		return examRepository.findOne(examId);
	}

	@Override
	public List<Group> findAssignedGroupsByExamId(int examId) {

		return examinationRepository.findGroupsByExamId(examId);
	}

	@Override
	public Group findGroupByGroupId(int groupId) {

		return groupRepository.findOne(groupId);
	}

	@Override
	public void saveGroupToExamination(Exam exam, Group group) {
		Examination ex = new Examination();
		ex.setExam(exam);
		ex.setGroup(group);
		examinationRepository.save(ex);

	}

	@Override
	public void removeGroupFromExamination(int examId, int groupId) {
		Examination ex = examinationRepository
				.findExaminationByExamIdAndGroupId(examId, groupId);
		examinationRepository.delete(ex);

	}

	public List<Sets> findAllSetsByTestId(int testId) {
		return testApi.findAllSetsBySpecificTestId(testId);

	}

	@Override
	public List<StudentExaminationInfo> findSeatPlanInformation(int examId) {

		return studentExaminationInfoRepository
				.findStudentExaminationInfoByExamId(examId);
	}

}
