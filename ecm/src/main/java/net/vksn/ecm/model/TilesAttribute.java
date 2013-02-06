package net.vksn.ecm.model;

import java.util.Date;

//@Entity
public class TilesAttribute extends org.apache.tiles.Attribute {
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
}
