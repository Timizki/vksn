package net.vksn.ecm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Definition extends net.vksn.bedrock.model.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Definition parent;
	private String preparer;
	private List<Attribute> attributes;
	private String template;
	
	@Column
	@NotNull
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne
	public Definition getParent() {
		return parent;
	}
	
	public void setParent(Definition parent) {
		this.parent = parent;
	}
	
	@Column
	public String getPreparer() {
		return preparer;
	}
	
	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}
	
	@Column
	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}

	@OneToMany
	public List<Attribute> getAttributes() {
		if(this.attributes == null) {
			this.attributes = new ArrayList<Attribute>();
		}
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
}
