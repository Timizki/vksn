package net.vksn.sitemap.dao.hibernate;

import net.vksn.bedrock.query.Query;

public class SitemapItemQuery extends Query {
	
	private String[] path;
	private Integer sitemapId;

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
