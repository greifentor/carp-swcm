package de.ollie.carp.swcm.core.service.impl;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBookChanges;
import de.ollie.carp.swcm.core.service.port.persistence.SourceBookChangesPersistencePort;
import de.ollie.carp.swcm.core.service.SourceBookChangesService;
import lombok.Generated;

/**
 * A service interface implementation for SourceBookChanges management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookChangesServiceImpl implements SourceBookChangesService {

	@Inject
	private SourceBookChangesPersistencePort persistencePort;

	@Override
	public SourceBookChanges create(SourceBookChanges model) {
		return persistencePort.create(model);
	}

	@Override
	public Page<SourceBookChanges> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SourceBookChanges> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SourceBookChanges update(SourceBookChanges model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SourceBookChanges model) {
		persistencePort.delete(model);
	}

}