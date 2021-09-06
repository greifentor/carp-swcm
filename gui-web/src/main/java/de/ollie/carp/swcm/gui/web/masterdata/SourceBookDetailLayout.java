package de.ollie.carp.swcm.gui.web.masterdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.carp.corelib.gui.vaadin.component.Button;
import de.ollie.carp.corelib.gui.vaadin.component.ParentLayout;
import de.ollie.carp.corelib.localization.LocalizationSO;
import de.ollie.carp.corelib.localization.ResourceManager;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.web.ButtonFactory;
import de.ollie.carp.swcm.gui.web.TextFieldFactory;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;

/**
 * A dialog to edit source book details.
 *
 * @author ollie (17.08.2021)
 */
public class SourceBookDetailLayout extends VerticalLayout {

	private static final Logger logger = LogManager.getLogger(SourceBookDetailLayout.class);

	private Button buttonBack;
	private Button buttonRemove;
	private Button buttonSave;
	private TextField textFieldGlobalId;
	private TextField textFieldName;
	private TextField textFieldOriginalName;
	private TextField textFieldToken;

	private ParentLayout parent;
	private ResourceManager resourceManager;
	private SourceBookGOService service;
	private SourceBookGO go;

	public SourceBookDetailLayout(
			ParentLayout parent,
			ResourceManager resourceManager,
			SourceBookGOService service,
			SourceBookGO go) {
		this.go = go;
		this.parent = parent;
		this.service = service;
		buttonBack = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.back.text"));
		buttonBack.addClickListener(event -> parent.back());
		buttonRemove = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.remove.text"));
		buttonRemove.addClickListener(event -> remove());
		buttonSave = ButtonFactory.createButton(resourceManager.getLocalizedString("sourcebooks.button.save.text"));
		buttonSave.addClickListener(event -> save());
		textFieldGlobalId = TextFieldFactory
				.createTextField(
						resourceManager
								.getLocalizedString("sourcebooks.details.field.globalId.label", LocalizationSO.DE));
		textFieldGlobalId.setValue(go.getGlobalId());
		textFieldGlobalId.setWidthFull();
		textFieldName = TextFieldFactory
				.createTextField(
						resourceManager.getLocalizedString("sourcebooks.details.field.name.label", LocalizationSO.DE));
		textFieldName.setValue(go.getName());
		textFieldName.setWidthFull();
		textFieldOriginalName = TextFieldFactory
				.createTextField(
						resourceManager
								.getLocalizedString("sourcebooks.details.field.originalName.label", LocalizationSO.DE));
		textFieldOriginalName.setValue(go.getOriginalName());
		textFieldOriginalName.setWidthFull();
		textFieldToken = TextFieldFactory
				.createTextField(
						resourceManager.getLocalizedString("sourcebooks.details.field.token.label", LocalizationSO.DE));
		textFieldToken.setValue(go.getToken());
		textFieldToken.setWidthFull();
		add(buttonBack, textFieldGlobalId, textFieldName, textFieldOriginalName, textFieldToken, buttonSave);
		if (go.getId() > 0) {
			add(buttonRemove);
		}
		setMargin(false);
		setWidthFull();
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

	private void save() {
		go.setGlobalId(textFieldGlobalId.getValue());
		go.setName(textFieldName.getValue());
		go.setOriginalName(textFieldOriginalName.getValue());
		go.setToken(textFieldToken.getValue());
		service.update(go);
		parent.back();
	}

	private void remove() {
		service.delete(go);
		parent.back();
	}

}