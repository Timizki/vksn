package projectmanagement.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import projectmanagement.model.Project;
import projectmanagement.queries.ProjectQuery;
import projectmanagement.services.ProjectService;

//Tätä kontrolleria käytetään hakemaan ja palauttamaan kaikki projektit projektilistanäkymää varten.
public class ListProjectsController extends ParameterizableViewController {
	
	private ProjectService service;


	public void setService(ProjectService service) {
		this.service = service;
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProjectQuery query = new ProjectQuery();
		query.setDeleted(false);
		Collection<Project> results = this.service.getProjects(query);
		
		return new ModelAndView(getViewName(), "results", results);
	}	
}
