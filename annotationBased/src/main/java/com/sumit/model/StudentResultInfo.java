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
@Table(name = "studentresultinfo")
public class StudentResultInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	public StudentResultInfo() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentexaminationinfo_id")
	private StudentExaminationInfo studentExaminationInfo;
	private String position;
	private Boolean status;
	private String remarks;

	private String obtainedScore;
	public StudentExaminationInfo getStudentExaminationInfo() {
		return studentExaminationInfo;
	}
	public void setStudentExaminationInfo(
			StudentExaminationInfo studentExaminationInfo) {
		this.studentExaminationInfo = studentExaminationInfo;
	}
	public String getObtainedScore() {
		return obtainedScore;
	}
	public void setObtainedScore(String obtainedScore) {
		this.obtainedScore = obtainedScore;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean getStatus() {
		return status;
	}


}
