package net.vksn.bedrock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity(name = "users")
public class User extends net.vksn.bedrock.model.Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
//	private Boolean enabled;
	private List<Role> roles;
	
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
	
//	@Column(nullable = false)
//	public boolean getEnabled() {
//		return this.enabled;
//	}
//	
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}

	@ManyToMany
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
