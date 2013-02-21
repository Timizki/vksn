package net.vksn.test.matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.vksn.bedrock.query.Query;
import net.vksn.bedrock.query.UserQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class QueryMatcherTest {
	

	@Test
	public void testQueryEq_happycase() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "username", "timii");
		assertTrue(matcher.matches(query));
	}
	
	@Test
	public void testQueryEq_valueNotMatch() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "username", "valueNotMatch");
		assertFalse(matcher.matches(query));
	}
	
	@Test 
	public void testQueryEq_nullClass() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(null, "username", "timii");
		assertFalse(matcher.matches(query));
	}
	
	@Test 
	public void testQueryEq_nullAttributeName() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, null, "timii");
		assertFalse(matcher.matches(query));
	}
	
	@Test 
	public void testQueryEq_nullExpectedValue() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "username", null);
		assertFalse(matcher.matches(query));
	}
	
	@Test
	public void testQueryEq_AttributeNotFound() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "notThisAttribute", "timii");
		assertFalse(matcher.matches(query));
	}
	
	@Test
	public void testQueryEq_ClassNotMatch() {
		QueryMatcher matcher = new QueryMatcher(Query.class, "username", "timii");
		assertFalse(matcher.matches("not Query class"));
	}
	
	@Test
	public void testAppendToBuffer_valueNotMatch() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "username", "valueNotMatch");
		matcher.matches(query);
		StringBuffer buffer = new StringBuffer();
		matcher.appendTo(buffer);
		assertEquals("Expected value does not match for attributes [username] value. Expecting value \"valueNotMatch\" but actual value was \"timii\"", buffer.toString());
	}
	
	@Test
	public void testAppendToBuffer_attributeNotFound() {
		UserQuery query = new UserQuery();
		query.setUsername("timii");
		QueryMatcher matcher = new QueryMatcher(UserQuery.class, "wrongAttributeName", "timii");
		matcher.matches(query);
		StringBuffer buffer = new StringBuffer();
		matcher.appendTo(buffer);
		assertEquals("Could not found attribute with name [wrongAttributeName] in class [net.vksn.bedrock.query.UserQuery]", buffer.toString());
	}
	
}
