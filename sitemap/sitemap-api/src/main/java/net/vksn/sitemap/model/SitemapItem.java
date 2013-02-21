package net.vksn.sitemap.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.vksn.bedrock.utils.EqualsHelper;

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
	private Map<String, String> properties;
	private String decorationName;
	private int pagePosition;
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne
	@JoinColumn(name="sitemap_id", nullable=true)
	public Sitemap getSitemap() {
		return sitemap;
	}
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	
	@ManyToOne
    @JoinColumn(name="parent_id")
	public SitemapItem getParent() {
		return parent;
	}
	public void setParent(SitemapItem parent) {
		this.parent = parent;
	}	
	
	@OneToMany(mappedBy="parent")
	@OrderBy("pagePosition")
	public Set<SitemapItem> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<SitemapItem> childrens) {
		this.childrens = childrens;
	}

	@Column
	public String getDecorationName() {
		return decorationName;
	}
	
	public void setDecorationName(String decorationName) {
		this.decorationName = decorationName;
	}
	
	@ElementCollection( fetch=FetchType.EAGER )
	@MapKeyColumn(name="name")
	@Column(name="value")
	public Map<String, String> getProperties() {
		return properties;
	}
		
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	@Column(nullable=false,updatable=true)
	public int getPagePosition() {
		return this.pagePosition;
	}
	
	public void setPagePosition(int position) {
		this.pagePosition = position;
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
	
	@Transient
	public String getPathAsString() {
		StringBuilder builder = new StringBuilder();
		List<String> path = getPath();
		for(Iterator<String> i = path.iterator(); i.hasNext();) {
			String slice = i.next();
			builder.append(slice);
			if(i.hasNext()) {
				builder.append("/");
			}
		}
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof SitemapItem) {
			SitemapItem that = (SitemapItem)object;
			if(!EqualsHelper.areEquals(this.decorationName, that.getDecorationName())) {
				return false;
			}
			else if(!EqualsHelper.areEquals(this.name, that.getName())) {
				return false;
			}
			else {
				return super.equals(that);
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int salt = 83;
		int hashCodeRoot = super.hashCode();
		hashCodeRoot += this.name == null ? 0 : this.name.hashCode();
		hashCodeRoot += this.decorationName == null ? 0 : this.decorationName.hashCode();
		return 37 * salt + hashCodeRoot;
	}
}
