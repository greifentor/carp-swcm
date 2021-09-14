package de.ollie.carp.swcm.gui.vaadin.component;

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
				.setBorderColor("yellow")
				.setColor("black")
				.setBackgroundImage("gate.png");
		return button;
	}

}