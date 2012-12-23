package net.vksn.ecm.tiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsReader;
import org.apache.tiles.definition.RefreshMonitor;
import org.apache.tiles.definition.dao.DefinitionDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseDefinitionDAO implements RefreshMonitor, DefinitionDAO<String> {
	private static final Logger log = Logger.getLogger(DatabaseDefinitionDAO.class); 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DefinitionsReader definitionReader;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void setDefinitionsReader(DefinitionsReader reader) {
		this.definitionReader = reader;
	}
	@Override
	@Transactional(readOnly = true)
	public Definition getDefinition(String name, String customizationKey) {
		Map<String, Definition> definitions = getDefinitions(customizationKey);
		Definition definition = definitions.get(name);
		return definition;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Definition> getDefinitions(String customizationKey) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(net.vksn.ecm.model.Definition.class);
		@SuppressWarnings("unchecked")
		List<Definition> definitionsFromDatabase = criteria.list();
		return definitionReader.read(definitionsFromDatabase);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean refreshRequired() {
		return true;
	}
}
