package de.ollie.carp.swcm.gui.vaadin.component;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;

import de.ollie.carp.swcm.gui.web.SessionData;
import de.ollie.carp.swcm.gui.web.UserAuthorizationChecker;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;

/**
 * A base class for master data layouts.
 *
 * 
 * import de.ollie.carp.swcm.gui.web.port.ResourceManager;@author ollie (22.09.2021)
 */
public abstract class AbstractMasterDataBaseLayout extends VerticalLayout
		implements BeforeEnterObserver, HasUrlParameter<String> {

	private static final Logger logger = LogManager.getLogger(AbstractMasterDataBaseLayout.class);

	protected Button buttonRemove;
	protected Button buttonSave;

	protected Map<String, List<String>> parametersMap;

	protected abstract ResourceManager getResourceManager();

	protected abstract SessionData getSessionData();

	protected abstract String getTextFieldResourceId();

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.info("setParameter");
		Location location = event.getLocation();
		QueryParameters queryParameters = location.getQueryParameters();
		parametersMap = queryParameters.getParameters();
		doSetParameter(event);
	}

	protected void doSetParameter(BeforeEvent event) {
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		logger.info("check for authorization");
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(getSessionData(), beforeEnterEvent);
		buttonRemove = ButtonFactory.createRemoveButton(getResourceManager(), event -> remove(), getSessionData());
		buttonSave = ButtonFactory.createSaveButton(getResourceManager(), event -> save(), getSessionData());
		doBeforeEnter(beforeEnterEvent);
	}

	protected abstract void remove();

	protected abstract void save();

	protected void doBeforeEnter(BeforeEnterEvent beforeEnterEvent) {
	}

	protected TextField createTextField(String fieldName, String fieldContent) {
		TextField textField = TextFieldFactory
				.createTextField(
						getResourceManager()
								.getLocalizedString(
										getTextFieldResourceId().replace("{}", fieldName),
										getSessionData().getLocalization()));
		textField.setValue(fieldContent);
		textField.setWidthFull();
		return textField;
	}

	protected Button[] getButtons(boolean couldBeRemoved) {
		Button[] buttons = new Button[1 + (couldBeRemoved ? 1 : 0)];
		buttons[0] = buttonSave;
		if (couldBeRemoved) {
			buttons[1] = buttonRemove;
		}
		return buttons;
	}

	protected MasterDataButtonLayout getMasterDataButtonLayout(boolean couldBeRemoved) {
		return new MasterDataButtonLayout(getButtons(couldBeRemoved));
	}
}