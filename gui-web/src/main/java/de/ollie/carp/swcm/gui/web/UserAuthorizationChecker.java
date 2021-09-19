package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.router.BeforeEnterEvent;

import lombok.experimental.UtilityClass;

/**
 * A base vertical layout for view with session data.
 *
 * @author ollie (19.09.2021)
 */
@UtilityClass
public class UserAuthorizationChecker {

	private static final Logger logger = LogManager.getLogger(UserAuthorizationChecker.class);

	public void forwardToLoginOnNoUserSetForSession(SessionData sessionData, BeforeEnterEvent beforeEnterEvent) {
		if (sessionData.getUserName() == null) {
			logger.info("no authorization forwarted to login page.");
			beforeEnterEvent.forwardTo(ApplicationStartLayout.URL);
		}
	}

}