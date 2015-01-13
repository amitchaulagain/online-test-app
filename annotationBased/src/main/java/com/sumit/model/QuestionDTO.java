package com.sumit.model;

import java.util.List;
import java.util.Map;

/**
 * @author sumit
 *
 */
public class QuestionDTO {
	
	String questionType;
     
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	String listOfQuestionAns[][];
	
	Map<MainQuestion, List<Options>> questionAnsMap;
	
	
Map<MainQuestion, List<Options>> testing;
	
	

	public Map<MainQuestion, List<Options>> getQuestionAnsMap() {
		return questionAnsMap;
	}

	public void setQuestionAnsMap(Map<MainQuestion, List<Options>> questionAnsMap) {
		this.questionAnsMap = questionAnsMap;
	}

	public String[] getListOfAnswers() {
		return listOfAnswers;
	}

	String listOfOptions[];
	String listOfAnswers[];
	int listOfTestQuestion[];
	 

	public int[] getListOfTestQuestion() {
		return listOfTestQuestion;
	}

	public void setListOfTestQuestion(int[] listOfTestQuestion) {
		this.listOfTestQuestion = listOfTestQuestion;
	}

	public String[] getListOfAnswer() {
		return listOfAnswers;
	}

	public void setListOfAnswers(String[] listOfAnswers) {
		this.listOfAnswers = listOfAnswers;
	}

	public String[] getListOfOptions() {
		return listOfOptions;
	}

	public void setListOfOptions(String[] listOfOptions) {
		this.listOfOptions = listOfOptions;
	}

	

	
	
	 
}
