package de.ollie.carp.swcm.core.service;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.SourcebookChanges;
import lombok.Generated;

/**
 * A service interface for SourcebookChanges management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourcebookChangesService {

	SourcebookChanges create(SourcebookChanges model);

	Optional<SourcebookChanges> findById(Long id);

	SourcebookChanges update(SourcebookChanges model);

	void delete(SourcebookChanges model);

}