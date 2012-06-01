package projectmanagement.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import projectmanagement.model.Project;

public class ProjectValidator implements Validator {

//	Tarkastetaan tuetaanko validoitavaa formia
	public boolean supports(Class clazz) {
		return Project.class.equals(clazz);
	}
	//Suoritetaan itese validointi
	public void validate(Object obj, Errors e) {
        Project project = (Project) obj;
        ValidationUtils.rejectIfEmpty(e, "name", "project.name.empty","Projektin nimi on pakollinen");
        ValidationUtils.rejectIfEmpty(e, "deadline","project.deadline.empty", "Projektille täytyy määritellä deadline muodossa pp.kk.vvv");
	}
}
