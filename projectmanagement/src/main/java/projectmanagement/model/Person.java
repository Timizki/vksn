package projectmanagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Person extends projectmanagement.model.Entity {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<Role> roles;
	
	@Column
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column
	public String getPassword() {
		return password;
	}
	
	@Column
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Transient
	public String getFullName() {
		return firstname+" "+lastname;
	}

	public void setFullName(String fullName) {
	}

	
	@OneToMany
	public List<Role> getRoles() {
		return roles;
	}
	
	@Column
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
