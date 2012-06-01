package net.scrappersite.ethryl.calendar.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Mark extends net.scrappersite.ethryl.calendar.model.TableEntity {
	
	private Date date;
	private Integer duration;
	private String type;
	private String location;
	private User user;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
