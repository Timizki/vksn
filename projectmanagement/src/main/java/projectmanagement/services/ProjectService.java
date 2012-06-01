package projectmanagement.services;

import java.util.List;

import projectmanagement.model.Project;
import projectmanagement.model.Task;
import projectmanagement.queries.ProjectQuery;
import projectmanagement.queries.TaskQuery;

public interface ProjectService {
	
	//Methods for Project
	Project getProject(int id);
	
	List<Project> getProjects(ProjectQuery query);
    
	Project storeProject(Project project);
    
	Project deteleProject(int id);
	
	//Methods for Task
	Task getTask(int id);
	
	List<Task> getTasks(TaskQuery query);	
	
	Task storeTask(Task task);
	
	Task deleteTask(int id);
}
