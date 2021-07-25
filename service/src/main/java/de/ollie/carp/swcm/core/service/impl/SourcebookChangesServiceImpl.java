package de.ollie.carp.swcm.core.service.impl;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourcebookChanges;
import de.ollie.carp.swcm.core.service.port.persistence.SourcebookChangesPersistencePort;
import de.ollie.carp.swcm.core.service.SourcebookChangesService;
import lombok.Generated;

/**
 * A service interface implementation for SourcebookChanges management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourcebookChangesServiceImpl implements SourcebookChangesService {

	@Inject
	private SourcebookChangesPersistencePort persistencePort;

	@Override
	public SourcebookChanges create(SourcebookChanges model) {
		return persistencePort.create(model);
	}

	@Override
	public Page<SourcebookChanges> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SourcebookChanges> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SourcebookChanges update(SourcebookChanges model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SourcebookChanges model) {
		persistencePort.delete(model);
	}

}