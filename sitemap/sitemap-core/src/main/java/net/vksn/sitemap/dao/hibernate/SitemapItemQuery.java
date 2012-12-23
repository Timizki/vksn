package net.vksn.sitemap.dao.hibernate;

import net.vksn.bedrock.query.Query;
import net.vksn.sitemap.model.Sitemap;

public class SitemapItemQuery extends Query {
	
	private String[] path;
	private Sitemap sitemap;
	private Integer sitemapId;
	
	public Sitemap getSitemap() {
		return sitemap;
	}
	
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	
	public Integer getSitemapId() {
		return sitemapId;
	}
	
	public void setSitemapId(Integer sitemapId) {
		this.sitemapId = sitemapId;
	}

	public String[] getPath() {
		return path;
	}

	public void setPath(String[] path) {
		this.path = path;
	}

}
