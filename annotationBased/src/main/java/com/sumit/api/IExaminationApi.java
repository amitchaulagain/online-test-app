package com.sumit.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.model.Exam;
import com.sumit.model.Examination;
import com.sumit.model.Group;
import com.sumit.model.StudentExaminationInfo;


@Component
public interface IExaminationApi {

	void createOrEditGroup(ExaminationAssignDTO dto);

	void deleteGroup(int groupId);

	Exam createOrEditExam(ExaminationAssignDTO dto);

	void deleteExam(Integer examId);

	List<Exam> findAllExaminations();

	List<Examination> findGroupsByExaminationId(int id);

	Exam findExamByExamId(int examId);

	List<Group> findAssignedGroupsByExamId(int examId);

	Group findGroupByGroupId(int groupId);

	void saveGroupToExamination(Exam exam, Group group);

	void removeGroupFromExamination(int examId, int groupId);

	List<StudentExaminationInfo> findSeatPlanInformation(int examId);

	Exam assignStudentToExaminationWithSeatPlan(Exam exam);

	Examination getExaminationByExamIdAndGroupId(int examId, int groupId);
	

}