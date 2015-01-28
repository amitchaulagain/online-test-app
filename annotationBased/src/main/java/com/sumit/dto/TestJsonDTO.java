package com.sumit.dto;

import java.util.List;

import com.sumit.model.MainQuestion;
import com.sumit.model.TestSet;


public class TestJsonDTO {
/*private	TestSet testSet;

private List<MainQuestion> questionsInTest;

public TestSet getTestSet() {
	return testSet;
}

public void setTestSet(TestSet testSet) {
	this.testSet = testSet;
}

public List<MainQuestion> getQuestionsInTest() {
	return questionsInTest;
}

public void setQuestionsInTest(List<MainQuestion> questionsInTest) {
	this.questionsInTest = questionsInTest;
}*/
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private int fullmark;
	private int passmark;
	  java.util.List<Integer> listOfQuestions;

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
