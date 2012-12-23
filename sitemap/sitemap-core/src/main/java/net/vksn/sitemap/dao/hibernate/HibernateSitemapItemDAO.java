package net.vksn.sitemap.dao.hibernate;

import java.util.Collection;
import java.util.List;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.dao.SitemapItemDAO;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HibernateSitemapItemDAO extends AbstractHibernateDAO<SitemapItem> implements SitemapItemDAO {

	public HibernateSitemapItemDAO() {
		super(SitemapItem.class);
	}

	@Transactional(readOnly = true)
	public SitemapItem getItemByPath(Sitemap sitemap, String[] path)
			throws EntityNotFoundException {
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("sitemap", sitemap));		
		criteria.add(Restrictions.eq("name", path[path.length - 1]));
		Criteria alias = criteria;
		if(path.length > 1) {
			for(int i = path.length -2; i == 0; i--) {
				alias = alias.createCriteria("parent", Criteria.LEFT_JOIN);			
				alias.add(Restrictions.conjunction().add(Restrictions.eq("name", path[i])));
			}
		}
		Collection<SitemapItem> items = criteria.list();
		if(items.isEmpty()) {
			throw new EntityNotFoundException(SitemapItem.class, path[path.length -1]);
		}
		return (SitemapItem)items.iterator().next();
	}
	
	@Transactional(readOnly = true)
	public List<SitemapItem> getAllSitemapItems(int sitemapId) {
		SitemapItemQuery q = new SitemapItemQuery();
		q.setSitemapId(sitemapId);
		return (List<SitemapItem>) super.getByQuery(q);
	}
}
