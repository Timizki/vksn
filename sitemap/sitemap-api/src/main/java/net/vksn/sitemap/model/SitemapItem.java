package net.vksn.sitemap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class SitemapItem extends net.vksn.bedrock.model.Entity {
	private String name;
	private Sitemap sitemap;
	private SitemapItem parent;
	private Set<SitemapItem> childrens;
	private Collection<SitemapItemProperty> sitemapItemProperties;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sitemap getSitemap() {
		return sitemap;
	}
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	public SitemapItem getParent() {
		return parent;
	}
	public void setParent(SitemapItem parent) {
		this.parent = parent;
	}
	public Set<SitemapItem> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<SitemapItem> childrens) {
		this.childrens = childrens;
	}
	public Collection<SitemapItemProperty> getSitemapItemProperties() {
		return sitemapItemProperties;
	}
	public void setSitemapItemProperties(
			Collection<SitemapItemProperty> sitemapItemProperties) {
		this.sitemapItemProperties = sitemapItemProperties;
	}
	
	@Transient
	public List<String> getPath() {
		List<String> path = new ArrayList<String>();
		path.add(getName());
		SitemapItem parent = getParent();
		while(parent != null) {
			path.add(parent.getName());
			parent = parent.getParent();
		}
		Collections.reverse(path);
		return path;
	}
}
