package com.sumit.model;


public class TestDTO {
	  Integer id;
	  String name;
	  Integer fullmark;
	  Integer passmark;
	  String testDate;
	  String testType;
	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	private String duration;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
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

}
