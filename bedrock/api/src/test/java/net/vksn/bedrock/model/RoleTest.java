package net.vksn.bedrock.model;

import java.util.Calendar;

import  org.junit.Assert;

import org.junit.Test;

public class RoleTest {

	@Test
	public void testHashCode_IsSame() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 21, 9, 00);
		Role thisRole = new Role();
		thisRole.setId(1);
		thisRole.setCreated(cal.getTime());
		thisRole.setAuthority("ROLE_USER");
		
		Role thatRole = new Role();
		thatRole.setId(1);
		thatRole.setCreated(cal.getTime());
		thatRole.setAuthority("ROLE_USER");

		Assert.assertEquals(thisRole.hashCode(), thatRole.hashCode());		
	}
	
	@Test
	public void testEquals_IsSame() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 21, 9, 00);
		Role thisRole = new Role();
		thisRole.setId(1);
		thisRole.setCreated(cal.getTime());
		thisRole.setAuthority("ROLE_USER");
		
		Role thatRole = new Role();
		thatRole.setId(1);
		thatRole.setCreated(cal.getTime());
		thatRole.setAuthority("ROLE_USER");
		
		Assert.assertTrue(thisRole.equals(thatRole));
	}
	
	@Test
	public void testEquals_hasDifferentRoleNames() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 21, 9, 00);
		Role thisRole = new Role();
		thisRole.setId(1);
		thisRole.setCreated(cal.getTime());
		thisRole.setAuthority("ROLE_USER");
		
		Role thatRole = new Role();
		thatRole.setId(1);
		thatRole.setCreated(cal.getTime());
		thatRole.setAuthority("ROLE_ADMIN");
		
		Assert.assertFalse(thisRole.equals(thatRole));
	}
}
