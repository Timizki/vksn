package net.vksn.sitemap.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.test.AbstractDatabaseTestCase;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(locations = {
		"classpath:net/vksn/sitemap/dataAccessContext.xml",
		"classpath:net/vksn/sitemap/dataSourceContext.xml",
		"classpath:net/vksn/sitemap/transactionContext.xml",
		"classpath:net/vksn/bedrock/dataAccessContext.xml" })
public class SitemapItemServiceImplTest extends AbstractDatabaseTestCase {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SitemapItemService service;

	@Test
	public void testGetSitemapItemByPath_rootItem() throws Exception {
		SitemapItem item = service.getItemByPath(1, new String[0]);
		assertNotNull("Excepted item with name 'parent' but was null",item);
		assertEquals("parent", item.getName());
	}
	
	@Test
	public void testGetSitemapItemByPath_rootItem_DefaultServletPathIsIndex() throws Exception {
		SitemapItem item = service.getItemByPath(1, new String[]{"index"});
		assertNotNull("Excepted item with name 'parent' but was null",item);
		assertEquals("parent", item.getName());
	}
	
	
	@Test
	public void testGetSitemapItemByPath_TwoParts()
			throws EntityNotFoundException {
		SitemapItem item = service.getItemByPath(1, new String[] { "parent",
				"document" });
		SitemapItem expected = new SitemapItem();
		expected.setId(2);
		
		assertSitemapItem(expected, item);
	}

	@Test
	public void testGetSitemapItemByPath_OnePart()
			throws EntityNotFoundException {
		SitemapItem item = service.getItemByPath(1, new String[] { "parent" });
		SitemapItem expected = new SitemapItem();
		expected.setId(1);
		Sitemap sitemap = new Sitemap();
		sitemap.setName("/");
		sitemap.setId(1);
		expected.setSitemap(sitemap);
		assertSitemapItem(expected, item);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testGetSitemapItem_ChildNotFound() throws Exception {
		service.getItemByPath(1, new String[] { "parent", "notFound" });
	}

	@Test(expected = EntityNotFoundException.class)
	public void testGetSitemapItem_ParentNotFound() throws Exception {
		service.getItemByPath(1, new String[] { "notFound", "document" });
	}

	private void assertSitemapItem(SitemapItem expected, SitemapItem item) {
		Assert.notNull(item);
		assertEquals(expected.getId(), item.getId());
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
