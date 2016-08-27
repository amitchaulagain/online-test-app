package com.sumit.dto;

import java.io.Serializable;

public class SectionDTO implements Serializable {
	int sectionId;
	Integer testId;
	String sectionName;

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getTestId() {
		return testId;
	}

}
