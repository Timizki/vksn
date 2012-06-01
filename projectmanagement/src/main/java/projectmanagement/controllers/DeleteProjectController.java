package projectmanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import projectmanagement.services.ProjectService;
//Tätä kontrolleria käytetään projektien poistamiseen
public class DeleteProjectController extends ParameterizableViewController {
	private static final Logger log = Logger.getLogger(DeleteProjectController.class);
	private ProjectService service;

	public void setService(ProjectService service) {
		this.service = service;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("Handling delete project request");
		//Haetaan projektin id sivupyynnöstä
		String id = request.getParameter("projectId");
		//Poistetaan projekti
		service.deteleProject(Integer.parseInt(id));
		//Lopuksi ohjataan oikeaan näkymään
		return new ModelAndView(getViewName());
	}
}