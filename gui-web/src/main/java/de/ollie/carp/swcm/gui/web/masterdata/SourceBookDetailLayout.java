package de.ollie.carp.swcm.gui.web.masterdata;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.component.TextFieldFactory;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;
import lombok.RequiredArgsConstructor;

/**
 * A dialog to edit source book details.
 *
 * @author ollie (17.08.2021)
 */
@Route("carp-swcm/sourcebooks/details")
@RequiredArgsConstructor
public class SourceBookDetailLayout extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	private static final Logger logger = LogManager.getLogger(SourceBookDetailLayout.class);

	private final ResourceManager resourceManager;
	private final SourceBookGOService service;

	private Button buttonBack;
	private Button buttonRemove;
	private Button buttonSave;

	private TextField textFieldGlobalId;
	private TextField textFieldName;
	private TextField textFieldOriginalName;
	private TextField textFieldToken;

	private SourceBookGO go;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.debug("setParameter");
		Location location = event.getLocation();
		QueryParameters queryParameters = location.getQueryParameters();
		Map<String, List<String>> parametersMap = queryParameters.getParameters();
		System.out.println(parameter + "\n" + parametersMap);
		long id = parametersMap.containsKey("id") && (parametersMap.get("id").size() > 0)
				? Long.parseLong(parametersMap.get("id").get(0))
				: -1;
		go = service
				.findById(id)
				.orElse(new SourceBookGO().setId(-1).setGlobalId("").setName("").setOriginalName("").setToken(""));
		System.out.println(go);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		buttonBack = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.back.text"));
		buttonBack.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("carp-swcm/sourcebooks")));
		buttonRemove = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.remove.text"));
		buttonRemove.addClickListener(event -> remove());
		buttonSave = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.save.text"));
		buttonSave.addClickListener(event -> save());
		textFieldGlobalId = TextFieldFactory
				.createTextField(
						resourceManager
								.getLocalizedString("sourcebooks.details.field.globalId.label", LocalizationGO.DE));
		textFieldGlobalId.setValue(go.getGlobalId());
		textFieldGlobalId.setWidthFull();
		textFieldName = TextFieldFactory
				.createTextField(
						resourceManager.getLocalizedString("sourcebooks.details.field.name.label", LocalizationGO.DE));
		textFieldName.setValue(go.getName());
		textFieldName.setWidthFull();
		textFieldOriginalName = TextFieldFactory
				.createTextField(
						resourceManager
								.getLocalizedString("sourcebooks.details.field.originalName.label", LocalizationGO.DE));
		textFieldOriginalName.setValue(go.getOriginalName());
		textFieldOriginalName.setWidthFull();
		textFieldToken = TextFieldFactory
				.createTextField(
						resourceManager.getLocalizedString("sourcebooks.details.field.token.label", LocalizationGO.DE));
		textFieldToken.setValue(go.getToken());
		textFieldToken.setWidthFull();
		setMargin(true);
		setWidthFull();
		MasterDataButtonLayout buttonLayout = new MasterDataButtonLayout(getButtons(go.getId()));
		add(buttonBack, textFieldGlobalId, textFieldName, textFieldOriginalName, textFieldToken, buttonLayout);
	}

	private Button[] getButtons(long id) {
		boolean couldBeRemoved = id > 0;
		Button[] buttons = new Button[1 + (couldBeRemoved ? 1 : 0)];
		buttons[0] = buttonSave;
		if (couldBeRemoved) {
			buttons[1] = buttonRemove;
		}
		return buttons;
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
		getElement().removeFromTree();
	}

	private void save() {
		go.setGlobalId(textFieldGlobalId.getValue());
		go.setName(textFieldName.getValue());
		go.setOriginalName(textFieldOriginalName.getValue());
		go.setToken(textFieldToken.getValue());
		service.update(go);
		getUI().ifPresent(ui -> ui.navigate("carp-swcm/sourcebooks"));
	}

	private void remove() {
		service.delete(go);
		getUI().ifPresent(ui -> ui.navigate("carp-swcm/sourcebooks"));
	}

}