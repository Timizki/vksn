package net.vksn.bedrock.dao.util;

import java.lang.reflect.Method;

import net.vksn.bedrock.query.Query;

public interface Populator {
	String getPropertyName(String methodName);
	
	boolean isGetter(Method method);
	
	boolean isSetter(Method method);
	
	boolean isNullGetProperty(Method method, Query query);
	
}
