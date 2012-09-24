package net.vksn.sitemap.services;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;

public interface SitemapService {
	
	Sitemap getRootSitemap() throws EntityNotFoundException;	
	
	Collection<Sitemap> getAllSitemaps();
	
	Sitemap getSitemap(int id) throws EntityNotFoundException;
	
	Sitemap getSitemapByName(String name) throws EntityNotFoundException;
		 
	void storeSitemap(Sitemap item) throws EntityNotFoundException;
	
	void deleteSitemap(int id)throws EntityNotFoundException;
	
	void undeleteSitemap(int id)throws EntityNotFoundException;
	
	void removeSitemap(int id)throws EntityNotFoundException;
		
}
