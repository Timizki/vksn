package net.vksn.sitemap.services.impl;

import static org.junit.Assert.assertEquals;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
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
	
//	@Test
//	public void testGetSitemapItemByPath_TwoParts() throws EntityNotFoundException {
//		SitemapItem item = service.getItemByPath(1, new String[]{"parent","document"});
//		SitemapItem expected = new SitemapItem();
//		expected.setId(2);
//		Sitemap sitemap = new Sitemap();
//		sitemap.setName("/");
//		sitemap.setId(1);
//		expected.setSitemap(sitemap);
//		assertSitemapItem(expected, item);
//	}

	@Test
	public void testGetSitemapItemByPath_OnePart() throws EntityNotFoundException {
		SitemapItem item = service.getItemByPath(1, new String[]{"parent"});
		SitemapItem expected = new SitemapItem();
		expected.setId(1);
		Sitemap sitemap = new Sitemap();
		sitemap.setName("/");
		sitemap.setId(1);
		expected.setSitemap(sitemap);
		assertSitemapItem(expected, item);
	}
	
	private void assertSitemapItem(SitemapItem expected, SitemapItem item) {
		assertEquals(expected.getId(), item.getId());
		assertItemsSitemap(item, expected.getSitemap());
	}
	
	private  void assertItemsSitemap(SitemapItem item, Sitemap expectedSitemap) {
		Sitemap actualSitemap = item.getSitemap();
		assertEquals(expectedSitemap.getId(), actualSitemap.getId());
		assertEquals(expectedSitemap.getName(), actualSitemap.getName());
	}	
}
