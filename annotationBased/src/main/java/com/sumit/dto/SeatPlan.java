package com.sumit.dto;

import java.io.Serializable;


	 
public class SeatPlan implements Serializable {
	private String seatNumber;
	private String name;
	private String username;
	private String assignedSet;
	private String email;
	 
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAssignedSet() {
		return assignedSet;
	}
	public void setAssignedSet(String assignedSet) {
		this.assignedSet = assignedSet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	 
}
