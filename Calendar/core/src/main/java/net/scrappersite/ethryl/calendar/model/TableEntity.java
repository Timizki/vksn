package net.scrappersite.ethryl.calendar.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class TableEntity {
	
	private Integer id;
	private Date created;
	private User createdBy;
	private Date deleted;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getDeleted() {
		return deleted;
	}
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

}
