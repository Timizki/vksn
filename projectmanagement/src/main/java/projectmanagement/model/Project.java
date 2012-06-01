package projectmanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Project extends projectmanagement.model.Entity {
	private String name;
	private String description;
	private List<Task> tasks;
	private List<Person> actors;
	private Date deadline;
	
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
	
	@Transient
	public Integer getStateMedian() {
		Integer median = 0;
		int count = 0;
		for (Task task : getTasks()) {
			if(task.getDeleted() == null) {
				median = median + task.getState();
				count++;
			}
		}
		if(count == 0) {
			return median;
		}
		else {
			return median / count;
		}
	}
	
//	@JoinTable(
//			name = "project_task",
//			joinColumns = @JoinColumn(name = "project_id"),
//			inverseJoinColumns = @JoinColumn(name ="task_id"))
    @OneToMany
	@JoinTable(name = "project_tasks",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns= @JoinColumn(name="task_id"))
    public List<Task> getTasks() {
		if (tasks == null) {
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@ManyToMany
	@JoinTable(name = "project_person",joinColumns = {@JoinColumn(name = "project_id") }) 
	public List<Person> getActors() {
		if (actors == null) {
			actors = new ArrayList<Person>();
		}
		return actors;
	}
	
	public void setActors(List<Person> actors) {
		this.actors = actors;
	}
	
	@Column
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Transient
	public String getTotalDuration() {
		Integer minDuration = 0;
		Integer hourDuration = 0;
		for(Task task : getTasks()) {
			if(task.getDeleted() == null) {
				if(task.getMinDuration() != null) {
					minDuration = minDuration+task.getMinDuration();
				}
				if(task.getHourDuration() != null) {
					hourDuration = hourDuration+task.getHourDuration();
				}
			}
		}
		
		return hourDuration+"H "+minDuration+"Min";
	}
}
