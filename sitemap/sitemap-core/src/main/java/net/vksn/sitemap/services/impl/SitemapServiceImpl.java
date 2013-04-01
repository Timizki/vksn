package net.vksn.sitemap.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.vksn.bedrock.dao.FetchModeEnum;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SitemapServiceImpl implements SitemapService {

	@Autowired
	private SitemapDAO dao;
	
	@Transactional(readOnly = true)
	public Sitemap getRootSitemap() throws EntityNotFoundException {
		return null;
	}

	@Transactional(readOnly = true)
	public Sitemap getSitemap(int id, boolean lazy) throws EntityNotFoundException {
		if(lazy) {
			return dao.get(id, null);
		}
		Map<String, FetchModeEnum>	fetchModes = new HashMap<String, FetchModeEnum>();
		fetchModes.put("sitemapItems", FetchModeEnum.JOIN);
		fetchModes.put("sitemapItems.childrens", FetchModeEnum.JOIN);
		return dao.get(id, fetchModes);
	}
	
	@Transactional(readOnly = true)
	public Collection<Sitemap> getAllSitemaps() {
		return dao.getAllSitemaps();
	}

	@Transactional
	public void storeSitemap(Sitemap item) throws EntityNotFoundException {
		dao.store(item);		
	}

	@Transactional
	public void deleteSitemap(int id) throws EntityNotFoundException {
		dao.delete(id);		
	}

	@Transactional
	public void undeleteSitemap(int id) throws EntityNotFoundException {
		dao.undelete(id);		
	}

	@Transactional
	public void removeSitemap(int id) throws EntityNotFoundException {
		dao.remove(id);
	}

	@Transactional(readOnly = true)
	public Sitemap getSitemapByName(String name) throws EntityNotFoundException {
		return dao.getSitemapByName(name);
	}

	@Override
	public Sitemap getDefaultSitemap() {
		return dao.getDefaultSitemap();
	}
}
