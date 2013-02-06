package org.apache.tiles.factory;

import net.vksn.ecm.tiles.factory.ECMDefinitionsDAOFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.locale.LocaleResolver;
import org.apache.tiles.request.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ECMTilesContainerFactory extends BasicTilesContainerFactory {
	public static final Logger log = LogManager.getLogger(ECMTilesContainerFactory.class);
	@Autowired
	private DefinitionsFactory definitionsFactory;
	
	public void setDefinitionsFactory(DefinitionsFactory definitionsFactory) {
		this.definitionsFactory = definitionsFactory;
	}
	
	@Override
	protected DefinitionsFactory createDefinitionsFactory(
			ApplicationContext applicationContext, LocaleResolver resolver) {
		log.entry();
		if(this.definitionsFactory == null) {
			return new ECMDefinitionsDAOFactory();
		}
		log.exit();
		return definitionsFactory;
	}
}
