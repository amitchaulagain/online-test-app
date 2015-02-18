package com.sumit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sets")
public class Sets implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	private String name;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestSet getTest() {
		return test;
	}

	public void setTest(TestSet test) {
		this.test = test;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "setsInTestquestion")
	@Fetch(value = FetchMode.SELECT)
	private List<TestQuestions> testQuestionInSet;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "test_id")
	private TestSet test;

	public List<TestQuestions> getTestQuestionInSet() {
		return testQuestionInSet;
	}

	public void setTestQuestionInSet(List<TestQuestions> testQuestionInSet) {
		this.testQuestionInSet = testQuestionInSet;
	}

}
