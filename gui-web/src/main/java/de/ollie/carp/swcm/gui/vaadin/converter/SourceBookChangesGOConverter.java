package de.ollie.carp.swcm.gui.vaadin.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.carp.swcm.gui.vaadin.go.SourceBookChangesGO;
import de.ollie.carp.swcm.core.model.SourceBookChanges;

/**
 * A GO converter for source_book_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookChangesGOConverter implements ToGOConverter<SourceBookChangesGO, SourceBookChanges> {

	public SourceBookChangesGO toGO(SourceBookChanges model) {
		if (model == null) {
			return null;
		}
		return new SourceBookChangesGO()
				.setId(model.getId())
				.setRecordId(model.getRecordId())
				.setAttributeName(model.getAttributeName())
				.setChangeDate(model.getChangeDate())
				.setNewValue(model.getNewValue());
	}

	public SourceBookChanges toModel(SourceBookChangesGO go) {
		if (go == null) {
			return null;
		}
		return new SourceBookChanges()
				.setId(go.getId())
				.setRecordId(go.getRecordId())
				.setAttributeName(go.getAttributeName())
				.setChangeDate(go.getChangeDate())
				.setNewValue(go.getNewValue());
	}

}