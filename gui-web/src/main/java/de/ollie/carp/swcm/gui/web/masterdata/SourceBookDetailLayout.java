package de.ollie.carp.swcm.gui.web.masterdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.carp.swcm.gui.vaadin.component.AbstractMasterDataDetailLayout;
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.component.ParentLayout;
import de.ollie.carp.swcm.gui.vaadin.component.TextFieldFactory;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;

/**
 * A dialog to edit source book details.
 *
 * @author ollie (17.08.2021)
 */
public class SourceBookDetailLayout extends AbstractMasterDataDetailLayout {

	private static final Logger logger = LogManager.getLogger(SourceBookDetailLayout.class);

	private TextField textFieldGlobalId;
	private TextField textFieldName;
	private TextField textFieldOriginalName;
	private TextField textFieldToken;

	private SourceBookGOService service;
	private SourceBookGO go;

	public SourceBookDetailLayout(
			ParentLayout parent,
			ResourceManager resourceManager,
			SourceBookGOService service,
			SourceBookGO go) {
		super(parent, resourceManager, "sourcebooks");
		this.service = service;
		this.go = go;
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
		setMargin(false);
		setWidthFull();
		MasterDataButtonLayout buttonLayout = new MasterDataButtonLayout(getButtons(go.getId()));
		add(buttonBack, textFieldGlobalId, textFieldName, textFieldOriginalName, textFieldToken, buttonLayout);
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

	@Override
	protected void save() {
		go.setGlobalId(textFieldGlobalId.getValue());
		go.setName(textFieldName.getValue());
		go.setOriginalName(textFieldOriginalName.getValue());
		go.setToken(textFieldToken.getValue());
		service.update(go);
		parent.back();
	}

	@Override
	protected void remove() {
		service.delete(go);
		parent.back();
	}

}