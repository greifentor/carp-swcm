package de.ollie.carp.swcm.persistence.converter;

import java.util.stream.Collectors;

import javax.inject.Named;

import de.ollie.carp.swcm.core.model.Page;

import lombok.AllArgsConstructor;
import lombok.Generated;

/**
 * A class to convert a repository page to a service layer page object.
 *
 * @param <CONTENT> The type of the page content.
 * @param <DBO>     The type of the DBO's which are representing CONTENT objects in the persistence layer.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@AllArgsConstructor
@Named
public class PageConverter<CONTENT, DBO> {

	private final ToModelConverter<CONTENT, DBO> toModelConverter;

	public Page<CONTENT> convert(org.springframework.data.domain.Page<DBO> page) {
		if (page == null) {
			return null;
		}
		return new Page<CONTENT>()
				.setEntries(page.getContent().stream().map(toModelConverter::toModel).collect(Collectors.toList()))
				.setEntriesPerPage(page.getSize())
				.setEntriesTotal(page.getTotalElements());
	}

}