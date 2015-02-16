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
@Table(name = "role 	"/*, 
	uniqueConstraints = @UniqueConstraint(
		columnNames = { "role", "user_id" })*/)
public class Role implements   Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", 
		unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private String role;


	
	
	
	public Role() {
	}
 
	public Role(User user, String role) {
		this.user = user;
		this.role = role;
	}
	public Role(  String role) {
		this.role = role;
	}
 
	public Integer getId() {
		return this.id;
	}
 
	public void setUserRoleId(Integer id) {
		this.id = id;
	}
 
	
	public User getUser() {
		return this.user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	
	public String getRole() {
		return this.role;
	}
 
	public void setRole(String roles) {
		this.role = roles;
	}
 
}
