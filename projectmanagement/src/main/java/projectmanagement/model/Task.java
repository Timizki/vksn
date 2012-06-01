package projectmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/*
 * Annotated entity class Task
 * Represent's project's task
 */
@Entity
public class Task extends projectmanagement.model.Entity {
	private Person actor;
	private String name;
	private String description;
	private int state;
	private int minDuration;
	private int hourDuration;

	@ManyToOne
	public Person getActor() {
		return actor;
	}

	public void setActor(Person actor) {
		this.actor = actor;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column
	public Integer getMinDuration() {
		return minDuration;
	}

	public void setMinDuration(Integer minDuration) {
		if(minDuration > 59) {
			int hours = minDuration / 60;
			setHourDuration(getHourDuration()+hours);
			minDuration = minDuration % 60;
		}
		this.minDuration = minDuration;
	}

	@Column
	public Integer getHourDuration() {
		return hourDuration;
	}

	public void setHourDuration(Integer hourDuration) {
		this.hourDuration = hourDuration;
	}
	
	@Transient
	public String getStringDuration() {
		String duration = new String();
		if(getHourDuration() != 0 && getMinDuration() != 0) {
			duration = getHourDuration()+ "H "+getMinDuration()+"Min";
		}
		else if(getHourDuration() == 0) {
			duration = getMinDuration()+"Min";
		}
		else if(getMinDuration() == 0) {
			duration = getHourDuration()+ "H";
		}
		else {
			duration = "-";			
		}
		
		return duration;
		
	}

}