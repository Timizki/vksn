package net.vksn.sitemap.model;

import javax.persistence.Entity;

import net.vksn.bedrock.model.Property;

@Entity
public class SitemapProperty extends Property {
	private Sitemap sitemap;

	public Sitemap getSitemap() {
		return sitemap;
	}

	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
}
