package net.vksn.bedrock.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity(name="sections")
public class Group extends net.vksn.bedrock.model.Entity {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<Role> roles;
	private String description;

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	public List<Role> getRoles() {
		if(this.roles == null) {
			this.roles = Collections.emptyList();
		}
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Group) {
			Group that = (Group) obj;			
			if(that.name != this.name) {
				return false;
			}
			else if(!super.equals(that)) {
				return false;
			}
			if(this.getRoles().size() != that.getRoles().size()) {
				return false;
			}
			for(Role thisRole : this.getRoles()) {
				boolean roleFound = false;
				for(Role thatRole : that.getRoles()){
					if(thisRole.equals(thatRole)) {
						roleFound = true;
						continue;
					}
				}
				if(!roleFound) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 56;
		int hashCodeRoot = this.name == null ? 0 : this.name.hashCode();
		for(Role role : getRoles()) {
			hashCodeRoot += role.hashCode();
		}
		hashCodeRoot += super.hashCode();
		return 37 * salt + hashCodeRoot;
	}
}
