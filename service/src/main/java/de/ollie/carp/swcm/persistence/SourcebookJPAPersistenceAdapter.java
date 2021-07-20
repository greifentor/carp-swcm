package de.ollie.carp.swcm.persistence;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Sourcebook;
import de.ollie.carp.swcm.persistence.converter.SourcebookDBOConverter;
import de.ollie.carp.swcm.persistence.repository.SourcebookDBORepository;

/**
 * A DBO persistence adapter for sourcebooks.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Named
public class SourcebookJPAPersistenceAdapter {

	@Inject
	private SourcebookDBOConverter converter;
	@Inject
	private SourcebookDBORepository repository;

	public Sourcebook create(Sourcebook model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public Optional<Sourcebook> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	public Sourcebook update(Sourcebook model) {
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public void delete(Sourcebook model) {
		repository.deleteById(model.getId());
	}

}