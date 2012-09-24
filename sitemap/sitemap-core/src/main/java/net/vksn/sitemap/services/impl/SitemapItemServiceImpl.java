package net.vksn.sitemap.services.impl;
import java.util.List;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapItemDAO;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SitemapItemServiceImpl implements SitemapItemService {

	@Autowired
	private SitemapItemDAO dao;
		
	public SitemapItem getItem(int id) throws EntityNotFoundException {	
		return dao.get(id);
	}

	public SitemapItem getItemByPath(String[] path)
			throws EntityNotFoundException {
		
		return dao.getItemByPath(null, path);
	}

	public List<SitemapItem> getAllSitemapItems(int sitemapId) {
		return dao.getAllSitemapItems(sitemapId);
	}

	public void storeSitemapItem(SitemapItem item) throws EntityNotFoundException{
		dao.store(item);
	}

	public void deleteSitemapItem(int id) throws EntityNotFoundException {
		dao.delete(id);
	}

	public void undeleteSitemapItem(int id) throws EntityNotFoundException {
		dao.undelete(id);
	}

	public void removeSitemapItem(int id) throws EntityNotFoundException {
		dao.remove(id);
	}

}
