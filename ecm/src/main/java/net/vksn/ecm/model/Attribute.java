package net.vksn.ecm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Attribute extends net.vksn.bedrock.model.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String value;
	private Definition definition;
	private Attribute parent;
	
	@Column
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne
	public Definition getDefinition() {
		return definition;
	}
	
	public void setDefinition(Definition definition) {
		this.definition = definition;
	}
	
	@OneToOne
	public Attribute getParent() {
		return parent;
	}
	
	public void setParent(Attribute parent) {
		this.parent = parent;
	}
	
	@Transient
	public boolean getCascadeAttribute() {
		return this.parent != null;
	}
}
