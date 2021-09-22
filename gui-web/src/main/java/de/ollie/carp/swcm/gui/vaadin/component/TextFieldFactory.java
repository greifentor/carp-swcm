package de.ollie.carp.swcm.gui.vaadin.component;

/**
 * A factory for a text field.
 *
 * @author ollie (17.08.2021)
 */
public class TextFieldFactory {

	public static TextField createTextField(String label) {
		return new TextField(label);
	}

}