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
@Table(name = "examination")
public class Examination implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;
	public Examination() {
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id")
	private TestSet test;

	public TestSet getTest() {
		return test;
	}

	public void setTest(TestSet test) {
		this.test = test;
	}
	
	 
}
