package net.vksn.bedrock.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import net.vksn.bedrock.AbstractDatabaseTestCase;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:net/vksn/bedrock/dataAccessContext.xml",		
		"classpath:net/vksn/bedrock/dataSourceContext.xml",
		"classpath:net/vksn/bedrock/transactionContext.xml"})
public class AbstractHibernateDAOTest extends AbstractDatabaseTestCase {
	
	@Autowired
	private HibernateUserDAO hibernateUserDAO;
	
	public void setHibernateUserDAO(HibernateUserDAO hibernateUserDAO) {
		this.hibernateUserDAO = hibernateUserDAO;		
	}
	
	@Test
	public void testQuery() {
		
		Query query = new Query();
		query.setId(1);
		query.setCreateBefore(new Date(2010-1900, 9, 9));
		Collection<User> result = hibernateUserDAO.getByQuery(query);
		assertEquals(1, result.size());
	}
}
