package projectmanagement.controllers;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import projectmanagement.editors.PersonCollectionEditor;
import projectmanagement.model.Project;
import projectmanagement.services.ProjectService;
/*
 * Tätä kontrolleria käytetään uuden projektin luonnin ja olemassa olevan 
 * projektin muokka pyyntöjen käsittelyyn.
 */
public class ProjectController extends SimpleFormController {
	private static final Logger log = Logger.getLogger(ProjectController.class);
	private ProjectService projectService;
	private Map<Class, PropertyEditor> editors;

	public void setProjectService(ProjectService service) {
		this.projectService = service;
	}

	public void setEditors(Map<Class, PropertyEditor> editors) {
		this.editors = editors;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Project project = null;
		//Haetaan projektin id
		String id = request.getParameter("id");
		//Tarkistetaan onko requestiin asetettu id:tä
		//Jos ei ole luodaan uusi projekti muussa tapauksessa projekti haetaan kannasta
		if(id == null) {
				project = new Project();		
				project.setCreated(new Date());
		}
		else {
			project = projectService.getProject(Integer.parseInt(id)); 
		}
		
		return project;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		//Käydään annetut propertyeditorit läpi ja rekisteröidään ne yksitellen.
        if (editors != null) {
            for (Class type : editors.keySet()) {
                PropertyEditor editor = editors.get(type);
                if(editor instanceof PersonCollectionEditor) {
                	binder.registerCustomEditor(type , "actors", editor);
                }
                else {
                	binder.registerCustomEditor(type, editor);
                }
            }
        }
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		//Tallennetaan projekti ja ohjataan oikeaan näkymään.
		Project project = projectService.storeProject((Project)command);
		return new ModelAndView(getSuccessView(), "project", project);
	}
}
