package net.vksn.sitemap.dao.hibernate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapItemDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class HibernateSitemapItemDAO extends AbstractHibernateDAO<SitemapItem> implements SitemapItemDAO {

	public HibernateSitemapItemDAO() {
		super(SitemapItem.class);
	}

	@Transactional(readOnly = true)
	public SitemapItem getItemByPath(Sitemap sitemap, String[] path)
			throws EntityNotFoundException {
		
		SitemapItem item = null;
		List<String> pathAsList = Arrays.asList(path);

		for(String slice : pathAsList) {
				item = getItemByName(sitemap, item, slice);
		}
		if(item == null) {
			throw new EntityNotFoundException(SitemapItem.class, path);
		}
		return item;
	}
	

	@Transactional(readOnly = true)
	public SitemapItem getItemByName(Sitemap sitemap, SitemapItem parent, String name)
			throws EntityNotFoundException {
		Criteria criteria = createCriteria();
		criteria.setFetchMode("childrens", FetchMode.JOIN);
		criteria.add(Restrictions.eq("name", name));
		if(sitemap != null && parent == null) {
			criteria.add(Restrictions.eq("sitemap", sitemap));		
		}
		if(parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		}
		else {
			criteria.add(Restrictions.isNull("parent"));
		}
		
		SitemapItem item = (SitemapItem) criteria.uniqueResult();
		if(item == null) {
			throw new EntityNotFoundException(SitemapItem.class, "Name: " + name + " parent: " + parent);
		}
		return item;
	}
	
	@Transactional(readOnly = true)
	public List<SitemapItem> getAllSitemapItems(int sitemapId) {
		Criteria criteria = createCriteria();
		Sitemap sitemap = new Sitemap();
		sitemap.setId(sitemapId);
		criteria.add(Restrictions.eq("sitemap", sitemap));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Set<SitemapItem> getSiblings(SitemapItem item) {
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.asc("pagePosition"));
		if(item.getParent() == null) {
			criteria.add(Restrictions.eq("sitemap", item.getSitemap()));
		}
		else {
			criteria.add(Restrictions.eq("parent", item.getParent()));
		}
		return new HashSet(criteria.list());
	}
}
