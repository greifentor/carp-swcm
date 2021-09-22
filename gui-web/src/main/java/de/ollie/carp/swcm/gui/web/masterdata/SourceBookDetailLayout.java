package de.ollie.carp.swcm.gui.web.masterdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;

import de.ollie.carp.swcm.gui.vaadin.component.AbstractMasterDataBaseLayout;
import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.web.HeaderLayout;
import de.ollie.carp.swcm.gui.web.HeaderLayout.HeaderLayoutMode;
import de.ollie.carp.swcm.gui.web.SessionData;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;
import lombok.RequiredArgsConstructor;

/**
 * A dialog to edit source book details.
 *
 * @author ollie (17.08.2021)
 */
@Route(SourceBookDetailLayout.URL)
@RequiredArgsConstructor
public class SourceBookDetailLayout extends AbstractMasterDataBaseLayout {

	public static final String URL = "carp-swcm/masterdata/sourcebooks/details";

	private static final Logger logger = LogManager.getLogger(SourceBookDetailLayout.class);

	private final ResourceManager resourceManager;
	private final SourceBookGOService service;
	private final SessionData sessionData;

	private Button buttonRemove;
	private Button buttonSave;

	private TextField textFieldGlobalId;
	private TextField textFieldName;
	private TextField textFieldOriginalName;
	private TextField textFieldToken;

	private SourceBookGO go;

	@Override
	protected SessionData getSessionData() {
		return sessionData;
	}

	@Override
	protected ResourceManager getResourceManager() {
		return resourceManager;
	}

	@Override
	protected String getTextFieldResourceId() {
		return "sourcebooks.details.field.{}.label";
	}

	@Override
	public void doSetParameter(BeforeEvent event) {
		long id = parametersMap.containsKey("id") && (parametersMap.get("id").size() > 0)
				? Long.parseLong(parametersMap.get("id").get(0))
				: -1;
		go = service
				.findById(id)
				.orElse(new SourceBookGO().setId(-1).setGlobalId("").setName("").setOriginalName("").setToken(""));
	}

	@Override
	public void doBeforeEnter(BeforeEnterEvent beforeEnterEvent) {
		buttonRemove = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.remove.text"));
		buttonRemove.addClickListener(event -> remove());
		buttonSave = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.save.text"));
		buttonSave.addClickListener(event -> save());
		textFieldGlobalId = createTextField("globalId", go.getGlobalId());
		textFieldName = createTextField("name", go.getName());
		textFieldOriginalName = createTextField("originalName", go.getOriginalName());
		textFieldToken = createTextField("token", go.getToken());
		setMargin(false);
		setWidthFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		layout.setWidthFull();
		MasterDataButtonLayout buttonLayout = new MasterDataButtonLayout(getButtons(go.getId()));
		layout
				.add(
						new HeaderLayout(
								ButtonFactory.createBackButton(resourceManager, this::getUI, SourceBookPageLayout.URL),
								ButtonFactory.createLogoutButton(resourceManager, this::getUI, sessionData, logger),
								"Sourcebook - " + go.getName(),
								HeaderLayoutMode.PLAIN),
						textFieldGlobalId,
						textFieldName,
						textFieldOriginalName,
						textFieldToken,
						buttonLayout);
		add(layout);
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
		getUI().ifPresent(ui -> ui.navigate(SourceBookPageLayout.URL));
	}

	private void remove() {
		service.delete(go);
		getUI().ifPresent(ui -> ui.navigate(SourceBookPageLayout.URL));
	}

}