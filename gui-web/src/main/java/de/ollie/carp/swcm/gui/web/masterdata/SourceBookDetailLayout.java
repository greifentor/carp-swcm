package de.ollie.carp.swcm.gui.web.masterdata;

import com.vaadin.flow.component.textfield.TextField;

import de.ollie.carp.corelib.localization.LocalizationSO;
import de.ollie.carp.corelib.localization.ResourceManager;
import de.ollie.carp.swcm.gui.vaadin.component.AbstractMasterDataDetailLayout;
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.component.ParentLayout;
import de.ollie.carp.swcm.gui.vaadin.component.TextFieldFactory;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.web.service.SourceBookGOService;

/**
 * A dialog to edit source book details.
 *
 * @author ollie (17.08.2021)
 */
public class SourceBookDetailLayout extends AbstractMasterDataDetailLayout {

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
		setMargin(false);
		setWidthFull();
		MasterDataButtonLayout buttonLayout = new MasterDataButtonLayout(getButtons(go.getId()));
		add(buttonBack, textFieldGlobalId, textFieldName, textFieldOriginalName, textFieldToken, buttonLayout);
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