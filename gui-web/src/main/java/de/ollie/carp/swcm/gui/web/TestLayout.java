package de.ollie.carp.swcm.gui.web;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;

/**
 * @author ollie (16.09.2021)
 */
@Route("carp-swcm/test")
public class TestLayout extends VerticalLayout {

	@Inject
	private SourceBookGOService sourceBookService;

	@PostConstruct
	void postConstruct() {
		sourceBookService
				.findAll(new PageParametersGO().setEntriesPerPage(Integer.MAX_VALUE))
				.getEntries()
				.stream()
				.forEach(sourceBook -> add(new Label(sourceBook.getName())));
		Button buttonDetail = ButtonFactory.createButton("Detail");
		add(buttonDetail);
		QueryParameters parameters = new QueryParameters(Map.of("id", List.of("2")));
		buttonDetail
				.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("carp-swcm/test/details", parameters)));
	}

}