package net.vksn.bedrock.security.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.query.UserQuery;
import net.vksn.bedrock.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AuthenticationService implements UserDetailsService {
	private static final Logger log = Logger.getLogger(AuthenticationService.class);
	private UserService userService; 
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Transactional(readOnly = true )
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		List<net.vksn.bedrock.model.User> users = null;
		UserQuery query = new UserQuery();
		query.setUsername(username);
		net.vksn.bedrock.model.User user = null;
		try {
			users = (List<net.vksn.bedrock.model.User>)userService.getByQuery(query);
			if(users.size() > 1 || users.isEmpty()) {
				throw new EntityNotFoundException();
			}
			user = users.iterator().next();
			log.debug("Found user with username " + username);
		}
		catch(EntityNotFoundException e) {
			throw new UsernameNotFoundException(username + " username not found");
		}
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), true, true, true, true, resolveAuthorities(user));
		return userDetails;
	}

	private GrantedAuthority[] resolveAuthorities(net.vksn.bedrock.model.User user) {
		GrantedAuthority[] authorities = new GrantedAuthority[user.getRoles().size()];
		for(int i = 0; i < user.getRoles().size(); i++) {
			
			Role role = user.getRoles().get(i);
			log.debug("adding role "+ role + " to user " + user.getUsername());
			authorities[i] = new GrantedAuthorityImpl(role.getAuthority());	
		}
		return authorities;
	}
}
