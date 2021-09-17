package de.ollie.carp.swcm.gui.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.carp.corelib.aop.annotations.LogExecutionTime;
import de.ollie.carp.corelib.event.Event;
import de.ollie.carp.corelib.event.EventListener;
import de.ollie.carp.corelib.event.EventManager;
import de.ollie.carp.corelib.gui.Disposable;
import de.ollie.carp.corelib.gui.user.UserLoginView;
import de.ollie.carp.corelib.localization.ResourceManager;
import de.ollie.carp.corelib.service.AppConfiguration;
import de.ollie.carp.corelib.service.SessionIdSO;
import de.ollie.carp.corelib.service.user.SessionOwner;
import de.ollie.carp.corelib.service.user.UserAuthorizationSO;

/**
 * The view manager for the Star Wars CARP.
 *
 * @author ollie (11.08.2021)
 */
@Route("carp-swcm")
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ViewManager extends VerticalLayout implements Disposable, EventListener, SessionOwner {

	private static final Logger logger = LogManager.getLogger(ViewManager.class);

	private static int counter = 0;

	private final AppConfiguration appConfiguration;
	private final EventManager eventManager;
	private final ResourceManager resourceManager;
	private final ServiceAccess serviceAccess;

	private UserAuthorizationSO userAuthorization;
	private SessionIdSO sessionId;

	public ViewManager(
			AppConfiguration appConfiguration,
			EventManager eventManager,
			ServiceAccess serviceAccess,
			ResourceManager resourceManager) {
		super();
		this.appConfiguration = appConfiguration;
		this.eventManager = eventManager;
		this.resourceManager = resourceManager;
		this.serviceAccess = serviceAccess;
		this.sessionId = new SessionIdSO(appConfiguration.getName() + "Session-" + ++counter);
		getStyle().set("background-image", "url(StarWars-Background.png)");
		setHeight("632px");
		logger.info("Started view manager ... " + sessionId);
		logger.info("AppConfiguration         {}", check(appConfiguration));
		logger.info("EventManager             {}", check(eventManager));
		System.out.println(eventManager);
		eventManager.addListener(this);
		setMargin(false);
		setWidthFull();
		add(
				new UserLoginView(
						appConfiguration,
						eventManager,
						resourceManager,
						this,
						serviceAccess.getUserAuthorizationService(),
						sessionId));
	}

	@LogExecutionTime
	private String check(Object obj) {
		return obj != null ? "ok" : "FAILED";
	}

	@LogExecutionTime
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		logger.info("onAttach");
		super.onAttach(attachEvent);
	}

	@LogExecutionTime
	@Override
	protected void onDetach(DetachEvent detachEvent) {
		logger.info("onDetach");
		super.onDetach(detachEvent);
		eventManager.removeListener(this);
	}

	@LogExecutionTime
	@Override
	public void eventDetected(Event event) {
		logger.info("event caught: {}", event);
		getUI()
				.ifPresentOrElse(
						ui -> ui.access(() -> processEvent(event)),
						() -> logger.warn("UI context not found while event caught!"));
	}

	@LogExecutionTime
	private void processEvent(Event event) {
		logger.info("processing event: {}", event);
		if (event.getType().getName().equals("LOGGED_IN")) {
			setMainMenuView();
			logger.info("login: {}", event);
		}
	}

	@LogExecutionTime
	@Override
	public void dispose() {
		eventManager.removeListener(this);
	}

	@LogExecutionTime
	@Override
	public UserAuthorizationSO getUserAuthorization() {
		return this.userAuthorization;
	}

	@LogExecutionTime
	@Override
	public void setUserAuthorization(UserAuthorizationSO userAuthorization) {
		this.userAuthorization = userAuthorization;
	}

	public void setMainMenuView() {
		getUI().ifPresent(ui -> ui.navigate("carp-swcm/menu"));
	}

}
