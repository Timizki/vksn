package net.vksn.sitemap.model;

import org.junit.Assert;

import org.junit.Test;


public class SitemapItemTest {

	@Test
	public void testGetPathAsString() {
		SitemapItem parentItem = new SitemapItem();
		parentItem.setName("parent");
		
		SitemapItem childItem = new SitemapItem();
		childItem.setName("child");
		childItem.setParent(parentItem);
		
		Assert.assertEquals("parent/child", childItem.getPathAsString());
	}
}
