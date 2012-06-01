package net.vksn.bedrock.query;

import java.util.Date;

import javax.persistence.Id;

import net.vksn.bedrock.model.User;

public class Query {
	private Integer id;
	private Date deletedBefore;
	private Date deletedAfter;
	private Date createBefore;
	private Date createdAfter;
	private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDeletedBefore() {
		return deletedBefore;
	}
	public void setDeletedBefore(Date deletedBefore) {
		this.deletedBefore = deletedBefore;
	}
	public Date getDeletedAfter() {
		return deletedAfter;
	}
	public void setDeletedAfter(Date deletedAfter) {
		this.deletedAfter = deletedAfter;
	}
	public Date getCreateBefore() {
		return createBefore;
	}
	public void setCreateBefore(Date createBefore) {
		this.createBefore = createBefore;
	}
	public Date getCreatedAfter() {
		return createdAfter;
	}
	public void setCreatedAfter(Date createdAfter) {
		this.createdAfter = createdAfter;
	}
}
