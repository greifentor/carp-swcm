package de.ollie.carp.swcm.gui.web.go;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A container for source book data in the GUI layer.
 *
 * @author ollie (16.08.2021)
 */
@Accessors(chain = true)
@Data
public class SourceBookGO {

	private long id;
	private String globalId;
	private String name;
	private String originalName;
	private String token;

}