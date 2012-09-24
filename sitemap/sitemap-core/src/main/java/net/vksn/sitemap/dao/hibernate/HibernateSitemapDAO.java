package net.vksn.sitemap.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.sitemap.dao.SitemapDAO;
import net.vksn.sitemap.model.Sitemap;

import org.springframework.stereotype.Component;

@Component
public class HibernateSitemapDAO extends AbstractHibernateDAO<Sitemap> implements SitemapDAO {

	public HibernateSitemapDAO() {
		super(Sitemap.class);
	}

	public Sitemap getRootSitemap() throws EntityNotFoundException {		
		return null;
	}

	public Collection<Sitemap> getAllSitemaps() {
		Query q = new Query();
		return super.getByQuery(q);
	}

	public Sitemap getSitemapByName(String name) throws EntityNotFoundException {
		SitemapQuery query = new SitemapQuery();
		query.setName(name);
		Collection<Sitemap> sitemaps = super.getByQuery(query);
		if(sitemaps.isEmpty()) {
			throw new EntityNotFoundException();
		}
		else if(sitemaps.size() > 1) {
			throw new EntityNotFoundException();
		}
		return sitemaps.iterator().next();
	}

}
