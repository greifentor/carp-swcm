package de.ollie.carp.swcm.persistence.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.carp.swcm.persistence.entity.SourceBookChangesDBO;
import de.ollie.carp.swcm.core.model.SourceBookChanges;

/**
 * A DBO converter for source_book_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookChangesDBOConverter implements ToModelConverter<SourceBookChanges, SourceBookChangesDBO> {

	public SourceBookChangesDBO toDBO(SourceBookChanges model) {
		if (model == null) {
			return null;
		}
		return new SourceBookChangesDBO()
				.setId(model.getId())
				.setRecordId(model.getRecordId())
				.setAttributeName(model.getAttributeName())
				.setChangeDate(model.getChangeDate())
				.setNewValue(model.getNewValue());
	}

	public SourceBookChanges toModel(SourceBookChangesDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SourceBookChanges()
				.setId(dbo.getId())
				.setRecordId(dbo.getRecordId())
				.setAttributeName(dbo.getAttributeName())
				.setChangeDate(dbo.getChangeDate())
				.setNewValue(dbo.getNewValue());
	}

}