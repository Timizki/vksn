package net.vksn.bedrock.security.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import net.vksn.bedrock.model.Group;
import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.query.UserQuery;
import net.vksn.bedrock.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@Component("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
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

	private Collection<GrantedAuthority> resolveAuthorities(net.vksn.bedrock.model.User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Group group : user.getGroups()) {
			for(Role role : group.getRoles()) {
				log.debug("adding role "+ role + " to user " + user.getUsername());
				authorities.add(new GrantedAuthorityImpl(role.getAuthority()));	
			}
		}
		return authorities;
	}
}
