package net.vksn.ecm.tiles.factory;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.definition.NoSuchDefinitionException;
import org.apache.tiles.definition.dao.DefinitionDAO;
import org.apache.tiles.request.Request;
import org.hibernate.criterion.ExistsSubqueryExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ECMDefinitionsDAOFactory implements DefinitionsFactory {
	public static final Logger log = LogManager.getLogger(ECMDefinitionsDAOFactory.class);
	@Autowired
	private DefinitionDAO<String> definitionDAO;
	
	public void setDefinitionDAO(DefinitionDAO<String> dao) {
		this.definitionDAO = dao;
	}
	
	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		log.entry();
        Definition definition;
        
        definition = definitionDAO.getDefinition(name, null);
        if (definition != null) {
            definition = new Definition(definition);
            String parentDefinitionName = definition.getExtends();
            while (parentDefinitionName != null) {
                Definition parent = definitionDAO.getDefinition(parentDefinitionName, null);
                if (parent == null) {
                    throw new NoSuchDefinitionException("Cannot find definition '"
                            + parentDefinitionName + "' ancestor of '"
                            + definition.getName() + "'");
                }
                definition.inherit(parent);
                parentDefinitionName = parent.getExtends();
            }
        }
        log.exit();
		
        return definition;
	}
}
