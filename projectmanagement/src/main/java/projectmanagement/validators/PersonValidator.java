package projectmanagement.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import projectmanagement.model.Person;

public class PersonValidator implements Validator {
//	Tarkastetaan tuetaanko validoitavaa formia
	public boolean supports(Class clazz) {
		return Person.class.equals(clazz);
	}
//Suoritetaan itese validointi
	public void validate(Object obj, Errors e) {
        Person person = (Person) obj;
        ValidationUtils.rejectIfEmpty(e, "firstname", "person.firstname.empty","Etunimi on pakollinen");
        ValidationUtils.rejectIfEmpty(e, "lastname", "person.lastname.empty","Sukunimi on pakollinen");
        
	}
}
