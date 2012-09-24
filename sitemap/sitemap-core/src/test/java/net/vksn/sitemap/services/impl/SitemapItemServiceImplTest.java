package net.vksn.sitemap.services.impl;

import static org.junit.Assert.assertEquals;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.test.AbstractDatabaseTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(locations = {
		"classpath:net/vksn/sitemap/dataAccessContext.xml",
		"classpath:net/vksn/sitemap/dataSourceContext.xml",
		"classpath:net/vksn/sitemap/transactionContext.xml",
		"classpath:net/vksn/bedrock/dataAccessContext.xml"
		})
public class SitemapItemServiceImplTest extends AbstractDatabaseTestCase {

	@Autowired
	private SitemapItemService service;
	
	@Test
	public void testGetSitemapItemByPath() throws EntityNotFoundException {
		String path = "/parent/document";
		SitemapItem item = service.getItemByPath(path.split("/"));
		SitemapItem actual = null;
		assertSitemapItem(actual, item);
	}

	private void assertSitemapItem(SitemapItem actual, SitemapItem item) {
		assertEquals(actual.getId(), item.getId());
		
	}
	
}
