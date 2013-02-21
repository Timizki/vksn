package net.vksn.bedrock.dao.util.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import org.easymock.internal.ArgumentToString;

public class ObjectMatcher implements IArgumentMatcher {

	private Class<?> expectedClass;
	private Map<String, Object> attributeNamesAndExpectedValues;
	private Map<String, Object> notExpectedValues = new HashMap<String, Object>();
	
	public ObjectMatcher(Class<?> expectedClass, Map<String, Object> attributeNamesAndExpectedValues){
		this.expectedClass = expectedClass;
		this.attributeNamesAndExpectedValues = attributeNamesAndExpectedValues;
	}
	
	@Override
	public boolean matches(Object argument) {
		boolean matches = true;
		if(expectedClass.isAssignableFrom(argument.getClass())) {
			notExpectedValues = new HashMap<String, Object>();
			if(attributeNamesAndExpectedValues == null) {
				attributeNamesAndExpectedValues = new HashMap<String, Object>();
			}
			for(String key : attributeNamesAndExpectedValues.keySet()) {
				Object expectedValue = attributeNamesAndExpectedValues.get(key);
				Object value = getValue(argument, key);
				if(expectedValue instanceof IArgumentMatcher ) {
					
					if(((IArgumentMatcher)expectedValue).matches(value)){
						continue;
					}
				}
				else {
					
					if(expectedValue.equals(value)) {
						continue;
					}
				}
				notExpectedValues.put(key, value);
				matches = false;
			}
		}
		return matches;
	}
	
	private Object getValue(Object argument, String key) {
		Field field;
		try {
			field = expectedClass.getDeclaredField(key);
			field.setAccessible(true);
			return field.get(argument);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public static Object eq(Class<?> expectedClass, Map<String, Object> attributeNamesAndExpectedValues){
		EasyMock.reportMatcher(new ObjectMatcher(expectedClass, attributeNamesAndExpectedValues));
		return null;
	}

	@Override
	public void appendTo(StringBuffer buffer) {
		for(String key : notExpectedValues.keySet()) {
			ArgumentToString.appendArgument(notExpectedValues.get(key), buffer);		
		}
	}
	

}
