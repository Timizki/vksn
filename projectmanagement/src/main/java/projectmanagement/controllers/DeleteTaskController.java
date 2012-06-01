package projectmanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import projectmanagement.model.Project;
import projectmanagement.model.Task;
import projectmanagement.services.ProjectService;

public class DeleteTaskController extends ParameterizableViewController {
	private static final Logger log = Logger.getLogger(DeleteTaskController.class);
	private ProjectService service;

	public void setService(ProjectService service) {
		this.service = service;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/* Tämä tulisi toteuttaa fiksumin ja antaa tietokannan hoitaa myös tehtävän poisto projektista*/
		//Haetaan projektin ja tehtävän id:t
		String tid = request.getParameter("taskId");
		String pid = request.getParameter("projectId");
		//Haetana projekti ja tehtävä niiden id:n perusteella
		Project project = service.getProject(Integer.parseInt(pid));
		Task task = service.getTask(Integer.parseInt(tid));
		int index = project.getTasks().indexOf(task);
		//Poistetaan tehtävä projektista
		project.getTasks().remove(index);
		//Tallennetaan projekti
		service.storeProject(project);
		//Tallennetaan tehtävä
		service.deleteTask(Integer.parseInt(tid));
		//Ohjataan oikeaseen näkymään.
		return new ModelAndView(getViewName(), "project", project );
	}
}
