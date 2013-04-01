package net.vksn.sitemap.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Set;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapService;
import net.vksn.test.AbstractDatabaseTestCase;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.Assert;
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
		"classpath:net/vksn/bedrock/dataAccessContext.xml" })
public class SitemapServiceImplTest extends AbstractDatabaseTestCase {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SitemapService service;

	@Test
	public void testGetSitemapByName_rootSitemap()
			throws EntityNotFoundException {
		String sitemapName = "/";
		Sitemap sitemap = service.getSitemapByName(sitemapName);
		assertNotNull(sitemap);
		assertEquals(sitemapName, sitemap.getName());
	}

	@Test
	public void testGetSitemapByName_OtherThatRootSitemap()
			throws EntityNotFoundException {
		String sitemapName = "/notRoot";
		Sitemap sitemap = service.getSitemapByName(sitemapName);
		assertNotNull(sitemap);
		assertEquals(sitemapName, sitemap.getName());
	}

	@Test
	public void testGetDefaultSitemap() throws Exception {
		Sitemap sitemap = service.getDefaultSitemap();
		Assert.assertTrue(sitemap.getDefaultSitemap());
	}

	@Test
	public void testGetSitemap_ById() throws Exception {
		Sitemap sitemap = service.getSitemap(1, true);
		Assert.assertTrue( sitemap.getId() == 1);
	}
	
	@Test
	public void testGetNotLazySitemap_ById() throws Exception {
		Sitemap sitemap = service.getSitemap(1, false);
		Set<SitemapItem> items = sitemap.getSitemapItems();
		items.iterator().next();
		Assert.assertTrue( sitemap.getId() == 1);
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws SQLException {
		try {
			return new DatabaseConnection(sessionFactory.getCurrentSession()
					.connection());
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		}
		return null;
	}
}
