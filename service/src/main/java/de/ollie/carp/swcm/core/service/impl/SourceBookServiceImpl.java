package de.ollie.carp.swcm.core.service.impl;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.core.service.port.persistence.SourceBookPersistencePort;
import de.ollie.carp.swcm.core.service.SourceBookService;
import lombok.Generated;

/**
 * A service interface implementation for SourceBook management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookServiceImpl implements SourceBookService {

	@Inject
	private SourceBookPersistencePort persistencePort;

	@Override
	public SourceBook create(SourceBook model) {
		return persistencePort.create(model);
	}

	@Override
	public Page<SourceBook> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SourceBook> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SourceBook update(SourceBook model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SourceBook model) {
		persistencePort.delete(model);
	}

}