package de.ollie.carp.swcm.gui.web;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import de.ollie.carp.swcm.gui.vaadin.component.Button;

/**
 * A layout for the page headers.
 *
 * @author ollie (20.09.2021)
 */
public class HeaderLayout extends HorizontalLayout {

	public enum HeaderLayoutMode {
		PLAIN,
		WRAPPED;
	}

	public HeaderLayout(Button button, String labelText, HeaderLayoutMode mode) {
		if (mode == HeaderLayoutMode.WRAPPED) {
			HorizontalLayout wrapper = new HorizontalLayout();
			prepareLayout(wrapper);
			wrapper.add(getInnerLayout(button, labelText));
			setWidthFull();
			setPadding(true);
			add(wrapper);
		} else {
			prepareLayout(this);
			add(getInnerLayout(button, labelText));
		}
	}

	private void prepareLayout(HorizontalLayout layout) {
		layout.setWidthFull();
		layout.setPadding(true);
		layout.getStyle().set("-moz-border-radius", "4px");
		layout.getStyle().set("-webkit-border-radius", "4px");
		layout.getStyle().set("border-radius", "4px");
		layout.getStyle().set("border", "1px solid LightSteelBlue");
	}

	private HorizontalLayout getInnerLayout(Button button, String labelText) {
		HorizontalLayout headerInner = new HorizontalLayout();
		headerInner.setWidthFull();
		headerInner.setMargin(false);
		Label label = new Label(labelText);
		label.setWidthFull();
		label.getStyle().set("display", "flex");
		label.getStyle().set("align-items", "center");
		label.getStyle().set("font-weight", "bold");
		button.setWidth("25%");
		headerInner.add(button, label);
		return headerInner;
	}
}
