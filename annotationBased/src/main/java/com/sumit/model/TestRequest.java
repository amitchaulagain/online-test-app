package com.sumit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "testrequest")
public class TestRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Date requestedDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "initiatedBy_id")
	private User initiatedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "test_id")
	private TestSet test;
	public TestRequest() {
	}

	private TestRequestStatus testRequestStatus;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "verifiedBy_id")
	private User verifiedBy;

	public User getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(User verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	private String rejectedReason;

	private Date verifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public User getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(User initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public TestRequestStatus getTestRequestStatus() {
		return testRequestStatus;
	}

	public void setTestRequestStatus(TestRequestStatus testRequestStatus) {
		this.testRequestStatus = testRequestStatus;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public TestSet getTest() {
		return test;
	}

	public void setTest(TestSet test) {
		this.test = test;
	}
	
}
