package com.sumit.model;

import java.util.List;

public class QuestionJSONDTO {
	
	private MainQuestion mainquestion;
	private List<Options> options;
	private List<QuestionAnswer> answers;
	public MainQuestion getMainquestion() {
		return mainquestion;
	}
	public void setMainquestion(MainQuestion mainquestion) {
		this.mainquestion = mainquestion;
	}
	public List<Options> getOptions() {
		return options;
	}
	public void setOptions(List<Options> options) {
		this.options = options;
	}
	public List<QuestionAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<QuestionAnswer> answers) {
		this.answers = answers;
	}
}
