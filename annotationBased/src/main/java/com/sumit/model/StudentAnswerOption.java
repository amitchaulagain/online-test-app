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

@Entity
@Table(name = "studentansweroption")
public class StudentAnswerOption implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;
	public StudentAnswerOption() {
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentexaminationinfo_id")
	private StudentExaminationInfo studentExaminationInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private Options option;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private MainQuestion question;

	 private String givenAnswer;

	public StudentExaminationInfo getStudentExaminationInfo() {
		return studentExaminationInfo;
	}

	public void setStudentExaminationInfo(
			StudentExaminationInfo studentExaminationInfo) {
		this.studentExaminationInfo = studentExaminationInfo;
	}

	public Options getOption() {
		return option;
	}

	public void setOption(Options option) {
		this.option = option;
	}

	public MainQuestion getQuestion() {
		return question;
	}

	public void setQuestion(MainQuestion question) {
		this.question = question;
	}

	public String getGivenAnswer() {
		return givenAnswer;
	}

	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}
	 
}
