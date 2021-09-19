package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonGrid;
import de.ollie.carp.swcm.gui.web.masterdata.SourceBookPageLayout;
import lombok.RequiredArgsConstructor;

/**
 * The main menu view for the Star Wars application.
 *
 * @author ollie (11.08.2021)
 */
@Route(MainMenuView.URL)
@RequiredArgsConstructor
public class MainMenuView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	public static final String URL = "carp-swcm/menu";

	private static final Logger logger = LogManager.getLogger(MainMenuView.class);

	private final SessionData sessionData;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(sessionData, beforeEnterEvent);
		logger.info("created");
		Button buttonSourceBooks = ButtonFactory.createButton("Source Books");
		buttonSourceBooks.addClickListener(event -> switchToSourceBooks());
		buttonSourceBooks.setWidthFull();
		Button buttonLogout = ButtonFactory.createButton("Logout");
		buttonLogout.addClickListener(event -> getUI().ifPresent(ui -> {
			logger.info("user '{}' logged out.", sessionData.getUserAuthorization().getName());
			sessionData.setUserAuthorization(null);
			ui.navigate("carp-swcm");
		}));
		buttonLogout.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonSourceBooks, buttonLogout);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		setWidthFull();
		setMargin(false);
		add(buttonGrid);
		logger.info("main menu view opened for user '{}'.", sessionData.getUserName());
	}

	private void switchToSourceBooks() {
		getUI().ifPresent(ui -> ui.navigate(SourceBookPageLayout.URL));
	}

}