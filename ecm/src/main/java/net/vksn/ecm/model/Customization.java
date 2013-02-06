package net.vksn.ecm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Customization {
	private Integer id;
	private Date created;
	private Date deleted;
	private String name;
	private Customization parent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Column
	public Date getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
	
	@Column
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne
	public Customization getParent() {
		return parent;
	}
	
	public void setParent(Customization parent) {
		this.parent = parent;
	}
}
