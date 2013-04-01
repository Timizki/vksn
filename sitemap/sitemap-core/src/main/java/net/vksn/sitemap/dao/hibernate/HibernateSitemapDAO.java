package net.vksn.sitemap.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.sitemap.dao.SitemapDAO;
import net.vksn.sitemap.model.Sitemap;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class HibernateSitemapDAO extends AbstractHibernateDAO<Sitemap> implements SitemapDAO {

	public HibernateSitemapDAO() {
		super(Sitemap.class);
	}

	@Transactional(readOnly = true)
	public Sitemap getRootSitemap() throws EntityNotFoundException {		
		return null;
	}
	
	@Transactional(readOnly = true)
	public Sitemap getDefaultSitemap() {
		SitemapQuery query = new SitemapQuery();
		query.setDefaultSitemap(true);
		Collection<Sitemap> sitemaps = super.getByQuery(query);
		if(sitemaps == null || sitemaps.isEmpty()){
			throw new RuntimeException(new EntityNotFoundException(Sitemap.class, "DEFAULT_SITEMAP"));
		}
		return sitemaps.iterator().next();
	}
	
	@Transactional(readOnly = true)
	public Collection<Sitemap> getAllSitemaps() {
		Query q = new Query();
		return super.getByQuery(q);
	}

	@Transactional(readOnly=true)
	public Sitemap getSitemapByName(String name) throws EntityNotFoundException {
		SitemapQuery query = new SitemapQuery();
		query.setName(name);
		Collection<Sitemap> sitemaps = super.getByQuery(query);
		if(sitemaps.isEmpty()) {
			throw new EntityNotFoundException(Sitemap.class, name);
		}
		else if(sitemaps.size() > 1) {
			throw new EntityNotFoundException(Sitemap.class, name);
		}
		return sitemaps.iterator().next();
	}

}
