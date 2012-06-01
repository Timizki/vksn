package net.vksn.gathering.web.validators;

import java.text.SimpleDateFormat;

import net.vksn.gathering.web.controllers.EventForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventFormValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return EventForm.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beginDate", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "required");			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event.name", "required");
	}

}
