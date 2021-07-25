package de.ollie.carp.swcm.core.service.port.persistence;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourcebookChanges;
import lombok.Generated;

/**
 * A persistence port interface for SourcebookChanges CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourcebookChangesPersistencePort {

	SourcebookChanges create(SourcebookChanges model);

	Page<SourcebookChanges> findAll(PageParameters pageParameters);

	Optional<SourcebookChanges> findById(Long id);

	SourcebookChanges update(SourcebookChanges model);

	void delete(SourcebookChanges model);

}