package net.vksn.sitemap.dao.hibernate;

import java.util.List;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapItemDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

import org.springframework.stereotype.Component;

@Component
public class HibernateSitemapItemDAO extends AbstractHibernateDAO<SitemapItem> implements SitemapItemDAO {

	public HibernateSitemapItemDAO() {
		super(SitemapItem.class);
	}

	public SitemapItem getItemByPath(Sitemap sitemap, String[] path)
			throws EntityNotFoundException {
		
		SitemapItemQuery q = new SitemapItemQuery();
		q.setPath(path);
		return super.getByQuery(q).iterator().next();
	}

	public List<SitemapItem> getAllSitemapItems(int sitemapId) {
		SitemapItemQuery q = new SitemapItemQuery();
		q.setSitemapId(sitemapId);
		return (List<SitemapItem>) super.getByQuery(q);
	}


}
