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

import projectmanagement.model.Project;
import projectmanagement.model.Task;
import projectmanagement.services.ProjectService;
//Tätä kontorlleria käytetään tehtävän luomiseen ja muokkaamispyyntöjen käsittelyyn.
public class TaskController extends SimpleFormController {
	private static final Logger log = Logger.getLogger(TaskController.class);
	private ProjectService service;
    private Map<Class, PropertyEditor> editors;

	public void setService(ProjectService service) {
		this.service = service;
	}

	public void setEditors(Map<Class, PropertyEditor> editors) {
		this.editors = editors;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id  = request.getParameter("id");
		if(log.isDebugEnabled()) {
			log.debug("Initializing form object - Task");
		}
		//Valitaan luodaanko uusi task vai haetaanko vanha kannasta
		Task task = null;
		if (id == null) {
			task = new Task();
			task.setCreated(new Date());
		}
		else {
			task = this.service.getTask(Integer.parseInt(id));			
		}
		if(log.isDebugEnabled()) {
			log.debug("Initializing completed, returning object");
		}
		return task;
	}
	
	@Override
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        //Käydään annetut propertyeditorit läpi ja rekisteröidään ne yksitellen.
        if (editors != null) {
            for (Class type : editors.keySet()) {
                PropertyEditor editor = editors.get(type);
                binder.registerCustomEditor(type, editor);
            }
        }
    }

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Task task = (Task)command;
		/* Tätä pitäisi parantaa ja antaa hibernaten hoitaa tehtävän lisäys projektille */
		//Lisätään tehtävä projektille
		String projectId = request.getParameter("projectId");
		Project project = service.getProject(Integer.parseInt(projectId));
		//tallennetaan tehtävä
		service.storeTask(task);
		boolean newTask = true;
		for(Task tempTask : project.getTasks()) {
			if(task.getId() == tempTask.getId()) {
				newTask = false;
			}
		}
		if(newTask){
			project.getTasks().add(task);
			//tallennetaan projekti jos projektilla ei ollut ennestään tehtävää
			service.storeProject(project);
		}
		return new ModelAndView(getSuccessView(), "project", project);

	}
}
