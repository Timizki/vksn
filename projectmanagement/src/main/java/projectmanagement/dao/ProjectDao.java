package projectmanagement.dao;

import java.util.List;

import projectmanagement.model.Project;
import projectmanagement.queries.ProjectQuery;

public interface ProjectDao {
	
	Project getProject(int id);
	
	List<Project> getProjects(ProjectQuery query);
	
	Project storeProject(Project project);
	
	Project deleteProject(int id);
}
