package net.vksn.gathering.web.editors;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.services.GenericService;
import net.vksn.gathering.model.Instance;

import org.springframework.beans.propertyeditors.ClassEditor;

public class EntityEditor<T extends Entity> extends ClassEditor {
	private GenericService<T> service;

	public void setInstanceService(GenericService<T> service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String idAsText) throws IllegalArgumentException {
		Entity entity = null;
		try {
			Integer id = Integer.valueOf(idAsText);
			try {
				entity = (T) service.get(id);
			} catch (EntityNotFoundException e) {
				entity = null;
			}
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		setValue(entity);
	}

}
