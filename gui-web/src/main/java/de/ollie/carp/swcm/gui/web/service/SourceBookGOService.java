package de.ollie.carp.swcm.gui.web.service;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.core.service.SourceBookService;
import de.ollie.carp.swcm.gui.vaadin.converter.PageGOConverter;
import de.ollie.carp.swcm.gui.vaadin.converter.PageParametersGOConverter;
import de.ollie.carp.swcm.gui.vaadin.converter.SourceBookGOConverter;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageGO;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;
import de.ollie.carp.swcm.gui.vaadin.go.converter.SourceBookGO;

/**
 * 
 *
 * @author ollie (16.08.2021)
 */
@Named
public class SourceBookGOService {

	@Inject
	private SourceBookGOConverter converter;
	@Inject
	private SourceBookService service;
	@Inject
	private PageParametersGOConverter pageParametersGOConverter;

	private PageGOConverter<SourceBookGO, SourceBook> pageGOConverter;

	@PostConstruct
	void postConstruct() {
		pageGOConverter = new PageGOConverter<>(converter);
	}

	public SourceBookGO create(SourceBookGO go) {
		return converter.toGO(service.create(converter.toModel(go)));
	}

	public PageGO<SourceBookGO> findAll(PageParametersGO pageParameters) {
		return pageGOConverter.toGO(service.findAll(pageParametersGOConverter.toModel(pageParameters)));
	}

	public Optional<SourceBookGO> findById(Long id) {
		return service.findById(id).map(converter::toGO);
	}

	public SourceBookGO update(SourceBookGO go) {
		return converter.toGO(service.update(converter.toModel(go)));
	}

	public void delete(SourceBookGO go) {
		service.delete(converter.toModel(go));
	}

}