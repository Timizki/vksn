package net.vksn.gathering.model;



import javax.persistence.Column;

import net.vksn.bedrock.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity(name="participants")
public class Participant extends Entity {
	private String name;
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}