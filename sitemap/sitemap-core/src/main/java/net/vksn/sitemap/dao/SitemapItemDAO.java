package net.vksn.sitemap.dao;

import java.util.List;
import java.util.Set;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

public interface SitemapItemDAO extends GenericDAO<SitemapItem> {
		
	SitemapItem getItemByPath(Sitemap sitemap, String[] path) throws EntityNotFoundException;
	
	List<SitemapItem> getAllSitemapItems(int sitemapId);
	
	Set<SitemapItem> getSiblings(SitemapItem item);
}
