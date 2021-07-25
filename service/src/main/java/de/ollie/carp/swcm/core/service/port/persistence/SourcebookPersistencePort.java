package de.ollie.carp.swcm.core.service.port.persistence;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.Sourcebook;
import lombok.Generated;

/**
 * A persistence port interface for Sourcebook CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourcebookPersistencePort {

	Sourcebook create(Sourcebook model);

	Page<Sourcebook> findAll(PageParameters pageParameters);

	Optional<Sourcebook> findById(Long id);

	Sourcebook update(Sourcebook model);

	void delete(Sourcebook model);

}