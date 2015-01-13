package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class TestSet implements Serializable {

	public TestSet() {
	}

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int fullmark;
	private int passmark;
	String duration;
	Date createDate = new Date();
	Date testDate;
	boolean isNegativeMarking;
	boolean negativeMarkingValue;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "test")
	List<TestRequest> testRequests = new ArrayList<TestRequest>();

	public boolean isNegativeMarkingValue() {
		return negativeMarkingValue;
	}

	public void setNegativeMarkingValue(boolean negativeMarkingValue) {
		this.negativeMarkingValue = negativeMarkingValue;
	}

	public boolean isNegativeMarking() {
		return isNegativeMarking;
	}

	public void setNegativeMarking(boolean isNegativeMarking) {
		this.isNegativeMarking = isNegativeMarking;
	}

	public int getFullmark() {
		return fullmark;
	}

	public void setFullmark(int fullmarks) {
		this.fullmark = fullmarks;
	}

	public int getPassmark() {
		return passmark;
	}

	public void setPassmark(int passmark) {
		this.passmark = passmark;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "test_question", joinColumns = { @JoinColumn(name = "test_id") }, inverseJoinColumns = { @JoinColumn(name = "mainquestion_id") })
	public List<MainQuestion> questionInTest;

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

	public List<MainQuestion> getQuestionInTest() {
		return questionInTest;
	}

	public void setQuestionInTest(List<MainQuestion> questionInTest) {
		this.questionInTest = questionInTest;
	}

	public List<TestRequest> getTestRequests() {
		return testRequests;
	}

	public void setTestRequests(List<TestRequest> testRequests) {
		this.testRequests = testRequests;
	}
}
