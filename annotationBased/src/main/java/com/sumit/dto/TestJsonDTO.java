package com.sumit.dto;

import java.util.List;

import com.sumit.model.TestType;


public class TestJsonDTO {
 
	private int id;
	private String name;
	private int fullmark;
	private int passmark;
	private String testDate;
	private TestType testType;
	private String duration;
	
	 List<Integer> listOfQuestions;

	  public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public TestType getTestType() {
		return testType;
	}
	public void setTestType(TestType testType) {
		this.testType = testType;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getId() {
		  return id;
	  }
	  public void setId(int id) {
		  this.id = id;
	  }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFullmark() {
		return fullmark;
	}
	public void setFullmark(int fullmark) {
		this.fullmark = fullmark;
	}
	public int getPassmark() {
		return passmark;
	}
	public void setPassmark(int passmark) {
		this.passmark = passmark;
	}
	public java.util.List<Integer> getListOfQuestions() {
		return listOfQuestions;
	}
	public void setListOfQuestions(java.util.List<Integer> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}
	
}
