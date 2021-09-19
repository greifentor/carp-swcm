package de.ollie.carp.swcm.gui.web;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.carp.corelib.service.SessionIdSO;
import de.ollie.carp.corelib.service.user.UserAuthorizationSO;
import lombok.Data;

/**
 * An object to hold data during the session.
 *
 * @author ollie (18.09.2021)
 */
@Component
@VaadinSessionScope
@Data
public class SessionData {

	private static int counter = 0;

	private UserAuthorizationSO userAuthorization;
	private SessionIdSO id;

	@PostConstruct
	void postConstruct() {
		id = new SessionIdSO("SWCM-Session-" + ++counter);
	}

	public String getUserName() {
		return userAuthorization != null ? userAuthorization.getName() : null;
	}

}