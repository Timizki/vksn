package net.vksn.bedrock.dao.util.impl;

import java.lang.reflect.Method;

import net.vksn.bedrock.dao.util.Populator;
import net.vksn.bedrock.query.Query;

import org.hibernate.Criteria;

public abstract class AbstractCriteriaPopulator implements Populator {

	abstract Criteria populateCriteria(Criteria criteria, Query query);	
	
	public boolean isGetter(Method method) {
		if(method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isSetter(Method method) {
		if(method.getName().startsWith("set") && method.getParameterTypes().length > 0) {
			return true;
		}
		return false;
	}
	
	public String getPropertyName(String methodName) {
			String propertyName = methodName.substring(3);
			propertyName = Character.toLowerCase(propertyName.charAt(0)) + propertyName.substring(1);
			return propertyName;
	}	
	
	public boolean isNullGetProperty(Method method, Query query) {
		try {
			Object result = method.invoke(query, new Object[0]);
			if(result == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
