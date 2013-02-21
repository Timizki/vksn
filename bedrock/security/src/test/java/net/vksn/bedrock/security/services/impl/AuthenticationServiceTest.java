package net.vksn.bedrock.security.services.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.UserQuery;
import net.vksn.bedrock.services.UserService;
import net.vksn.test.matchers.QueryMatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(JUnit4.class)
public class AuthenticationServiceTest {

	

	@Autowired
	private AuthenticationServiceImpl authenticationService;
	private UserService mockUserService;

	@Before
	public void setupTest() {
		this.authenticationService = new AuthenticationServiceImpl();
		this.mockUserService = createMock(UserService.class);
		authenticationService.setUserService(this.mockUserService);
	}

	@Test
	public void testLoadUserByUsername_happyCase() throws Exception {
		User user = new User();
		user.setId(1);
		user.setUsername("timii");
		user.setPassword("kanikasi");

		List<User> users = new ArrayList<User>();
		users.add(user);

		expect(mockUserService.getByQuery(QueryMatcher.QueryAttributeEq(UserQuery.class, "username", "timii")))
				.andReturn(users);
		replay(mockUserService);

		UserDetails userDetails = this.authenticationService
				.loadUserByUsername("timii");
		assertEquals("timii", userDetails.getUsername());
		assertEquals("kanikasi", userDetails.getPassword());

	}
}
