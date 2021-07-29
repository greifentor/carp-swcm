package de.ollie.carp.swcm.core.service;

import java.util.Optional;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBook;
import lombok.Generated;

/**
 * A service interface for SourceBook management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SourceBookService {

	SourceBook create(SourceBook model);

	Page<SourceBook> findAll(PageParameters pageParameters);

	Optional<SourceBook> findById(Long id);

	SourceBook update(SourceBook model);

	void delete(SourceBook model);

}