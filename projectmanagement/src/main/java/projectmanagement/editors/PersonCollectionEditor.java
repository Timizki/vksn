package projectmanagement.editors;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import projectmanagement.model.Person;
import projectmanagement.services.UserService;

public class PersonCollectionEditor extends CustomCollectionEditor {
	private static final Logger log = Logger.getLogger(PersonCollectionEditor.class);
	private UserService service;
	
	public void setService(UserService service) {
		this.service = service;
	}

	public PersonCollectionEditor() {
		super(ArrayList.class);
	}
	
	public PersonCollectionEditor(Class collectionType) {
		super(collectionType);
	}

	@Override
	protected Object convertElement(Object element) {
		log.info("<==================== Converting Persons =====================>");
		log.info("persons id: "+element.toString());
		Integer id = null;
		if(Person.class.equals(element)) {
			Person person = (Person)element;
			id = person.getId();
		}
		else {
			try {
				id = Integer.parseInt(element.toString());
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Person person = (Person)service.getPerson(id);
		return person;
	}

}
