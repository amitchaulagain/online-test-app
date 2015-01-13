package com.sumit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "userinfo")
public class UserInfo implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "registerdate")
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@Column(name = "gender")
	private boolean isMale;

	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "country")
	private String country;

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date date) {
		this.registerDate = date;
	}

	public void setGender(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean getGender() {
		return this.isMale;
	}

	public void setDob(Date dob) {

		this.dob = dob;

	}

	public Date getDob() {
		return dob;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {

		this.address = address;

	}
}
