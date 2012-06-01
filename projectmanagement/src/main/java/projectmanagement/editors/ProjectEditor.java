package projectmanagement.editors;

import java.beans.PropertyEditorSupport;

import projectmanagement.model.Project;
import projectmanagement.services.ProjectService;

public class ProjectEditor extends PropertyEditorSupport {
	private ProjectService service;
	private String values;

	public void setService(ProjectService service) {
		this.service = service;
	}

	public void setValues(String values) {
		this.values = values;
	}

	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Project project = null;
		if(id != null) {
			project = service.getProject(Integer.parseInt(id));
		}
		if (project != null) {
			setValue(project);
		}
		else {
			throw new IllegalArgumentException("No id was set");
		}
	}	
}
