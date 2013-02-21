package net.vksn.bedrock.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import net.vksn.bedrock.utils.EqualsHelper;

@MappedSuperclass
public abstract class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date created;
	private Date deleted;
	
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
	
	@Override
	public boolean equals(Object object) {
		if(object != null && object instanceof Entity) {
			Entity that = (Entity)object;
			if(this.id != that.getId())  {
				return false;
			}
			else if(!EqualsHelper.areEquals(this.getCreated(), that.getCreated())) {
				return false;
			}
			else if(!EqualsHelper.areEquals(this.getDeleted(), that.getDeleted())) {
				return false;
			}
			else {
				
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 75;
		int hashCodeRoot = id == null ? 0 : id.hashCode();
		hashCodeRoot += created == null ? 0 : created.hashCode();
		hashCodeRoot += deleted == null ? 0 : deleted.hashCode();
		
		return 37 * salt + hashCodeRoot;
	}
	
}
