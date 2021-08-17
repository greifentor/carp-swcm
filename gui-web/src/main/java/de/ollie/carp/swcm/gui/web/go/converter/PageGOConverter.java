package de.ollie.carp.swcm.gui.web.go.converter;

import java.util.stream.Collectors;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.gui.web.go.PageGO;

/**
 * A converter for pages of GO's.
 * 
 * @param <GO>    The type of the GO's.
 * @param <MODEL> The type of the models.
 *
 * @author ollie (16.08.2021)
 */
public class PageGOConverter<GO, MODEL> {

	private ToGOConverter<GO, MODEL> converter;

	public PageGOConverter(ToGOConverter<GO, MODEL> converter) {
		this.converter = converter;
	}

	public PageGO<GO> toGO(Page<MODEL> model) {
		if (model == null) {
			return null;
		}
		return new PageGO<GO>()
				.setEntries(model.getEntries().stream().map(converter::toGO).collect(Collectors.toList()))
				.setEntriesPerPage(model.getEntriesPerPage())
				.setEntriesTotal(model.getEntriesTotal());
	}

}