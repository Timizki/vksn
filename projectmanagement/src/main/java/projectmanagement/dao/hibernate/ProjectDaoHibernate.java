package projectmanagement.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import projectmanagement.dao.ProjectDao;
import projectmanagement.model.Project;
import projectmanagement.queries.ProjectQuery;

public class ProjectDaoHibernate extends HibernateDaoSupport implements ProjectDao {
	private static final Logger log = Logger.getLogger(ProjectDaoHibernate.class);

	public Project deleteProject(int id) {
		Project project = getProject(id);
		getHibernateTemplate().delete(project);
		log.info("Project with id "+id+" was deleted.");
		return project;
	}

	public Project storeProject(Project project) {
		log.info("Storing project with id "+ project.getId());
		getHibernateTemplate().saveOrUpdate(project);
		return project;
	}
	
	@Transactional
	public Project getProject(int id) {
		Project project = null;
		Session session = SessionFactoryUtils.getSession(getHibernateTemplate().getSessionFactory(), true);
		Criteria criteria = session.createCriteria(Project.class);
		criteria.add(Restrictions.idEq(id));
		if(!criteria.list().isEmpty()) {
			project = (Project)criteria.list().get(0);
		}
		else {
			throw new QueryException("Cannot find project with id " + id);
		}
		if(log.isDebugEnabled()) {
			log.debug("returned Project with id " + id +" project has "+ project.getTasks().size()+" tasks and "+ project.getActors().size()+" actors");
		}
		return project;
	}

	public List<Project> getProjects(ProjectQuery query) {
		log.info("Quering projects.");
		Session session = SessionFactoryUtils.getSession(getHibernateTemplate().getSessionFactory(), true); 
		Criteria criteria =  session.createCriteria(Project.class);
		populateProjectQuery(criteria, query);
//		log.info("Found "+criteria.list().size()+" project");
		List<Project> results = criteria.list();
		if (results == null) {
			results = new ArrayList<Project>();
		}
		return results; 
	}
	
	private void populateProjectQuery(Criteria criteria, ProjectQuery query) {
		if (query.getId() != null) {
			criteria.add(Restrictions.idEq(query.getId()));
		}
		if(query.isDeleted() != null) {
			if (query.isDeleted()) {
				criteria.add(Restrictions.isNotNull("deleted"));
			}
			else {
				criteria.add(Restrictions.isNull("deleted"));
			}
		}
		if (query.getStateMedian() != null) {
			criteria.add(Restrictions.eq("stateMedian", query.getStateMedian()));			
		}
		
		if(query.getStartDate() != null) {
			if(query.getEndDate() == null) {
				criteria.add(Restrictions.between("deadline", query.getStartDate(), new Date()));
			}
			else {
				criteria.add(Restrictions.between("deadline", query.getStartDate(), query.getEndDate()));
			}
		}
	}

}
