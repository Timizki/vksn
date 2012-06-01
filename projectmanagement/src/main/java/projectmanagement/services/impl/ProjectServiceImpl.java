package projectmanagement.services.impl;

import java.util.List;

import projectmanagement.dao.ProjectDao;
import projectmanagement.dao.TaskDao;
import projectmanagement.model.Project;
import projectmanagement.model.Task;
import projectmanagement.queries.ProjectQuery;
import projectmanagement.queries.TaskQuery;
import projectmanagement.services.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDao projectDao;
	private TaskDao taskDao;	

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public Project getProject(int id) {
		return projectDao.getProject(id);
	}

	public List<Project> getProjects(ProjectQuery query) {
		return projectDao.getProjects(query);
	}

	public Task getTask(int id) {
		return taskDao.getTask(id);
	}

	public List<Task> getTasks(TaskQuery query) {
		return taskDao.getTasks(query);
	}

	public Task deleteTask(int id) {
		return taskDao.deleteTask(id);
	}

	public Project deteleProject(int id) {
		return projectDao.deleteProject(id);
	}

	public Project storeProject(Project project) {
		return projectDao.storeProject(project);
	}

	public Task storeTask(Task task) {
		return taskDao.storeTask(task);
	}

}
