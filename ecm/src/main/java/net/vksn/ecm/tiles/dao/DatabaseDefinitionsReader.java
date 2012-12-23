package net.vksn.ecm.tiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vksn.ecm.model.Definition;

import org.apache.tiles.Attribute;
import org.apache.tiles.definition.DefinitionsReader;

public class DatabaseDefinitionsReader implements DefinitionsReader {

	@Override
	public Map<String, org.apache.tiles.Definition> read(Object source) {
		@SuppressWarnings("unchecked")
		List<Definition> definitionsFromDatabase = (List<Definition>)source;
		Map<String, org.apache.tiles.Definition> definitions = new HashMap<String,  org.apache.tiles.Definition>();
		for(Definition def : definitionsFromDatabase) {
			
			definitions.put(def.getName(), convert(def));
		}
		return definitions;
	}
	
	protected org.apache.tiles.Definition convert(Definition def) {
		org.apache.tiles.Definition definition  = new org.apache.tiles.Definition();
		definition.setName(def.getName());
		Definition parentDefinition = def.getParent();
		if(parentDefinition != null) {
			definition.setExtends(parentDefinition.getName());
		}
		
		definition.setPreparer(def.getPreparer());		
		String templateValue = def.getTemplate();
		
		if(templateValue == null) {
			while(parentDefinition != null) {
				if(parentDefinition.getTemplate() != null) {
					templateValue = parentDefinition.getTemplate();
					break;
				}
				parentDefinition = parentDefinition.getParent();
			}
		}
		definition.setTemplateAttribute(Attribute.createTemplateAttribute(templateValue));
		
		copyAttributes(definition, def);
		
		return definition;
	}
	
	protected void copyAttributes(org.apache.tiles.Definition target, Definition source) {
		Definition sourceDefinition = source;
		while(sourceDefinition != null) {
			for(net.vksn.ecm.model.Attribute attr : sourceDefinition.getAttributes()) {			
				if(target.getAttribute(attr.getName()) == null) {
					Attribute attribute = new Attribute();
					attribute.setRenderer(attr.getType());
					attribute.setValue(attr.getValue());
					target.putAttribute(attr.getName(), attribute, attr.getCascadeAttribute());
				}
			}
			sourceDefinition = sourceDefinition.getParent();
		}
	}

}
