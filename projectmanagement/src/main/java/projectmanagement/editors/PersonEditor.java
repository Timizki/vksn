package projectmanagement.editors;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.apache.log4j.Logger;

import projectmanagement.model.Person;
import projectmanagement.services.UserService;

public class PersonEditor extends PropertyEditorSupport {
	private static final Logger log = Logger.getLogger(PersonEditor.class);
	private UserService service;

	public PersonEditor() {
		super(List.class);
		log.debug("PersonEditor initialized");
	}

	public PersonEditor(Class collectionType) {
		super(collectionType);
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		log.info("<================== CONVERTING ==========================>");
		log.debug("Converting '" + id + "'  to Person object.");
		Person person = null;				
		try {
			person = service.getPerson(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			setValue(person);
		}		
		setValue(person);
	}

	@Override
	  public String getAsText() {
	      if (getValue() == null) {
	         return null;
	      }
	      Person person = (Person) getValue();
	      return "" + person.getId();
	    }

}
