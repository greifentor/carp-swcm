package de.ollie.carp.swcm.core.service;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBookChanges;
import lombok.Generated;

/**
 * A service interface for SourceBookChanges management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourceBookChangesService {

	SourceBookChanges create(SourceBookChanges model);

	Page<SourceBookChanges> findAll(PageParameters pageParameters);

	Optional<SourceBookChanges> findById(Long id);

	SourceBookChanges update(SourceBookChanges model);

	void delete(SourceBookChanges model);

}