 package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "mainquestion")
public class MainQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	public MainQuestion() {

	}
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class ,property="@id")
	//@JsonManagedReference
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "optionQuestion")
	private List<Options> options = new ArrayList<Options>();
	
	
	QuestionType questionType;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "answerQuestion")
	@Fetch(value = FetchMode.SELECT)
	private List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="questionInTest")
	private List<TestSet> questiontests;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<QuestionAnswer> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}
	public List<TestSet> getQuestiontests() {
		return questiontests;
	}

	public void setQuestiontests(List<TestSet> questiontests) {
		this.questiontests = questiontests;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	 
}
