package de.ollie.carp.swcm.core.service;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Sourcebook;
import lombok.Generated;

/**
 * A service interface for Sourcebook management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourcebookService {

	Sourcebook create(Sourcebook model);

	Optional<Sourcebook> findById(Long id);

	Sourcebook update(Sourcebook model);

	void delete(Sourcebook model);

}