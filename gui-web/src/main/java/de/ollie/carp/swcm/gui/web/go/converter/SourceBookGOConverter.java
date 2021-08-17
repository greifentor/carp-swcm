package de.ollie.carp.swcm.gui.web.go.converter;

import javax.inject.Named;

import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.gui.web.go.SourceBookGO;

/**
 * A converter for source book GO's.
 *
 * @author ollie (16.08.2021)
 */
@Named
public class SourceBookGOConverter implements ToGOConverter<SourceBookGO, SourceBook> {

	public SourceBookGO toGO(SourceBook model) {
		if (model == null) {
			return null;
		}
		return new SourceBookGO()
				.setId(model.getId())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName())
				.setOriginalName(model.getOriginalName())
				.setToken(model.getToken());
	}

	public SourceBook toModel(SourceBookGO go) {
		if (go == null) {
			return null;
		}
		return new SourceBook()
				.setId(go.getId())
				.setGlobalId(go.getGlobalId())
				.setName(go.getName())
				.setOriginalName(go.getOriginalName())
				.setToken(go.getToken());
	}

}