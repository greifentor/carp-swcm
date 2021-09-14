package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import de.ollie.carp.corelib.event.Event;
import de.ollie.carp.corelib.event.EventManager;
import de.ollie.carp.corelib.event.EventType;
import de.ollie.carp.corelib.gui.Disposable;
import de.ollie.carp.corelib.service.user.SessionOwner;
import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonGrid;
import de.ollie.carp.swcm.gui.vaadin.component.ParentLayout;
import de.ollie.carp.swcm.gui.web.masterdata.SourceBookPageLayout;

/**
 * The main menu view for the Star Wars application.
 *
 * @author ollie (11.08.2021)
 */
public class MainMenuView extends VerticalLayout implements ParentLayout {

	private static final Logger logger = LogManager.getLogger(MainMenuView.class);

	private final transient EventManager eventManager;
	private final transient SessionOwner sessionOwner;
	private final transient ServiceAccess serviceAccess;
	private final transient ViewManager parent;

	public MainMenuView(
			EventManager eventManager,
			SessionOwner sessionOwner,
			ServiceAccess serviceAccess,
			ViewManager parent) {
		super();
		logger.info("created");
		this.eventManager = eventManager;
		this.parent = parent;
		this.serviceAccess = serviceAccess;
		this.sessionOwner = sessionOwner;
		Button buttonSourceBooks = ButtonFactory.createButton("Source Books");
		buttonSourceBooks.addClickListener(event -> switchToSourceBooks());
		buttonSourceBooks.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonSourceBooks);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		setMargin(false);
		setWidthFull();
		add(buttonGrid);
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

	private void switchToSourceBooks() {
		Disposable.removeAll(this);
		SourceBookPageLayout layout = new SourceBookPageLayout(serviceAccess, this);
		layout.setMargin(false);
		layout.setWidthFull();
		add(layout);
	}

	public void back() {
		parent.setMainMenuView();
	}

	@Override
	public void updateViewWith(Component component) {
		Disposable.removeAll(this);
		add(component);
	}

}