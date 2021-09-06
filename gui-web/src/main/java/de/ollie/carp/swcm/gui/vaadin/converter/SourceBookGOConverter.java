package de.ollie.carp.swcm.gui.vaadin.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.core.model.SourceBook;

/**
 * A GO converter for source_books.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookGOConverter implements ToGOConverter<SourceBookGO, SourceBook> {

	public SourceBookGO toGO(SourceBook model) {
		if (model == null) {
			return null;
		}
		return new SourceBookGO()
				.setId(model.getId())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName())
				.setOriginalName(model.getOriginalName())
				.setToken(model.getToken());
	}

	public SourceBook toModel(SourceBookGO go) {
		if (go == null) {
			return null;
		}
		return new SourceBook()
				.setId(go.getId())
				.setGlobalId(go.getGlobalId())
				.setName(go.getName())
				.setOriginalName(go.getOriginalName())
				.setToken(go.getToken());
	}

}