package net.vksn.ecm.editor;

import net.vksn.ecm.model.TilesAttribute;

import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Component;

@Component
public class TilesAttributeEditor extends ClassEditor {
		
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		TilesAttribute attribute = new TilesAttribute();
		attribute.setId(Integer.valueOf(idStr));
		setValue(attribute);
	}
	
	@Override
	public String getAsText() {
		return ((TilesAttribute)getValue()).getId() + "";
	}
	
}