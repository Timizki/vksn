package net.vksn.bedrock.dao.util.impl;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
