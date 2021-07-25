package de.ollie.carp.swcm.persistence.converter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import de.ollie.carp.swcm.core.model.PageParameters;

/**
 * A converter to create a Pageable from a PageParameters object.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
public class PageParametersToPageableConverter {

	public Pageable convert(PageParameters pageParameters) {
		if (pageParameters == null) {
			return null;
		}
		return PageRequest.of(pageParameters.getPageNumber(), pageParameters.getEntriesPerPage());
	}

}