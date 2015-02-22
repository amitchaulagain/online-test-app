package com.sumit.model;

import java.util.List;

public class UserSupportDTO {
	 private User user;
	 private List<Group> groups;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
