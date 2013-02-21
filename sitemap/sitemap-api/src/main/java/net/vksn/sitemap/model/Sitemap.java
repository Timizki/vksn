package net.vksn.sitemap.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import net.vksn.bedrock.utils.EqualsHelper;

@Entity
public class Sitemap extends net.vksn.bedrock.model.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<SitemapItem> sitemapItems;
	private String name;
	private boolean defaultSitemap;
	
	@OneToMany(mappedBy="sitemap")
	@OrderBy("pagePosition")
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
	@Column(nullable=false)
	public boolean getDefaultSitemap() {
		return this.defaultSitemap;
	}
	public boolean isDefaultSitemap() {
		return defaultSitemap;  	
	}
	//TODO: parametri booleaniksi ja selvitä miksi siihen syötetään Null
	public void setDefaultSitemap(boolean isDefault) {
		this.defaultSitemap = isDefault;
		
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Sitemap) {
			Sitemap that = (Sitemap)object;
			if(!EqualsHelper.areEquals(name, that.getName())) {
				return false;
			}
			else if(this.defaultSitemap != that.getDefaultSitemap()) {
				return false;
			}			
			return super.equals(object);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 37;
		int hashCodeRoot = super.hashCode();
		hashCodeRoot += defaultSitemap ? 1 : 0;
		hashCodeRoot += this.name == null ? 0 : this.name.hashCode();
		return 37 * salt + hashCodeRoot;
	}
}
