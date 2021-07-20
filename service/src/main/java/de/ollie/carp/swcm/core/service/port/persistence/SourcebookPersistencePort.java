package de.ollie.carp.swcm.core.service.port.persistence;

import java.util.Optional;

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

	Optional<Sourcebook> findById(Long id);

	Sourcebook update(Sourcebook model);

	void delete(Sourcebook model);

}