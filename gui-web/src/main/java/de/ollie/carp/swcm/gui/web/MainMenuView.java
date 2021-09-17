package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import de.ollie.carp.corelib.event.Event;
import de.ollie.carp.corelib.event.EventManager;
import de.ollie.carp.corelib.event.EventType;
import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonGrid;
import lombok.RequiredArgsConstructor;

/**
 * The main menu view for the Star Wars application.
 *
 * @author ollie (11.08.2021)
 */
@Route("carp-swcm/menu")
@RequiredArgsConstructor
public class MainMenuView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	private static final Logger logger = LogManager.getLogger(MainMenuView.class);

	private final EventManager eventManager;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		logger.info("created");
		Button buttonSourceBooks = ButtonFactory.createButton("Source Books");
		buttonSourceBooks.addClickListener(event -> switchToSourceBooks());
		buttonSourceBooks.setWidthFull();
		Button buttonTest = ButtonFactory.createButton("Test");
		buttonTest.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("carp-swcm/test")));
		buttonTest.setWidthFull();
		Button buttonLogout = ButtonFactory.createButton("Logout");
		buttonLogout.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("carp-swcm")));
		buttonLogout.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonSourceBooks, buttonTest, buttonLogout);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
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
		getUI().ifPresent(ui -> ui.navigate("carp-swcm/sourcebooks"));
	}

}