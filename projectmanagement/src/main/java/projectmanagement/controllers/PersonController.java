package projectmanagement.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import projectmanagement.model.Person;
import projectmanagement.services.UserService;
/*
 * Tätä kontrolleria käytetään uuden person luonnin sekä olemassa 
 * olevan muokkauksen käsittelyyn.
 */
public class PersonController extends SimpleFormController {
	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Person person = null;
		//Haetaan personin id
		String id = request.getParameter("id");
		//Jos id:tä ei ole annettu luodaan uusi personi ja sille asetetaan luonti aika
		//Muussa tapauksessa person haetaan kannasta
		if (id == null) {
			person = new Person();
			person.setCreated(new Date());
		}
		else {
			person = service.getPerson(Integer.parseInt(id));			
		}
		return person;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Person person = (Person)command;
		//Tallennetaan person kantaan ja ohjataan oikeaan näkymään.
		service.storePerson(person);
		return new ModelAndView(getSuccessView());		
		
	}
	
}
