package org.krams.util;

import java.io.Serializable;

public class ResultDTO implements Serializable {
	
	private int examInfoId;
	private String name;
	 
	private String obtainedScore;
	private String assignedSet;
	private String position;
	private String remarks;
	private String status;
	
	public int getExamInfoId() {
		return examInfoId;
	}
	public void setExamInfoId(int examInfoId) {
		this.examInfoId = examInfoId;
	}
	public String getObtainedScore() {
		return obtainedScore;
	}
	public void setObtainedScore(String obtainedScore) {
		this.obtainedScore = obtainedScore;
	}
	public String getAssignedSet() {
		return assignedSet;
	}
	public void setAssignedSet(String assignedSet) {
		this.assignedSet = assignedSet;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	 
	public void setStatus(  String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 
}
