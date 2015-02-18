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
@Table(name = "studentsectionscore")
public class StudentSectionScore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentexaminfo_id")
	private StudentExaminationInfo studentExaminationInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Sections section;
	
	private int score;
	private boolean status;
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
	public StudentExaminationInfo getStudentExaminationInfo() {
		return studentExaminationInfo;
	}
	public void setStudentExaminationInfo(
			StudentExaminationInfo studentExaminationInfo) {
		this.studentExaminationInfo = studentExaminationInfo;
	}
	public Sections getSection() {
		return section;
	}
	public void setSection(Sections section) {
		this.section = section;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}


}
