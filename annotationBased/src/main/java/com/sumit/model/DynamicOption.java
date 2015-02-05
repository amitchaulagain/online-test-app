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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "dynamicoption")
public class DynamicOption implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	private String dynamicOptionKey;
	private String dynamicOptionValue;
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
	// @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "test_id")
	@JsonIgnore
	private TestSet testInDynamicOption;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDynamicOptionKey() {
		return dynamicOptionKey;
	}
	public void setDynamicOptionKey(String dynamicOptionKey) {
		this.dynamicOptionKey = dynamicOptionKey;
	}
	public String getDynamicOptionValue() {
		return dynamicOptionValue;
	}
	public void setDynamicOptionValue(String dynamicOptionValue) {
		this.dynamicOptionValue = dynamicOptionValue;
	}
	public TestSet getTestInDynamicOption() {
		return testInDynamicOption;
	}
	public void setTestInDynamicOption(TestSet testInDynamicOption) {
		this.testInDynamicOption = testInDynamicOption;
	}

	
	
}
