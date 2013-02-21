package net.vksn.bedrock.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import net.vksn.bedrock.utils.EqualsHelper;

@Entity(name="users")
public class User extends net.vksn.bedrock.model.Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean enabled;
	private List<Group> groups;
	
	@Column(unique = true)
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(columnDefinition="boolean default true", nullable = false)
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToMany
	@JoinTable(name="users_sections")
	public List<Group> getGroups() {
		if(this.groups == null) {
			this.groups = Collections.emptyList();
		}
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof User) {
			User that = (User)object;
			if(!super.equals(that)) {
				return false;
			}
			else if(!EqualsHelper.areEquals(this.username, that.username)) {
				return false;
			}
			else if(!EqualsHelper.areEquals(this.password, that.getPassword())){
				return false;
			}
			else if(!this.enabled == that.getEnabled()) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 43;
		int hashCodeRoot = super.hashCode();
		hashCodeRoot += this.username == null ? 0 : this.username.hashCode();
		hashCodeRoot += this.password == null ? 0 : this.password.hashCode();
		hashCodeRoot += this.enabled ? 1 : 0;
		return 37 * salt + hashCodeRoot;
	}
}
