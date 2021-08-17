package de.ollie.carp.swcm.gui.web;

import de.ollie.carp.corelib.gui.vaadin.component.Button;

/**
 * A factory for buttons.
 *
 * @author ollie (16.08.2021)
 */
public class ButtonFactory {

	public static Button createButton(String text) {
		Button button = new Button(text)
				.setBackgroundColor("white")
				.setBorder("solid 2px")
				.setBorderColor("green")
				.setColor("black")
				.setBackgroundImage("gate.png");
		return button;
	}

}