package de.ollie.carp.swcm.core.service.impl;

import java.util.HashSet;
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

	private static final UserAuthorizationSO THE_USER =
			new UserAuthorizationSO(new UserLoginIdSO("KEY"), "TEST-USER", "test", new HashSet<>());

	@Override
	public Optional<UserAuthorizationSO> authenticate(String userName, String password) {
		return Optional.of(THE_USER);
	}

	@Override
	public Optional<UserAuthorizationSO> getUserAuthorization(UserLoginIdSO userLoginId) {
		return Optional.of(THE_USER);
	}

}