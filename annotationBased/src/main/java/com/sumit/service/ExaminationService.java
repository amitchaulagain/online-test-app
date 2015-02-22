package com.sumit.service;

import java.util.List;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.dto.ExaminationDTO;
import com.sumit.model.Group;
import com.sumit.model.UserDTO;


public interface ExaminationService {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	void createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);

	List<ExaminationDTO> findAllExaminations();

	void addStudentsToGroup(ExaminationAssignDTO dto);

	void deleteStudentsFromGroup(int groupId, int studentId);

	List<Group> findAllGroups();

	List<UserDTO> findStudentsByGroupId(int groupId);


}
