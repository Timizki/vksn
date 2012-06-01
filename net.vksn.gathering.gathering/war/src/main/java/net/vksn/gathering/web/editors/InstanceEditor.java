package net.vksn.gathering.web.editors;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.model.Instance;
import net.vksn.gathering.services.InstanceService;

import org.springframework.beans.propertyeditors.ClassEditor;

public class InstanceEditor extends ClassEditor {
	private InstanceService instanceService;

	public void setInstanceService(InstanceService instanceService) {
		this.instanceService = instanceService;
	}
	
	@Override
	public void setAsText(String instanceId) throws IllegalArgumentException {
		Instance instance = null;
		try {
			Integer id = Integer.valueOf(instanceId);
			try {
				instance = instanceService.get(id);
			} catch (EntityNotFoundException e) {
				instance = null;
			}
		}
		catch(NumberFormatException e) {
			instance = new Instance();
		}
		setValue(instance);
	}


}
