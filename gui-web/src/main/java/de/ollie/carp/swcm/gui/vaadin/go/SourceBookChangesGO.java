package de.ollie.carp.swcm.gui.vaadin.go;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for source_book_changess data in the GUI layer.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class SourceBookChangesGO {

	private long id;
	private long recordId;
	private String attributeName;
	private LocalDateTime changeDate;
	private String newValue;

}