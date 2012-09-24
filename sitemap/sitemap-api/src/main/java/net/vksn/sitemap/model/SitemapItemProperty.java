package net.vksn.sitemap.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import net.vksn.bedrock.model.Property;

@Entity
public class SitemapItemProperty extends Property {

	private SitemapItem sitemapItem;

	@Override
	@Column(insertable=false, updatable=false)
	public Integer getId() {
		if (this.sitemapItem != null) {
			return sitemapItem.getId();
		}
		return null;
	}

	@Override
	public void setId(Integer id) {
		if (this.sitemapItem != null) {
			super.setId(sitemapItem.getId());
		}
		super.setId(null);
	}
}
