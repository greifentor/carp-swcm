package de.ollie.carp.swcm.persistence.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.carp.swcm.persistence.entity.SourceBookDBO;
import de.ollie.carp.swcm.core.model.SourceBook;

/**
 * A DBO converter for source_books.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookDBOConverter implements ToModelConverter<SourceBook, SourceBookDBO> {

	public SourceBookDBO toDBO(SourceBook model) {
		if (model == null) {
			return null;
		}
		return new SourceBookDBO()
				.setId(model.getId())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName())
				.setOriginalName(model.getOriginalName())
				.setToken(model.getToken());
	}

	public SourceBook toModel(SourceBookDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SourceBook()
				.setId(dbo.getId())
				.setGlobalId(dbo.getGlobalId())
				.setName(dbo.getName())
				.setOriginalName(dbo.getOriginalName())
				.setToken(dbo.getToken());
	}

}