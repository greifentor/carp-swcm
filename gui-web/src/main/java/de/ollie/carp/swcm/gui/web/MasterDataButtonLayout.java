package de.ollie.carp.swcm.gui.web;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import de.ollie.carp.corelib.gui.vaadin.component.Button;

/**
 * A layout for master data buttons.
 *
 * @author ollie (08.09.2021)
 */
public class MasterDataButtonLayout extends HorizontalLayout {

	public MasterDataButtonLayout(Button... buttons) {
		setMargin(false);
		setWidthFull();
		add(buttons);
	}

}