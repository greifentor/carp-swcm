package de.ollie.carp.swcm.core.service;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
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

	Page<SourcebookChanges> findAll(PageParameters pageParameters);

	Optional<SourcebookChanges> findById(Long id);

	SourcebookChanges update(SourcebookChanges model);

	void delete(SourcebookChanges model);

}