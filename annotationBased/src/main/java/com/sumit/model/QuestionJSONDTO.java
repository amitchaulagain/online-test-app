package com.sumit.model;

import java.util.List;

public class QuestionJSONDTO {

	private MainQuestion mainquestion;
	private List<Options> options;
	private List<QuestionAnswer> answers;
	private String[] listOfAnswers;
	private String[] listOfOptions;
	private Category category;

	public String[] getListOfAnswers() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
