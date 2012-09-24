package net.vksn.sitemap.dao;

import java.util.Collection;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;

public interface SitemapDAO  extends GenericDAO<Sitemap> {

	Sitemap getRootSitemap() throws EntityNotFoundException;
	
	Collection<Sitemap> getAllSitemaps();
	
	Sitemap getSitemapByName(String name) throws EntityNotFoundException;
}
