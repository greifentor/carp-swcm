package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.carp.corelib.gui.user.UserLoginView;
import de.ollie.carp.corelib.localization.ResourceManager;
import de.ollie.carp.corelib.service.AppConfiguration;
import de.ollie.carp.corelib.service.user.SessionOwner;
import de.ollie.carp.corelib.service.user.UserAuthorizationSO;

/**
 * The view manager for the Star Wars CARP.
 *
 * @author ollie (11.08.2021)
 */
@Route(ApplicationStartLayout.URL)
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ApplicationStartLayout extends VerticalLayout implements SessionOwner {

	public static final String URL = "carp-swcm";

	private static final Logger logger = LogManager.getLogger(ApplicationStartLayout.class);

	private final AppConfiguration appConfiguration;
	private final ResourceManager resourceManager;
	private final ServiceAccess serviceAccess;
	private final SessionData sessionData;

	public ApplicationStartLayout(
			AppConfiguration appConfiguration,
			ServiceAccess serviceAccess,
			ResourceManager resourceManager,
			SessionData sessionData) {
		super();
		this.appConfiguration = appConfiguration;
		this.resourceManager = resourceManager;
		this.serviceAccess = serviceAccess;
		this.sessionData = sessionData;
		getStyle().set("background-image", "url(StarWars-Background.png)");
		setHeight("632px");
		setMargin(false);
		setWidthFull();
		add(
				new UserLoginView(
						appConfiguration,
						null,
						resourceManager,
						this,
						serviceAccess.getUserAuthorizationService(),
						sessionData.getId()) {
					public void loggedIn(UserAuthorizationSO userAuthorization) {
						loggedInToApplication(userAuthorization);
					}
				});
	}

	private void loggedInToApplication(UserAuthorizationSO userAuthorization) {
		sessionData.setUserAuthorization(userAuthorization);
		logger.info("user '{}' has logged in.", sessionData.getUserAuthorization().getName());
		getUI().ifPresent(ui -> ui.navigate(MainMenuView.URL));
	}

	@Override
	public UserAuthorizationSO getUserAuthorization() {
		return sessionData.getUserAuthorization();
	}

	@Override
	public void setUserAuthorization(UserAuthorizationSO userAuthorization) {
		sessionData.setUserAuthorization(userAuthorization);
	}

}
