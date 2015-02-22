package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
//	Date testDate;
 
	private TestType type;
 
	boolean isNegativeMarking;
	boolean negativeMarkingValue;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "test")
	List<TestRequest> testRequests = new ArrayList<TestRequest>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "testInTestquestion")
	@Fetch(value = FetchMode.SELECT)
	private List<TestQuestions> testQuestionInTest;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "testInDynamicOption")
	private List<DynamicOption> dynamicOptions = new ArrayList<DynamicOption>();
	
	
	public List<DynamicOption> getDynamicOptions() {
		return dynamicOptions;
	}

	public void setDynamicOptions(List<DynamicOption> dynamicOptions) {
		this.dynamicOptions = dynamicOptions;
	}

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


	public List<TestQuestions> getTestQuestionInTest() {
		return testQuestionInTest;
	}

	public void setTestQuestionInTest(List<TestQuestions> testQuestionInTest) {
		this.testQuestionInTest = testQuestionInTest;
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

	public List<TestRequest> getTestRequests() {
		return testRequests;
	}

	public void setTestRequests(List<TestRequest> testRequests) {
		this.testRequests = testRequests;
	}
	public TestType getType() {
		return type;
	}
	
	public void setType(TestType type) {
		this.type = type;
	}
}
