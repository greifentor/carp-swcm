package de.ollie.carp.swcm.persistence;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.persistence.converter.SourcebookChangesDBOConverter;
import de.ollie.carp.swcm.persistence.repository.SourcebookChangesDBORepository;
import de.ollie.carp.swcm.core.model.SourcebookChanges;

/**
 * A DBO persistence adapter for sourcebook_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Named
public class SourcebookChangesJPAPersistenceAdapter {

	@Inject
	private SourcebookChangesDBOConverter converter;
	@Inject
	private SourcebookChangesDBORepository repository;

	public SourcebookChanges create(SourcebookChanges model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public Page<SourcebookChanges> findAll(PageParameters pageParameters) {
		return null;
	}

	public Optional<SourcebookChanges> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	public SourcebookChanges update(SourcebookChanges model) {
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public void delete(SourcebookChanges model) {
		repository.deleteById(model.getId());
	}

}