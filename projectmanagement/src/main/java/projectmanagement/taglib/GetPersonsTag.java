package projectmanagement.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.WebApplicationContextUtils;

import projectmanagement.model.Person;
import projectmanagement.queries.PersonQuery;
import projectmanagement.services.UserService;
/**
 * This class is tag-class that should get all persons from database
 * @author timii
 *
 */
@Configurable
public class GetPersonsTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetPersonsTag.class);
	private UserService service;
	
	public void setService() {
		this.service = (UserService) WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext())
				.getBean("userService");
		log.debug("UserServices class is "+this.service.getClass());
	}

	private String var;

	public String getVar() {
		if (var == null) {
			var = "persons";
		}
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public int doStartTag() throws JspException {
		log.debug("Running method doStartTag in class GetPersonsTag");
		setService();
		if (service == null) {
			throw new JspTagException("Service not set");
		}
		PersonQuery query = new PersonQuery();
		query.setDeleted(false);
		List<Person> persons = service.getPersons(query);
		if (persons == null) {
			throw new JspTagException("No persons were found");
		} else {
			pageContext.setAttribute(var, persons);
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
