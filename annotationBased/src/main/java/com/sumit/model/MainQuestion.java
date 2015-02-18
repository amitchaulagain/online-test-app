 package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	OptionType optionType;
	public OptionType getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questionInTestquestion")
	@Fetch(value = FetchMode.SELECT)
	private List<TestQuestions> testQuestionInQuestion;
	
	public List<TestQuestions> getTestQuestionInQuestion() {
		return testQuestionInQuestion;
	}

	public void setTestQuestionInQuestion(List<TestQuestions> testQuestionInQuestion) {
		this.testQuestionInQuestion = testQuestionInQuestion;
	}
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class ,property="@id")
	//@JsonManagedReference
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "optionQuestion")
	private List<Options> options = new ArrayList<Options>();
	
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private  Category category;

	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "answerQuestion")
	@Fetch(value = FetchMode.SELECT)
	private List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
	
	/*@JsonIgnore
	@ManyToMany(mappedBy="questionInTest")
	private List<TestQuestions> questiontests;

*/
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
	/*public List<TestQuestions> getQuestiontests() {
		return questiontests;
	}

	public void setQuestiontests(List<TestQuestions> questiontests) {
		this.questiontests = questiontests;
	}*/

	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	 
}
