package net.vksn.sitemap.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionOfElements;

@Entity
public class Sitemap extends net.vksn.bedrock.model.Entity {
	private Set<SitemapItem> sitemapItems;
	private String name;
	
	@OneToMany
	public Set<SitemapItem> getSitemapItems() {
		return sitemapItems;
	}
	public void setSitemapItems(Set<SitemapItem> sitemapItems) {
		this.sitemapItems = sitemapItems;
	}
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
