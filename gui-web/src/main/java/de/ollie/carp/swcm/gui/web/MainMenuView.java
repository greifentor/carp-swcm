package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import de.ollie.carp.corelib.event.Event;
import de.ollie.carp.corelib.event.EventManager;
import de.ollie.carp.corelib.event.EventType;
import de.ollie.carp.corelib.service.user.SessionOwner;

/**
 * The main menu view for the Star Wars application.
 *
 * @author ollie (11.08.2021)
 */
public class MainMenuView extends VerticalLayout {

	private static final Logger logger = LogManager.getLogger(MainMenuView.class);

	private final transient EventManager eventManager;
	private final transient SessionOwner sessionOwner;

	public MainMenuView(EventManager eventManager, SessionOwner sessionOwner) {
		super();
		logger.info("created");
		this.eventManager = eventManager;
		this.sessionOwner = sessionOwner;
		add(new Label("TEST"));
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		logger.info("onAttach");
		super.onAttach(attachEvent);
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		logger.info("onDetach");
		super.onDetach(detachEvent);
	}

	private void logout() {
		eventManager.fireEvent(new Event(EventType.LOGGED_OUT));
		VaadinSession.getCurrent().close();
		VaadinService.getCurrentRequest().getWrappedSession().invalidate();
	}

}
