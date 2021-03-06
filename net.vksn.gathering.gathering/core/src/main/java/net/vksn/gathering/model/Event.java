package net.vksn.gathering.model;



import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.model.User;

@javax.persistence.Entity(name="events")
public class Event extends Entity {
	private String name;
	private String description;
	private User owner;
	private Collection<Instance> instances;
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	@OneToMany
	@OrderBy("date")
	@JoinTable(name = "events_instances")
	public Collection<Instance> getInstances() {
		if(instances == null) {
			this.instances = new ArrayList<Instance>();
		}
		return instances;
	}
	public void setInstances(Collection<Instance> instances) {
		this.instances = instances;
	}
}
