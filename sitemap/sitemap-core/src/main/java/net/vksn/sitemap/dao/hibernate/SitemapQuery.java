package net.vksn.sitemap.dao.hibernate;

import net.vksn.bedrock.dao.annotations.Match;
import net.vksn.bedrock.query.Query;

public class SitemapQuery extends Query {
	
	private String name;
	private Boolean defaultSitemap;

	@Match(mode="EXACT")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDefaultSitemap() {
		return defaultSitemap;
	}

	public void setDefaultSitemap(Boolean defaultSitemap) {
		this.defaultSitemap = defaultSitemap;
	}
}
