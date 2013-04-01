package net.vksn.bedrock.dao.util.impl;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.model.Role;

import org.easymock.EasyMock;
import org.easymock.internal.matchers.ArrayEquals;
import org.easymock.internal.matchers.Equals;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(locations = {
		"classpath:net/vksn/bedrock/transactionContext.xml",
		"classpath:net/vksn/bedrock/dataSourceContext.xml",
		"classpath:net/vksn/bedrock/dataAccessContext.xml"
		})
public class CriteriaPopulatorTest {
	
	@Autowired
	private CriteriaPopulator populator;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Test
	public void testBooleanTypePopulation_valueTrue() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "booleanValue");
		expected.put("value", true);
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setBooleanValue(true);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testBooleanTypePopulation_valueFalse() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "booleanValue");
		expected.put("value", false);
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setBooleanValue(false);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testBooleanTypePopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "booleanValue");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setBooleanValue(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testIdPopulation_valueOne() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "id");
		expected.put("value", 1);
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setId(1);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testIdPopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "id");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setId(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	
	@Test
	public void testStringTypePopulation_valueString() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "stringValue");
		expected.put("value", "String");
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setStringValue("String");
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testStringTypePopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "stringValue");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setId(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testIntegerTypePopulation_valueTwo() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "integerValue");
		expected.put("value", 2);
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setIntegerValue(2);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testIntegerTypePopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "integerValue");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setIntegerValue(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testCollectionTypePopulation_valuePopulatedList() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		List<String> listOfString = new ArrayList<String>();
		listOfString.add("String value");
		
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "collectionValue");
		expected.put("value", new Equals(listOfString));
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setCollectionValue(listOfString);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testCollectionTypePopulation_valueEmptyList() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		List<String> listOfStrings = new ArrayList<String>();
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "collectionValue");
		expected.put("values", new ArrayEquals(listOfStrings));
		
		EasyMock.expect(mockCiCriteria.add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setCollectionValue(listOfStrings);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testCollectionTypePopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "collectionValue");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setCollectionValue(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	//TODO: fix test
	public void testEntityTypePopulation_valueRole() throws Exception {
		Role expectedRole = new Role();
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "entityValue");
		expected.put("values", expectedRole);
		
		EasyMock.expect(mockCiCriteria.add((Criterion)ObjectMatcher.eq(Conjunction.class, new HashMap<String,Object>())).add((Criterion) ObjectMatcher.eq(SimpleExpression.class, expected))).andReturn(mockCiCriteria);
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setEntityValue(expectedRole);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testEntityTypePopulation_valueNull() throws Exception {
		Criteria mockCiCriteria = EasyMock.createStrictMock(Criteria.class);
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("propertyName", "entityValue");
		expected.put("value", null);
		
		EasyMock.replay(mockCiCriteria);
		TestQuery2 query = new TestQuery2();
		query.setEntityValue(null);
		populator.populateCriteria(mockCiCriteria, query);
		EasyMock.verify(mockCiCriteria);
	}
	
	@Test
	public void testPopulateString() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Criteria criteria = new CriteriaImpl("Sitemap", (SessionImplementor) sessionFactory.getCurrentSession());
		TestQuery query = new TestQuery();
		Method[] methods = query.getClass().getMethods();
		Method method = null;
		for(Method m : methods) {
			if(m.getName().equals("getName")) {
				method = m; 
				break;
			}
		}
		populator.populateString(method, "name", query, criteria);
		assertEquals("CriteriaImpl(Sitemap:this[][name ilike /])", criteria.toString());
		
	}
	
	@Test
	public void testPopulateString_twoPartPropertyName() throws Exception {
		String property = populator.getPropertyName("getFreeText");
		assertEquals("freeText", property);
	}
	
	@Test
	public void testJoinPopulation() {
		Criteria criteria = new CriteriaImpl("Entity", (SessionImplementor) sessionFactory.getCurrentSession());
		TestQuery query = new TestQuery();
		query.setEntity(new TestEntity(1));
		populator.populateCriteria(criteria, query);
		
		assertEquals("CriteriaImpl(Entity:this[][name ilike /, (Entity_id=1)])", criteria.toString());

	}
	
	class TestEntity extends Entity {
		public TestEntity(Integer id) {
			setId(id);
		}
	}
}
