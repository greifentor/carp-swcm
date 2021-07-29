package de.ollie.carp.swcm.core.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A model for source_book_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class SourceBookChanges {

	private long id;
	private long recordId;
	private String attributeName;
	private LocalDateTime changeDate;
	private String newValue;

}