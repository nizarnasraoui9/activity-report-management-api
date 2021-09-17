package com.emainfo.cra.config;

import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Role;
import com.emainfo.cra.repository.AccountRepository;
import com.emainfo.cra.repository.RoleRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Resource
	private AccountRepository userRepository;

	@Resource
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// Create user roles
		final Role userRole = createRoleIfNotFound(Role.ROLE_USER);
		final Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

		// Create users
		createUserIfNotFound("user", userRole);
		createUserIfNotFound("admin", adminRole);

		alreadySetup = true;
	}

	private  Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role = roleRepository.save(role);
		}
		return role;
	}

	private Account createUserIfNotFound(final String name, final Role role) {
		Account user = userRepository.findByUsername(name);
		if (Objects.isNull(user)) {
			user = new Account();
			user.setUsername(name);
			user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			user.setRoles(Set.of(role));
			user.setEnabled(true);
			user = userRepository.save(user);
		}
		return user;
	}
}
