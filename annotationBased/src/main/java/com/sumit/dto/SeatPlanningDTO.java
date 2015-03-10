package com.sumit.dto;

import com.sumit.model.Sets;
import com.sumit.model.UserDTO;

public class SeatPlanningDTO {
	private UserDTO student;
	private String seatNumber;
	private Sets set;
	
	public UserDTO getStudent() {
		return student;
	}
	public void setStudent(UserDTO student) {
		this.student = student;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Sets getSet() {
		return set;
	}
	public void setSet(Sets set) {
		this.set = set;
	}

}
