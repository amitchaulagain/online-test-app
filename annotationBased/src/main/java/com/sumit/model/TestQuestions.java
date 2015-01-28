package com.sumit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "test_question")
public class TestQuestions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	// @Column(name = "sets_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "set_id")
	@JsonIgnore
	private Sets setsInTestquestion;

	// @Column(name = "question_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private MainQuestion questionInTestquestion;

	// @Column(name = "section_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	@JsonIgnore
	private Sections sectionInTestquestion;

	// @Column(name = "test_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id")
	@JsonIgnore
	private TestSet testInTestquestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sets getSetsInTestquestion() {
		return setsInTestquestion;
	}

	public void setSetsInTestquestion(Sets setsInTestquestion) {
		this.setsInTestquestion = setsInTestquestion;
	}

	public MainQuestion getQuestionInTestquestion() {
		return questionInTestquestion;
	}

	public void setQuestionInTestquestion(MainQuestion questionInTestquestion) {
		this.questionInTestquestion = questionInTestquestion;
	}

	public Sections getSectionInTestquestion() {
		return sectionInTestquestion;
	}

	public void setSectionInTestquestion(Sections sectionInTestquestion) {
		this.sectionInTestquestion = sectionInTestquestion;
	}

	public TestSet getTestInTestquestion() {
		return testInTestquestion;
	}

	public void setTestInTestquestion(TestSet testInTestquestion) {
		this.testInTestquestion = testInTestquestion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
