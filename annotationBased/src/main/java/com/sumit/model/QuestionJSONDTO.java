package com.sumit.model;

public class QuestionJSONDTO {
	
	String questionType;

	String questionName;
	

	String listOfOptions[];
	String listOfAnswers[];
	
	
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String[] getListOfOptions() {
		return listOfOptions;
	}
	public void setListOfOptions(String[] listOfOptions) {
		this.listOfOptions = listOfOptions;
	}
	public String[] getListOfAnswers() {
		return listOfAnswers;
	}
	public void setListOfAnswers(String[] listOfAnswers) {
		this.listOfAnswers = listOfAnswers;
	}

}
