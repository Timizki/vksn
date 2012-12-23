package net.vksn.sitemap.services.impl;
import java.util.List;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapItemDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SitemapItemServiceImpl implements SitemapItemService {

	@Autowired
	private SitemapItemDAO dao;
		
	@Autowired
	private SitemapService sitemapService;
	
	public SitemapItem getItem(int id) throws EntityNotFoundException {	
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public SitemapItem getItemByPath(int id, String[] path)
			throws EntityNotFoundException {
		Sitemap sitemap = sitemapService.getSitemap(id);
		return dao.getItemByPath(sitemap, path);
	}

	@Transactional(readOnly = true)
	public List<SitemapItem> getAllSitemapItems(int sitemapId) {
		return dao.getAllSitemapItems(sitemapId);
	}
	
	@Transactional
	public void storeSitemapItem(SitemapItem item) throws EntityNotFoundException{
		dao.store(item);
	}

	@Transactional
	public void deleteSitemapItem(int id) throws EntityNotFoundException {
		dao.delete(id);
	}

	@Transactional
	public void undeleteSitemapItem(int id) throws EntityNotFoundException {
		dao.undelete(id);
	}

	public void removeSitemapItem(int id) throws EntityNotFoundException {
		dao.remove(id);
	}

}
