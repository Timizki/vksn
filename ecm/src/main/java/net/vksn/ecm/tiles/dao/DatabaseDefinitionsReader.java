package net.vksn.ecm.tiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vksn.ecm.model.TilesDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.definition.DefinitionsReader;

public class DatabaseDefinitionsReader implements DefinitionsReader {
	public static final Logger log = LogManager.getLogger(DatabaseDefinitionDAO.class);
	@Override
	public Map<String, org.apache.tiles.Definition> read(Object source) {
		log.entry();
		@SuppressWarnings("unchecked")
		List<TilesDefinition> definitionsFromDatabase = (List<TilesDefinition>)source;
		Map<String, org.apache.tiles.Definition> definitions = new HashMap<String,  org.apache.tiles.Definition>();
		for(TilesDefinition def : definitionsFromDatabase) {	
			definitions.put(def.getName(), def);
		}
		log.entry();
		return definitions;
	}
	
	

}
