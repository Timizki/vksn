package net.vksn.bedrock.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import net.vksn.bedrock.utils.EqualsHelper;

@Entity(name="authorities")
public class Role extends net.vksn.bedrock.model.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	@Column(nullable = false)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}	
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Role) {
			Role that = (Role)object;
			
			if(!EqualsHelper.areEquals(this.authority, that.getAuthority())) {
				return false;
			}
			return super.equals(object);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 34;
		int hashCodeRoot = authority == null ? 0 : authority.hashCode();
		hashCodeRoot += super.hashCode();
		return 37 * salt + hashCodeRoot;
	}
}