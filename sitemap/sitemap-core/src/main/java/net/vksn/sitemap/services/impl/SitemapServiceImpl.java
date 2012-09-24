package net.vksn.sitemap.services.impl;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SitemapServiceImpl implements SitemapService {

	@Autowired
	private SitemapDAO dao;
	
	public Sitemap getRootSitemap() throws EntityNotFoundException {
		return null;
	}

	public Sitemap getSitemap(int id) throws EntityNotFoundException {
		return dao.get(id);
	}

	public Collection<Sitemap> getAllSitemaps() {
		return dao.getAllSitemaps();
	}

	public void storeSitemap(Sitemap item) throws EntityNotFoundException {
		dao.store(item);		
	}

	public void deleteSitemap(int id) throws EntityNotFoundException {
		dao.delete(id);		
	}

	public void undeleteSitemap(int id) throws EntityNotFoundException {
		dao.undelete(id);		
	}

	public void removeSitemap(int id) throws EntityNotFoundException {
		dao.remove(id);
	}

	public Sitemap getSitemapByName(String name) throws EntityNotFoundException {
		return dao.getSitemapByName(name);
	}

}
