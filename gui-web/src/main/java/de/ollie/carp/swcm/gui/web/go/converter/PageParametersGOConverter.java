package de.ollie.carp.swcm.gui.web.go.converter;

import javax.inject.Named;

import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.gui.web.go.PageParametersGO;

/**
 * A converter for the page parameters GO.
 *
 * @author ollie (16.08.2021)
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