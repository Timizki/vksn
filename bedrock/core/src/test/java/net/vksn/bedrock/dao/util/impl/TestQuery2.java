package net.vksn.bedrock.dao.util.impl;

import java.util.List;

import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.query.Query;

public class TestQuery2 extends Query {

	private Boolean booleanValue;
	private String stringValue;
	private Integer integerValue;
	private List<String> collectionValue;
	private Role entityValue;
	
	public void setBooleanValue(Boolean isBoolean) {
		this.booleanValue = isBoolean;
	}
	
	public Boolean getBooleanValue() {
		return booleanValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public Integer getIntegerValue() {
		return integerValue;
	}
	
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
	
	public List<String> getCollectionValue() {
		return collectionValue;
	}
	
	public void setCollectionValue(List<String> collectionValue) {
		this.collectionValue = collectionValue;
	}
	
	public Role getEntityValue() {
		return entityValue;
	}
	
	public void setEntityValue(Role entityValue) {
		this.entityValue = entityValue;
	}
}
