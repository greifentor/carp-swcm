package de.ollie.carp.swcm.core.service.impl;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Sourcebook;
import de.ollie.carp.swcm.core.service.port.persistence.SourcebookPersistencePort;
import de.ollie.carp.swcm.core.service.SourcebookService;
import lombok.Generated;

/**
 * A service interface implementation for Sourcebook management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourcebookServiceImpl implements SourcebookService {

	@Inject
	private SourcebookPersistencePort persistencePort;

	@Override
	public Sourcebook create(Sourcebook model) {
		return persistencePort.create(model);
	}

	@Override
	public Optional<Sourcebook> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public Sourcebook update(Sourcebook model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(Sourcebook model) {
		persistencePort.delete(model);
	}

}