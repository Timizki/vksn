package net.vksn.bedrock.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Property {
	private Integer id;
	private String name;
	private String value;
	
	
	@Id
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getValue() {
		return value;
	}

	@Column
	public void setValue(String value) {
		this.value = value;
	}
	
}
