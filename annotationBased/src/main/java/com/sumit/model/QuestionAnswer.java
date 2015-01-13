package com.sumit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "questionanswer")
public class QuestionAnswer implements Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "option_id")
	private int optionId;
	
	 @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mainquestion_id")
	private MainQuestion answerQuestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public MainQuestion getAnswerQuestion() {
		return answerQuestion;
	}

	public void setAnswerQuestion(MainQuestion answerQuestion) {
		this.answerQuestion = answerQuestion;
	}

	public MainQuestion getQuestion() {
		return answerQuestion;
	}

	public void setQuestion(MainQuestion question) {
		this.answerQuestion = question;
	}

	public QuestionAnswer() {
	}


}
