package de.ollie.carp.swcm.gui.vaadin.component;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.UI;

import de.ollie.carp.swcm.gui.web.ApplicationStartLayout;
import de.ollie.carp.swcm.gui.web.SessionData;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;

/**
 * A factory for buttons.
 *
 * @author ollie (16.08.2021)
 */
public class ButtonFactory {

	public static Button createButton(String text) {
		Button button = new Button(text)
				.setBackgroundColor("white")
				.setBorder("solid 2px")
				.setBorderColor("yellow")
				.setColor("black")
				.setBackgroundImage("gate.png");
		return button;
	}

	public static Button createBackButton(ResourceManager resourceManager, Supplier<Optional<UI>> uiSupplier,
			String urlBack) {
		Button buttonBack = ButtonFactory
				.createButton(resourceManager.getLocalizedString("commons.button.back.text", LocalizationGO.DE));
		buttonBack.addClickListener(event -> uiSupplier.get().ifPresent(ui -> ui.navigate(urlBack)));
		return buttonBack;
	}

	public static Button createLogoutButton(ResourceManager resourceManager, Supplier<Optional<UI>> uiSupplier,
			SessionData sessionData, Logger logger) {
		Button buttonLogout = ButtonFactory
				.createButton(resourceManager.getLocalizedString("commons.button.logout.text", LocalizationGO.DE));
		buttonLogout.addClickListener(event -> uiSupplier.get().ifPresent(ui -> {
			logger.info("user '{}' logged out.", sessionData.getUserAuthorization().getName());
			sessionData.setUserAuthorization(null);
			ui.navigate(ApplicationStartLayout.URL);
		}));
		return buttonLogout;
	}

}