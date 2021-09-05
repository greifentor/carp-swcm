package de.ollie.carp.swcm.gui.vaadin.converter;

import javax.inject.Named;

import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;

/**
 * A converter for the page parameters GO.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Named
public class PageParametersGOConverter {

	public PageParameters toModel(PageParametersGO go) {
		if (go == null) {
			return null;
		}
		return new PageParameters().setEntriesPerPage(go.getEntriesPerPage()).setPageNumber(go.getPageNumber());
	}

}