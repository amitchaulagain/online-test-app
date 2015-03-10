package com.sumit.service;

import java.util.List;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.dto.ExaminationDTO;
import com.sumit.dto.SeatPlanningDTO;
import com.sumit.model.Exam;
import com.sumit.model.Group;
import com.sumit.model.UserDTO;


public interface ExaminationService {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	Exam createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);

	List<ExaminationDTO> findAllExaminations();

	void addStudentsToGroup(ExaminationAssignDTO dto);

	void deleteStudentsFromGroup(int groupId, int studentId);

	List<Group> findAllGroups();

	List<UserDTO> findStudentsByGroupId(int groupId);

	ExaminationAssignDTO findExaminationByExamId(int examId);

	void addGroupToExamination(int examId, int groupId);

	void removeGroupFromExamination(int examId, int groupId);

	List<SeatPlanningDTO> getSeatPlanByExamId(int examId);

	Exam assignExam(ExaminationAssignDTO dto);


}
