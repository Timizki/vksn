package net.vksn.bedrock.dao.util.impl;

import net.vksn.bedrock.dao.annotations.Match;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;

public class TestQuery extends Query {

	private Entity entity;
	private String name = "/";
	private String freeText;
	
	@Match(mode = "EXACT")
	public String getName() {
		return name;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	@Match(mode = "EXACT")
	public String getFreeText() {
		return freeText;
	}
	
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}
}
