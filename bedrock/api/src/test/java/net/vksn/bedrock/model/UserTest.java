package net.vksn.bedrock.model;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
	
	@Test
	public void testHashCode() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 21, 9, 00);
		User thisUser = new User();
		thisUser.setId(1);
		thisUser.setCreated(cal.getTime());

		
		User thatUser = new User();
		thatUser.setId(1);
		thatUser.setCreated(cal.getTime());
	

		Assert.assertEquals(thisUser.hashCode(), thatUser.hashCode());		
	}
	
	@Test
	public void testEquals_IsSame() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 1, 15, 21, 9, 00);
		User thisUser = new User();
		thisUser.setId(1);
		thisUser.setCreated(cal.getTime());
		thisUser.setEnabled(true);
		
		User thatUser = new User();
		thatUser.setId(1);
		thatUser.setCreated(cal.getTime());
		thatUser.setEnabled(true);
		Assert.assertTrue(thisUser.equals(thatUser));		
	}

}
