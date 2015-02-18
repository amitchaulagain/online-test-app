package com.sumit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.Utility.AuthenticationUtil;
import com.sumit.model.Role;

@Service("userDetailsService")
// Remove comment below to give it a try
public class MyUserDetailsService implements UserDetailsService , AuthenticationSuccessHandler{
	protected final Log logger = LogFactory.getLog(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	// get user from the database, via Hibernate
	@Autowired
	private UserService userServ;
	User user;
	Role role;
	com.sumit.model.User userToBeAuthorized;
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		 userToBeAuthorized = userServ
				.findUserByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(userToBeAuthorized
				.getUserRole());

		return buildUserForAuthentication(userToBeAuthorized, authorities);

	}

	// Converts .model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.sumit.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

		List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		 handle(request, response, authentication);
		com.sumit.model.User authorizedUser=userServ.findUserByUserName(authentication.getName());
		 AuthenticationUtil.setCurrentUser(authorizedUser);
		 
		
	}

	private void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException{
		final String targetUrl = determineTargetUrl(authentication);
		   if (response.isCommitted()) {
	            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
	            return;
	        }

	        redirectStrategy.sendRedirect(request, response, targetUrl);
	        
	}

	private String determineTargetUrl(Authentication authentication) {
		  boolean isUser = false;
	        boolean isAdmin = false;
	        List<GrantedAuthority> authorities = buildUserAuthority(userToBeAuthorized
					.getUserRole());
	        for (final GrantedAuthority grantedAuthority : authorities) {
	            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
	                isUser = true;
	                break;
	            } else if (grantedAuthority.getAuthority().contains("ROLE_ADMIN")) {
	                isAdmin = true;
	                break;
	            }
	        }

	        if (isUser) {
	            return "/student/welcome";
	        } else if (isAdmin) {
	            return "/admin/dashboard";
	        } else {
	            throw new IllegalStateException();
	        }
	}
	

	
}
