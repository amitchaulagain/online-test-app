package org.krams.util;

import java.io.Serializable;

public class ResultDTO implements Serializable {
	
	private int studentResultInfoId;
	private String name;
	private String email;
	private int fullMark;
	private int passMark;
	private int highScore;
	private int obtainedScore;
	private String assignedSet;
	private String position;
	private String remarks;
	private String status;
	private String studentName;
	 
	public int getObtainedScore() {
		return obtainedScore;
	}
	public int getFullMark() {
		return fullMark;
	}
	public void setFullMark(int fullMark) {
		this.fullMark = fullMark;
	}
	public int getPassMark() {
		return passMark;
	}
	public void setPassMark(int passMark) {
		this.passMark = passMark;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public void setObtainedScore(int obtainedScore) {
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
	public int getStudentResultInfoId() {
		return studentResultInfoId;
	}
	public void setStudentResultInfoId(int studentResultInfoId) {
		this.studentResultInfoId = studentResultInfoId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	 
}
