package de.ollie.carp.swcm.core.model;

import java.util.List;

import lombok.Data;
import lombok.Generated;

/**
 * A page of content objects.
 *
 * @param <CONTENT> The type of the objects which are to return with the page.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Data
@Generated
public class Page<CONTENT> {

	private List<CONTENT> entries;
	private int entriesPerPage;
	private long entriesTotal;

}