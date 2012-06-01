package projectmanagement.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import projectmanagement.model.Project;
import projectmanagement.services.ProjectService;
/**
 * This class is tag-class that returns project by given id
 * @author timii
 *
 */
public class GetProjectTag extends TagSupport {

	private ProjectService service;
	private String var;
	private String id;
	
	private void setService() {
		service = (ProjectService)WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()).getBean("projectService");		
	}
	
	public String getVar() {
		if (var == null || "".equals(var)) {
			var = "project";
		}
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int doStartTag() throws JspException {
		setService();
		Project project = null;
		if (id != null || !"".equals(id)) {
			project = service.getProject(Integer.parseInt(id));
			if(project == null) {
				throw new JspException("No project found with id " + id);
			}
			else {
				pageContext.setAttribute(var, project);
			}
		}
		
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		id = null;
		var = null;
		super.release();		
	}
	

}
