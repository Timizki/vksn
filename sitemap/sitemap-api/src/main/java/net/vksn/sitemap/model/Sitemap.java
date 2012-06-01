package net.vksn.sitemap.model;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Sitemap extends net.vksn.bedrock.model.Entity {
	private Set<SitemapItem> sitemapItems;
	private String name;
	
	public Set<SitemapItem> getSitemapItems() {
		return sitemapItems;
	}
	public void setSitemapItems(Set<SitemapItem> sitemapItems) {
		this.sitemapItems = sitemapItems;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
