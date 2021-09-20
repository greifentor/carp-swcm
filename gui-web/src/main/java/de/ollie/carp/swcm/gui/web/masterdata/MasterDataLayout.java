package de.ollie.carp.swcm.gui.web.masterdata;

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
import de.ollie.carp.swcm.gui.web.HeaderLayout;
import de.ollie.carp.swcm.gui.web.HeaderLayout.HeaderLayoutMode;
import de.ollie.carp.swcm.gui.web.MainMenuView;
import de.ollie.carp.swcm.gui.web.SessionData;
import de.ollie.carp.swcm.gui.web.UserAuthorizationChecker;
import lombok.RequiredArgsConstructor;

/**
 * A layout with buttons to select a master data page
 *
 * @author ollie (20.09.2021)
 */
@Route(MasterDataLayout.URL)
@RequiredArgsConstructor
public class MasterDataLayout extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	public static final String URL = "carp-swcm/masterdata/menu";

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
		Button buttonMasterData = ButtonFactory.createButton("Sourcebooks");
		buttonMasterData.addClickListener(event -> switchToSourceBooks());
		buttonMasterData.setWidthFull();
		Button buttonBack = ButtonFactory.createButton("Back");
		buttonBack.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(MainMenuView.URL)));
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonMasterData);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		setWidthFull();
		setMargin(false);
		add(new HeaderLayout(buttonBack, "Master Data", HeaderLayoutMode.WRAPPED), buttonGrid);
		logger.info("main menu view opened for user '{}'.", sessionData.getUserName());
	}

	private void switchToSourceBooks() {
		getUI().ifPresent(ui -> ui.navigate(SourceBookPageLayout.URL));
	}

}