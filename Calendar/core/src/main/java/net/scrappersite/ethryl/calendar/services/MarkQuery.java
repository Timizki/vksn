package net.scrappersite.ethryl.calendar.services;

import net.scrappersite.ethryl.calendar.model.User;

public class MarkQuery {
	private String createdBy;
	private User user;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
