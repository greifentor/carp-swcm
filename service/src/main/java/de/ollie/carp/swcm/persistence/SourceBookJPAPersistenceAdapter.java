package de.ollie.carp.swcm.persistence;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.core.service.port.persistence.SourceBookPersistencePort;
import de.ollie.carp.swcm.persistence.converter.PageConverter;
import de.ollie.carp.swcm.persistence.converter.PageParametersToPageableConverter;
import de.ollie.carp.swcm.persistence.converter.SourceBookDBOConverter;
import de.ollie.carp.swcm.persistence.entity.SourceBookDBO;
import de.ollie.carp.swcm.persistence.repository.SourceBookDBORepository;
import lombok.Generated;

/**
 * A DBO persistence adapter for source_books.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SourceBookJPAPersistenceAdapter implements SourceBookPersistencePort {

	@Inject
	private SourceBookDBOConverter converter;
	@Inject
	private SourceBookDBORepository repository;

	@Inject
	private PageParametersToPageableConverter pageParametersToPageableConverter;

	private PageConverter<SourceBook, SourceBookDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	public SourceBook create(SourceBook model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public Page<SourceBook> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	public Optional<SourceBook> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	public SourceBook update(SourceBook model) {
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public void delete(SourceBook model) {
		repository.deleteById(model.getId());
	}

}