package projectmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends projectmanagement.model.Entity {
	private String name;

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
