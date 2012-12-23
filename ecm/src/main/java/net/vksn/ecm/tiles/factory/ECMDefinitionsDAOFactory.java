package net.vksn.ecm.tiles.factory;

import net.vksn.ecm.tiles.dao.DatabaseDefinitionDAO;

import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.definition.dao.DefinitionDAO;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ECMDefinitionsDAOFactory implements DefinitionsFactory {
	
	@Autowired
	private DefinitionDAO<String> definitionDAO;
	
	public void setDefinitionDAO(DefinitionDAO dao) {
		this.definitionDAO = dao;
	}
	
	
	public DefinitionDAO<String> getDefinitionDAO() {
		if(definitionDAO == null) {
			this.definitionDAO = new DatabaseDefinitionDAO();
		}
		return this.definitionDAO;
	}
	
	@Override
	public Definition getDefinition(String name, Request tilesContext) {

		return getDefinitionDAO().getDefinition(name, null);
	}
}
