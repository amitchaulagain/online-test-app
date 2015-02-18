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
@Table(name = "sections")
public class Sections implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sectionInTestquestion")
	@Fetch(value = FetchMode.SELECT)
	private List<TestQuestions> testQuestionInSection;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "test_id")
	private TestSet test;
	
	private int fullmark;
	private int passmark;

	public int getFullmark() {
		return fullmark;
	}

	public void setFullmark(int fullmark) {
		this.fullmark = fullmark;
	}

	public int getPassmark() {
		return passmark;
	}

	public void setPassmark(int passmark) {
		this.passmark = passmark;
	}

	public TestSet getTest() {
		return test;
	}

	public void setTest(TestSet test) {
		this.test = test;
	}

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

	public List<TestQuestions> getTestQuestionInSection() {
		return testQuestionInSection;
	}

	public void setTestQuestionInSection(
			List<TestQuestions> testQuestionInSection) {
		this.testQuestionInSection = testQuestionInSection;
	}

}
