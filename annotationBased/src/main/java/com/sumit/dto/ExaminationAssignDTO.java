package com.sumit.dto;

import java.util.List;

import com.sumit.model.Exam;
import com.sumit.model.Group;
import com.sumit.model.User;

public class ExaminationAssignDTO {
	private Exam exam;
	private Group group;
	private User student;
	private int testId;
	private Integer[] listOfStudents;
	private List<Group> assignedGroups;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public Integer[] getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(Integer[] listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public void setAssignedGroups(List<Group> assignedGroups) {
		this.assignedGroups=assignedGroups;
		
	}
	public List<Group> getAssignedGroups(){
		return assignedGroups;
		
	}
}
