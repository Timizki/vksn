package projectmanagement.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.WebApplicationContextUtils;

import projectmanagement.model.Project;
import projectmanagement.queries.ProjectQuery;
import projectmanagement.services.ProjectService;
/**
 * This class is tag-class that should get all projects from database
 * @author timii
 *
 */
@Configurable
public class GetProjectsTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService service;

	public void setService() {
		this.service = (ProjectService) WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext())
				.getBean("projectService");
	}

	private String var;

	public String getVar() {
		if (var == null) {
			var = "projects";
		}
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public int doStartTag() throws JspException {
		setService();
		if (service == null) {
			throw new JspTagException("Service not set");
		}
		List<Project> projects = service.getProjects(new ProjectQuery());
		if (projects == null) {
			throw new JspTagException("No projects were found");
		} else {
			pageContext.setAttribute(var, projects);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		var = null;
		super.release();
	}

}
