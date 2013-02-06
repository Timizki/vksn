package net.vksn.ecm.tiles.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vksn.ecm.cache.CacheRefreshable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsReader;
import org.apache.tiles.definition.RefreshMonitor;
import org.apache.tiles.definition.dao.DefinitionDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component(value ="definitionDAO")
public class DatabaseDefinitionDAO implements RefreshMonitor, DefinitionDAO<String>, CacheRefreshable {
	private static final Logger log = LogManager.getLogger(DatabaseDefinitionDAO.class); 
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DefinitionsReader definitionReader;
	private Map<String, Definition> definitions = new HashMap<String, Definition>();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void setDefinitionsReader(DefinitionsReader reader) {
		this.definitionReader = reader;
	}
	
	public void storeDefinition(Definition definition) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(definition);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Definition getDefinition(String name, String customizationKey) {
		log.entry();
		Definition definition = null;
		if(definitions.containsKey(name)) {
			definition = definitions.get(name);
		}
		else {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(net.vksn.ecm.model.TilesDefinition.class);
			criteria.add(Restrictions.eq("name", name));
			
			@SuppressWarnings("unchecked")
			List<Definition> matches = criteria.list();
			if(!matches.isEmpty()) {
				definition = matches.iterator().next();
				definitions.put(name, definition);
			}
		}
		log.exit();
		return definition;
	}

	@Override
	@Transactional(readOnly = true, propagation=Propagation.REQUIRES_NEW)
	public Map<String, Definition> getDefinitions(String customizationKey) {
		log.entry();
		
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(net.vksn.ecm.model.TilesDefinition.class);
			@SuppressWarnings("unchecked")
			List<Definition> definitionsFromDatabase = criteria.list();
			log.exit();
			this.definitions = definitionReader.read(definitionsFromDatabase);
		
		return definitions;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean refreshRequired() {
		return false;
	}


	@Override
	public boolean refreshCache() {
		try {
			definitions.clear();
			return true;
		}
		catch(UnsupportedOperationException e) {
			return false;
		}		
	}
}
