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
@Table(name = "studentexaminationinfo")
public class StudentExaminationInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;
	public StudentExaminationInfo() {
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examination_id")
	private Examination examination;
	
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public Sets getSet() {
		return set;
	}

	public void setSet(Sets set) {
		this.set = set;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "set_id")
	private Sets set;

	 private String seatNumber;

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	 
}
