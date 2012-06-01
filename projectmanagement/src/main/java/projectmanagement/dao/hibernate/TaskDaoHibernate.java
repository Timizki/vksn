package projectmanagement.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import projectmanagement.dao.TaskDao;
import projectmanagement.model.Task;
import projectmanagement.queries.TaskQuery;

public class TaskDaoHibernate extends HibernateDaoSupport implements TaskDao{

	/*
	 * (non-Javadoc)
	 * @see projectmanagement.dao.TaskDao#deleteTask(int)
	 */
	public Task deleteTask(int id) {
		Task task = getTask(id);
		getHibernateTemplate().delete(task);
		
		return task;
	}

	/*
	 * (non-Javadoc)
	 * @see projectmanagement.dao.TaskDao#storeTask(projectmanagement.model.Task)
	 * Store <code>Task</code>
	 */
	public Task storeTask(Task task) {
		getHibernateTemplate().saveOrUpdate(task);
		return task;
	}

	/*
	 * (non-Javadoc)
	 * @see projectmanagement.dao.TaskDao#getTask(int)
	 * Method for getting <code>Task</code>. Database query is made with HQL. 
	 * @Param <code>int</code> id
	 * @Return <code>Task</code>
	 */
	public Task getTask(int id) {
		//HQL based query for getting Task
		List<Task> result = getHibernateTemplate().find("select t from Task t Where t.id = ?", id);
		Task task = null;
		if (result.size() > 0) {
			task = result.get(0);
		}
		return task;
	}

	/*
	 * (non-Javadoc)
	 * @see projectmanagement.dao.TaskDao#getTasks(projectmanagement.queries.TaskQuery)
	 * Method for geting all Task, restriction is made by criteria.
	 * @Param <code>TaskQuery</code> query
	 * @Return <code>List<Task></code>
	 */
	public List<Task> getTasks(TaskQuery query) {
		//Criteria based query for getting Tasks
		Session session = SessionFactoryUtils.getSession(getHibernateTemplate().getSessionFactory(), true);
		//Create criteria for task
		Criteria criteria = session.createCriteria(Task.class);
		//Create query restrictions;
		populateTaskQuery(criteria, query);
		return criteria.list();
	}
	
	/*
	 * Method adds criteria restrictions that are setted in query
	 * @Param <code>Criteria</code> criteria, <code>TaskQuery</code> query
	 */
	private void populateTaskQuery(Criteria criteria, TaskQuery query) {
		if (query.isDeleted() != null) {
			criteria.add(Restrictions.eq("deleted", query.isDeleted()));
		}
	}

}
