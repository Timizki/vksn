package net.vksn.sitemap.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import net.vksn.test.AbstractDatabaseTestCase;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.services.SitemapService;

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
public class SitemapServiceImplTest extends AbstractDatabaseTestCase {

	@Autowired
	private SitemapService service;
	
	@Test
	public void testGetSitemapByName_rootSitemap() throws EntityNotFoundException {
		String sitemapName = "/";
		Sitemap sitemap = service.getSitemapByName(sitemapName);
		assertNotNull(sitemap);
		assertEquals(sitemapName, sitemap.getName());
	}
	
	@Test
	public void testGetSitemapByName_OtherThatRootSitemap() throws EntityNotFoundException {
		String sitemapName = "/notRoot";
		Sitemap sitemap = service.getSitemapByName(sitemapName);
		assertNotNull(sitemap);
		assertEquals(sitemapName, sitemap.getName());
	}
}
