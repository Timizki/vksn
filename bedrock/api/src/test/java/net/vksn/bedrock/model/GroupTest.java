package net.vksn.bedrock.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GroupTest {

	@Test
	public void testHashCode_hasNoRoles() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		
		Group thatGroup = new Group();
		thatGroup.setId(1);
		thatGroup.setCreated(cal.getTime());
		
		Assert.assertTrue(thisGroup.hashCode()== thatGroup.hashCode());
	}
	
	@Test
	public void testEquals_NewObject() {
		Group thisGroup = new Group();
		Group thatGroup = new Group();
		
		Assert.assertEquals(thisGroup, thatGroup);
	}
	
	@Test
	public void testEquals_hasNoRoles() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		
		Group thatGroup = new Group();
		thatGroup.setId(1);
		thatGroup.setCreated(cal.getTime());
		
		Assert.assertTrue(thisGroup.equals(thatGroup));
	}
	
	@Test
	public void testEquals_hasNoRolesAndDifferendId() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		
		Group thatGroup = new Group();
		thatGroup.setId(2);
		thatGroup.setCreated(cal.getTime());
		
		Assert.assertFalse(thisGroup.equals(thatGroup));
	}
	
	@Test
	public void testEquals_hasNoRolesAndDifferenceInCreated() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		
		Group thatGroup = new Group();
		thatGroup.setId(1);
		thatGroup.setCreated(new Date());
		
		Assert.assertFalse(thisGroup.equals(thatGroup));
	}
	
	@Test
	public void testEquals_hasNoRolesAndDifferenceInDeleted() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		
		Group thatGroup = new Group();
		thatGroup.setId(1);
		thatGroup.setCreated(cal.getTime());
		thatGroup.setDeleted(new Date());
		
		Assert.assertFalse(thisGroup.equals(thatGroup));
	}
	
	@Test
	public void testEquals_hasRoles() {

		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		
		Group thisGroup = new Group();
		thisGroup.setId(1);
		thisGroup.setCreated(cal.getTime());
		thisGroup.setRoles(createRoleList());
		
		Group thatGroup = new Group();
		thatGroup.setId(1);
		thatGroup.setCreated(cal.getTime());
		thatGroup.setRoles(createRoleList());		
		
		Assert.assertTrue(thisGroup.equals(thisGroup));
	}
	
	private List<Role> createRoleList() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 20, 1);
		List<Role> roles = new ArrayList<Role>();
		for(int i = 0; i < 2; i++) {
			Role role = new Role();
			role.setId(i);
			role.setCreated(cal.getTime());
			role.setAuthority("authority"+i);
			roles.add(role);
		}
		return roles;
	}
}
