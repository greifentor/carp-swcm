package de.ollie.carp.swcm.core.service.port.persistence;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBook;
import lombok.Generated;

/**
 * A persistence port interface for SourceBook CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourceBookPersistencePort {

	SourceBook create(SourceBook model);

	Page<SourceBook> findAll(PageParameters pageParameters);

	Optional<SourceBook> findById(Long id);

	SourceBook update(SourceBook model);

	void delete(SourceBook model);

}