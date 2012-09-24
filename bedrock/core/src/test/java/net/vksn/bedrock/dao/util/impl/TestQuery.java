package net.vksn.bedrock.dao.util.impl;

import net.vksn.bedrock.dao.annotations.Match;
import net.vksn.bedrock.query.Query;

public class TestQuery extends Query {

	private String name = "/";
	
	@Match(mode = "EXACT")
	public String getName() {
		return name;
	}

}
