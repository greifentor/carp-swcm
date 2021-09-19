package de.ollie.carp.swcm.core.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import de.ollie.carp.corelib.service.user.UserAuthorizationSO;
import de.ollie.carp.corelib.service.user.UserAuthorizationService;
import de.ollie.carp.corelib.service.user.UserLoginIdSO;

/**
 * An implementation of the UserAuthorizationService for the Star Wars CARP application.
 *
 * @author ollie (11.08.2021)
 */
@Named
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

	private static final UserAuthorizationSO[] USERS = new UserAuthorizationSO[] {
			new UserAuthorizationSO(new UserLoginIdSO("GM"), "", "", new HashSet<>()),
			new UserAuthorizationSO(new UserLoginIdSO("P1"), "p1", "p1", new HashSet<>()),
			new UserAuthorizationSO(new UserLoginIdSO("P2"), "p2", "p2", new HashSet<>()) };

	@Override
	public Optional<UserAuthorizationSO> authenticate(String userName, String password) {
		return List
				.of(USERS)
				.stream()
				.filter(user -> user.getName().equals(userName) && user.getPassword().equals(password))
				.findFirst();
	}

	@Override
	public Optional<UserAuthorizationSO> getUserAuthorization(UserLoginIdSO userLoginId) {
		return List.of(USERS).stream().filter(user -> user.getUserLoginId().equals(userLoginId)).findFirst();
	}

}