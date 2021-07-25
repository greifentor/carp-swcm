package de.ollie.carp.swcm.persistence.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.carp.swcm.persistence.entity.SourcebookChangesDBO;
import de.ollie.carp.swcm.core.model.SourcebookChanges;

/**
 * A DBO converter for sourcebook_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourcebookChangesDBOConverter implements ToModelConverter<SourcebookChanges, SourcebookChangesDBO> {

	public SourcebookChangesDBO toDBO(SourcebookChanges model) {
		if (model == null) {
			return null;
		}
		return new SourcebookChangesDBO()
				.setId(model.getId())
				.setRecordId(model.getRecordId())
				.setAttributeName(model.getAttributeName())
				.setChangeDate(model.getChangeDate())
				.setNewValue(model.getNewValue());
	}

	public SourcebookChanges toModel(SourcebookChangesDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SourcebookChanges()
				.setId(dbo.getId())
				.setRecordId(dbo.getRecordId())
				.setAttributeName(dbo.getAttributeName())
				.setChangeDate(dbo.getChangeDate())
				.setNewValue(dbo.getNewValue());
	}

}