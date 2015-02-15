package com.sumit.dto;

import java.util.List;

import com.sumit.model.DynamicOption;
import com.sumit.model.MainQuestion;
import com.sumit.model.TestSet;


public class TestJsonDTO {
	
public List<DynamicOption> getDynamicOptions() {
		return dynamicOptions;
	}

	public void setDynamicOPtions(List<DynamicOption> dynamicOptions) {
		this.dynamicOptions = dynamicOptions;
	}

private	TestSet testSet;
	
private List<MainQuestion> questionsInTest;

private List<DynamicOption> dynamicOptions;

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
}
	
}
