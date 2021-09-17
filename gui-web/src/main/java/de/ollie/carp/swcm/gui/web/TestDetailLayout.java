package de.ollie.carp.swcm.gui.web;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (16.09.2021)
 */
@Route("carp-swcm/test/details")
@RequiredArgsConstructor
public class TestDetailLayout extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	private static final Logger logger = LogManager.getLogger(TestDetailLayout.class);

	private final SourceBookGOService sourceBookService;

	private long id;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		Location location = event.getLocation();
		QueryParameters queryParameters = location.getQueryParameters();
		Map<String, List<String>> parametersMap = queryParameters.getParameters();
		id = Long.parseLong(parametersMap.get("id").get(0));
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

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		Button buttonBack = ButtonFactory.createButton("Back");
		buttonBack.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("carp-swcm/test")));
		sourceBookService
				.findById(id)
				.ifPresentOrElse(
						sourceBook -> add(
								new Label(sourceBook.getName()),
								new Label(sourceBook.getToken()),
								buttonBack),
						() -> System.out.println("Fehler"));
		logger.info("beforeEnter");
	}

}
