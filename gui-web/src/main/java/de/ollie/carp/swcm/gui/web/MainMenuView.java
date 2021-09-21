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
import de.ollie.carp.swcm.gui.web.HeaderLayout.HeaderLayoutMode;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.masterdata.MasterDataLayout;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;
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

	private final ResourceManager resourceManager;
	private final SessionData sessionData;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(sessionData, beforeEnterEvent);
		logger.info("created");
		Button buttonMasterData = ButtonFactory
				.createButton(
						resourceManager.getLocalizedString("main-menu.button.master-data.text", LocalizationGO.DE));
		buttonMasterData.addClickListener(event -> switchToMasterData());
		buttonMasterData.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonMasterData);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		setWidthFull();
		setMargin(false);
		add(
				new HeaderLayout(
						ButtonFactory.createLogoutButton(resourceManager, this::getUI, sessionData, logger),
						resourceManager.getLocalizedString("commons.header.main-menu.label", LocalizationGO.DE),
						HeaderLayoutMode.WRAPPED),
				buttonGrid);
		logger.info("main menu view opened for user '{}'.", sessionData.getUserName());
	}

	private void switchToMasterData() {
		getUI().ifPresent(ui -> ui.navigate(MasterDataLayout.URL));
	}

}