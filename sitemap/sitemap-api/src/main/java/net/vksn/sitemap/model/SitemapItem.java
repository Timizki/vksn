package net.vksn.sitemap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;

@Entity
public class SitemapItem extends net.vksn.bedrock.model.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7714741063980844013L;
	
	private String name;
	private Sitemap sitemap;
	private SitemapItem parent;
	private Set<SitemapItem> childrens;
	private Collection<SitemapItemProperty> sitemapItemProperties;
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public Sitemap getSitemap() {
		return sitemap;
	}
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	
	@ManyToOne
    @JoinColumn(name="parent_id", insertable=false, updatable=false, nullable=true)
	public SitemapItem getParent() {
		return parent;
	}
	public void setParent(SitemapItem parent) {
		this.parent = parent;
	}
	
	
	@OneToMany(mappedBy="parent")
	public Set<SitemapItem> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<SitemapItem> childrens) {
		this.childrens = childrens;
	}
	
	@OneToMany
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
