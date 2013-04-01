package net.vksn.sitemap.services;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;

public interface SitemapService {
	
	Sitemap getRootSitemap() throws EntityNotFoundException;	
	
	Sitemap getDefaultSitemap();
	
	Collection<Sitemap> getAllSitemaps();
	
	Sitemap getSitemap(int id, boolean lazy) throws EntityNotFoundException;
	
	Sitemap getSitemapByName(String name) throws EntityNotFoundException;
		 
	void storeSitemap(Sitemap item) throws EntityNotFoundException;
	
	void deleteSitemap(int id)throws EntityNotFoundException;
	
	void undeleteSitemap(int id)throws EntityNotFoundException;
	
	void removeSitemap(int id)throws EntityNotFoundException;
		
}
