package net.vksn.gathering.model;

import javax.persistence.Column;

import net.vksn.bedrock.model.Entity;

@javax.persistence.Entity
public class InstanceProperty extends Entity {
	private String key;
	private Entity value;
	
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Column
	public String getKey() {
		return this.key;
	}
	
	public void setValue(Entity value) {
		this.value = value;
	}
	
	@Column
	public Entity getValue() {
		return this.value;
	}	
}
