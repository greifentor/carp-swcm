package de.ollie.carp.swcm.persistence;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBookChanges;
import de.ollie.carp.swcm.core.service.port.persistence.SourceBookChangesPersistencePort;
import de.ollie.carp.swcm.persistence.converter.PageConverter;
import de.ollie.carp.swcm.persistence.converter.PageParametersToPageableConverter;
import de.ollie.carp.swcm.persistence.converter.SourceBookChangesDBOConverter;
import de.ollie.carp.swcm.persistence.entity.SourceBookChangesDBO;
import de.ollie.carp.swcm.persistence.repository.SourceBookChangesDBORepository;
import lombok.Generated;

/**
 * A DBO persistence adapter for source_book_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookChangesJPAPersistenceAdapter implements SourceBookChangesPersistencePort {

	@Inject
	private SourceBookChangesDBOConverter converter;
	@Inject
	private SourceBookChangesDBORepository repository;

	@Inject
	private PageParametersToPageableConverter pageParametersToPageableConverter;

	private PageConverter<SourceBookChanges, SourceBookChangesDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	public SourceBookChanges create(SourceBookChanges model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public Page<SourceBookChanges> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	public Optional<SourceBookChanges> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	public SourceBookChanges update(SourceBookChanges model) {
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public void delete(SourceBookChanges model) {
		repository.deleteById(model.getId());
	}

}