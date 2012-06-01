package projectmanagement.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import projectmanagement.model.Task;

public class TaskValidator implements Validator {

	//Tarkastetaan tuetaanko validoitavaa formia
	public boolean supports(Class clazz) {
		return Task.class.equals(clazz);
	}
	//Suoritetaan itese validointi
	public void validate(Object obj, Errors e) {
        Task task = (Task) obj;
        ValidationUtils.rejectIfEmpty(e, "name", "task.name.empty","Tehtävän nimi on pakollinen");
        if(task.getState() == null) {
        	e.rejectValue("state", "state", "Tilan on pakollinen");
        }
        if (task.getState() < 0 && task.getState() != null) {
            e.rejectValue("state", "state", "Tilan täytyy olla nollan (Ei aloitettu) ja kymmenen (Valmis) väliltä");
        } else if (task.getState() > 10 && task.getState() != null) {
            e.rejectValue("state", "state","Tilan täytyy olla nollan (Ei aloitettu) ja kymmenen (Valmis) väliltä");
        }
	}
}
