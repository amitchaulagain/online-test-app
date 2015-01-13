 package com.sumit.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "user_verification")
public class UserVerification implements Serializable {

	public UserVerification() {

	}
	@Id
	@GeneratedValue
	private int id;

	private String verificationToken;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setAutoVerificationToken() {
		
		 verificationToken=UUID.randomUUID().toString();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	 
	 
}
