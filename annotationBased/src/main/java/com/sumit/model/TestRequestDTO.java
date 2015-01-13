package com.sumit.model;

import java.util.Date;

public class TestRequestDTO {
	private int testRequestId;
	private String initiatedBy;
	private String verifiedBy;
	private int testId;
	private Date requestedDate;
	private Date verifiedDate;
	private String rejectedReason;
	private TestRequestStatus status;
	private String testName;
	Date testDate;


	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public int getTestRequestId() {
		return testRequestId;
	}

	public void setTestRequestId(int testRequestId) {
		this.testRequestId = testRequestId;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public TestRequestStatus getStatus() {
		return status;
	}

	public void setStatus(TestRequestStatus status) {
		this.status = status;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public String getTestName() {
		return testName;
	}
	
	public void setTestName(String testName) {
		this.testName = testName;
	}

}
