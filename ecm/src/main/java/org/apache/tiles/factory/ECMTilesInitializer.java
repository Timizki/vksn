package org.apache.tiles.factory;

import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;

public class ECMTilesInitializer extends AbstractTilesInitializer {
	
	private AbstractTilesContainerFactory tilesContainerFactory;
	
	public void setTilesContainerFactory(
			AbstractTilesContainerFactory tilesContainerFactory) {
		this.tilesContainerFactory = tilesContainerFactory;
	}

	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			ApplicationContext context) {
		if(this.tilesContainerFactory == null) {
			return new ECMTilesContainerFactory();
		}
		return tilesContainerFactory;
	}

}
