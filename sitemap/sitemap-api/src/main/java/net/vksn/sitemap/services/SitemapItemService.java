package net.vksn.sitemap.services;

import java.util.List;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.SitemapItem;

public interface SitemapItemService {

	SitemapItem getItem(int id) throws EntityNotFoundException;
	
	SitemapItem getItemByPath(String[] path) throws EntityNotFoundException;
	
	List<SitemapItem> getAllSitemapItems(int sitemapId);
	
	void storeSitemapItem(SitemapItem item) throws EntityNotFoundException;
	
	void deleteSitemapItem(int id)throws EntityNotFoundException;
	
	void undeleteSitemapItem(int id)throws EntityNotFoundException;
	
	void removeSitemapItem(int id)throws EntityNotFoundException;
	
}
