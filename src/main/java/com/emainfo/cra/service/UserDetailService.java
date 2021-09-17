package com.emainfo.cra.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Role;
import com.emainfo.cra.repository.AccountRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Resource
	private AccountRepository repo;

	@Resource
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Account user = repo.findByUsername(username);
		if (Objects.nonNull(user)) {
			return buildUserForAuthentication(user, buildSimpleGrantedAuthorities(user.getRoles()));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	private UserDetails buildUserForAuthentication(final Account user, final List<SimpleGrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
}
