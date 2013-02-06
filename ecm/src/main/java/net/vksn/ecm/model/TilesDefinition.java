package net.vksn.ecm.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.apache.tiles.Attribute;

//@Entity
public class TilesDefinition extends org.apache.tiles.Definition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date created;
	private Date deleted;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	
//	@Column
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
//	@Column
	public Date getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
	
	public Map<String, Attribute> getAttributes() {
		if(super.attributes == null) {
			super.addAll(Collections.<String, Attribute> emptyMap());
		}
		return super.attributes;
	}
	
	public void setAttributes(Map<String, Attribute> attributes) {
		super.addAll(attributes);
	}
}
