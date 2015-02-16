package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


 
@Entity
@Table(name = "user")
public class User implements Serializable{
	
	@Id
	@GeneratedValue

	private int id;
	
	@Column(name = "username", unique = true, 
			nullable = false, length = 45)
	private String username;
	@Column(name = "password", 
			nullable = false, length = 60)
	private String password;
	@Column(name = "enable", nullable = true)
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "student")
	private List<StudentGroup> students = new ArrayList<StudentGroup>();


	public User() {
	}
 
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
 
	public User(String username, String password, 
		boolean enabled, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}
 
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return this.username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	
	public String getPassword() {
		return this.password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public boolean isEnabled() {
		return this.enabled;
	}
 
	public void setEnabled(boolean enabled  ) {
		this.enabled = enabled;
	}
 
	public List<Role> getUserRole() {
		return this.roles;
	}
 
	public void setUserRole(List<Role> roles) {
		this.roles = roles;
	}
 
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
