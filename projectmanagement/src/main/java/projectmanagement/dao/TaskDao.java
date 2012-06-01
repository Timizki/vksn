package projectmanagement.dao;

import java.util.List;

import projectmanagement.model.Task;
import projectmanagement.queries.TaskQuery;

public interface TaskDao {
	
	Task getTask(int id);
	
	List<Task> getTasks(TaskQuery query);
	
	Task storeTask(Task task);
	
	Task deleteTask(int id);
}
