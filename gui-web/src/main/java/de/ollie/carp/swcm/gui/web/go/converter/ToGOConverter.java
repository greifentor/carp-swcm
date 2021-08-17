package de.ollie.carp.swcm.gui.web.go.converter;

/**
 * An interface for to GO converters.
 * 
 * @param <GO>    The type of the GO's.
 * @param <MODEL> The type of the models.
 *
 * @author ollie (16.08.2021)
 */
public interface ToGOConverter<GO, MODEL> {

	GO toGO(MODEL model);

}