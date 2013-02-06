package net.vksn.ecm.tiles.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.vksn.ecm.model.TilesDefinition;
import static org.junit.Assert.assertEquals;
import org.apache.tiles.definition.DefinitionsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DatabaseDefinitionsReaderTest {

	private DefinitionsReader definitionsReader;
	
	@Before
	public void setup() {
		this.definitionsReader = new DatabaseDefinitionsReader();
	}
	
	@Test
	public void testRead() {
		List<TilesDefinition> databaseDefinitions = new ArrayList<TilesDefinition>();
		TilesDefinition  def1 = new TilesDefinition();
		def1.setId(1);
		def1.setName("home");
		databaseDefinitions.add(def1);
		Map<String, org.apache.tiles.Definition> definitions = definitionsReader.read(databaseDefinitions);
		assertEquals(databaseDefinitions.size(), definitions.size());
	}
}
