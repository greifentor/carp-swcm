package de.ollie.carp.swcm.persistence;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.persistence.converter.PageConverter;
import de.ollie.carp.swcm.persistence.converter.PageParametersToPageableConverter;
import de.ollie.carp.swcm.persistence.converter.SourcebookDBOConverter;
import de.ollie.carp.swcm.persistence.entity.SourcebookDBO;
import de.ollie.carp.swcm.persistence.repository.SourcebookDBORepository;
import de.ollie.carp.swcm.core.model.Sourcebook;

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

	@Inject
	private PageParametersToPageableConverter pageParametersToPageableConverter;

	private PageConverter<Sourcebook, SourcebookDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	public Sourcebook create(Sourcebook model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	public Page<Sourcebook> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
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