package net.vksn.gathering.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.vksn.bedrock.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity(name="instancies")
public class Instance extends Entity {
	private Date date;
	private boolean cancelled;
	private Collection<Participant> participants;
	private Collection<InstanceProperty> properties;
	
	@Column
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "instance_participants")
	public Collection<Participant> getParticipants() {
		if(this.participants == null) {
			this.participants = Collections.EMPTY_LIST;
		}
		return participants;
	}
	public void setParticipants(Collection<Participant> participants) {
		this.participants = participants;
	}
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "instance_properties")
	public Collection<InstanceProperty> getProperties() {
		if(this.properties == null) {
			this.properties = Collections.EMPTY_LIST;
		}
		return this.properties;
	}
	public void setProperties(Collection<InstanceProperty> instanceProperties) {
		this.properties = instanceProperties;
	}
	
	@Transient
	public void addProperty(InstanceProperty property) {
		getProperties().add(property);
	}
	
	public Entity getProperty(String key) {
		if(getProperties().contains(key)) {
			for(InstanceProperty property : getProperties()) {
				if(property.getKey().equals(key)) {
					return property;
				}
			}
		}
		return null;
	}
	
}
