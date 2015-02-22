package com.sumit.dto;

import java.util.List;

import com.sumit.model.Exam;
import com.sumit.model.Group;
import com.sumit.model.TestSet;

public class ExaminationDTO {
	private Exam exam;
	
	private TestSet test;
	public TestSet getTest() {
		return test;
	}

	public void setTest(TestSet test) {
		this.test = test;
	}

	private List<Group> groups;

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
