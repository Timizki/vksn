package net.vksn.test.matchers;

import java.lang.reflect.Field;
import java.util.Map;

import net.vksn.bedrock.query.Query;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import org.easymock.internal.ArgumentToString;
//TODO: implement support for multiple attributes
public class QueryMatcher implements IArgumentMatcher {
	private Class<? extends Query> queryClass;
	private String attributeName;
	private Object expectedValue;
	private Object actualValue;
	private boolean attributeFound = true;

	
	public QueryMatcher(Class<? extends Query> queryClass,	String attributeName, Object expectedValue) {
		this.queryClass = queryClass;
		this.attributeName = attributeName;
		this.expectedValue = expectedValue;
	}
	
	public QueryMatcher(Class<? extends Query> queryClass, Map<String, Object> attributesAndExpectedValues) {
		throw new RuntimeException("Not implemented yet");
	}
	
	public static final Query QueryAttributeEq(Class<? extends Query> queryClass, String attributeName, Object expectedValue) {
		EasyMock.reportMatcher(new QueryMatcher(queryClass, attributeName, expectedValue));
		return null;
	}

	@Override
	public boolean matches(Object argument) {		
		if(argument.getClass().equals(queryClass)){
			try {
				if(attributeName == null) {
					return false;
				}
				this.actualValue = getAttributeValue(argument);
			} catch (NoSuchFieldException e) {
				this.attributeFound = false;
				return false;
			}
			if (this.actualValue.equals(expectedValue)) {
				return true;
			}
		}
		return false;
	}

	private Object getAttributeValue(Object argument) throws NoSuchFieldException {
		Object value = null;
		try {
			Field attribute = argument.getClass().getDeclaredField(attributeName);
			attribute.setAccessible(true);
			value = attribute.get(argument);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return value;
	}

	@Override
	public void appendTo(StringBuffer buffer) {
		
		if(attributeFound) {
			buffer.append("Expected value does not match for attributes [");
			buffer.append(attributeName);
			buffer.append("] value. Expecting value ");
			ArgumentToString.appendArgument(expectedValue, buffer);
			buffer.append(" but actual value was ");
			ArgumentToString.appendArgument(actualValue, buffer);
		}
		else {
			buffer.append("Could not found attribute with name [");
			buffer.append(attributeName);
			buffer.append("] in class [");
			buffer.append(queryClass.getName());
			buffer.append("]");
		}
	}
}