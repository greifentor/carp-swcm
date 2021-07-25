package de.ollie.carp.swcm.persistence.converter;

import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Sourcebook;
import de.ollie.carp.swcm.persistence.entity.SourcebookDBO;
import lombok.Generated;

/**
 * A DBO converter for sourcebooks.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourcebookDBOConverter implements ToModelConverter<Sourcebook, SourcebookDBO> {

	public SourcebookDBO toDBO(Sourcebook model) {
		if (model == null) {
			return null;
		}
		return new SourcebookDBO()
				.setId(model.getId())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName())
				.setOriginalName(model.getOriginalName())
				.setToken(model.getToken());
	}

	public Sourcebook toModel(SourcebookDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new Sourcebook()
				.setId(dbo.getId())
				.setGlobalId(dbo.getGlobalId())
				.setName(dbo.getName())
				.setOriginalName(dbo.getOriginalName())
				.setToken(dbo.getToken());
	}

}