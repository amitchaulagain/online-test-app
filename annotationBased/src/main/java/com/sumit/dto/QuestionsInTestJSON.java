package com.sumit.dto;

import java.util.List;

import com.sumit.model.MainQuestion;
import com.sumit.model.TestSet;

public class QuestionsInTestJSON {
	private TestSet testSet;
	private List<MainQuestion> questions;

	public TestSet getTestSet() {
	
		return testSet;
	}

	public void setTestSet(TestSet testSet) {
		this.testSet = testSet;
	}

	public List<MainQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<MainQuestion> questions) {
		this.questions = questions;
	}

}
