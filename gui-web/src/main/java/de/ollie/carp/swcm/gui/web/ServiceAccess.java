package de.ollie.carp.swcm.gui.web;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.corelib.localization.ResourceManager;
import de.ollie.carp.corelib.service.user.UserAuthorizationService;
import lombok.Getter;

/**
 * A container for service access.
 *
 * @author ollie (11.08.2021)
 */
@Named
@Getter
public class ServiceAccess {

	@Inject
	private ResourceManager resourceManager;
	@Inject
	private UserAuthorizationService userAuthorizationService;

}