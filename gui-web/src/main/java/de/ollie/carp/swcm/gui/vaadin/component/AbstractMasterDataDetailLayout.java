package de.ollie.carp.swcm.gui.vaadin.component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.ollie.carp.swcm.gui.web.port.ResourceManager;
import lombok.Generated;

/**
 * A base layout for master data detail views.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class AbstractMasterDataDetailLayout extends VerticalLayout {

	protected Button buttonBack;
	protected Button buttonRemove;
	protected Button buttonSave;

	protected ParentLayout parent;
	protected ResourceManager resourceManager;

	public AbstractMasterDataDetailLayout(ParentLayout parent, ResourceManager resourceManager, String resourcePrefix) {
		this.parent = parent;
		this.resourceManager = resourceManager;
		buttonBack =
				ButtonFactory.createButton(resourceManager.getLocalizedString(resourcePrefix + ".button.back.text"));
		buttonBack.addClickListener(event -> back());
		buttonRemove =
				ButtonFactory.createButton(resourceManager.getLocalizedString(resourcePrefix + ".button.remove.text"));
		buttonRemove.addClickListener(event -> remove());
		buttonSave =
				ButtonFactory.createButton(resourceManager.getLocalizedString(resourcePrefix + ".button.save.text"));
		buttonSave.addClickListener(event -> save());
	}

	protected Button[] getButtons(long id) {
		boolean couldBeRemoved = id > 0;
		Button[] buttons = new Button[1 + (couldBeRemoved ? 1 : 0)];
		buttons[0] = buttonSave;
		if (couldBeRemoved) {
			buttons[1] = buttonRemove;
		}
		return buttons;
	}

	protected void back() {
		parent.back();
	}

	protected abstract void remove();

	protected abstract void save();

}