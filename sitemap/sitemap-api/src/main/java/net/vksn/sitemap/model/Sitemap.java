package net.vksn.sitemap.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionOfElements;

@Entity
public class Sitemap extends net.vksn.bedrock.model.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<SitemapItem> sitemapItems;
	private String name;
	private boolean defaultSitemap;
	
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
	@Column
	public boolean getDefaultSitemap() {
		return this.defaultSitemap;
	}
	public boolean isDefaultSitemap() {
		return defaultSitemap;  	
	}
	//TODO: parametri booleaniksi ja selvitä miksi siihen syötetään Null
	public void setDefaultSitemap(Boolean isDefault) {
		if(isDefault != null) {
			
			this.defaultSitemap = isDefault;
		}
		this.defaultSitemap = false;
		
	}
	
}
