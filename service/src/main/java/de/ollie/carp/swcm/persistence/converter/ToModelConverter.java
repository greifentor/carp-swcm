package de.ollie.carp.swcm.persistence.converter;

/**
 * An interface for converter which are able to convert DBO to model objects.
 *
 * @param <MODEL> The type of the model objects.
 * @param <DBO>   The type of the DBO's which are representing CONTENT objects in the persistence layer.
 *
 *                GENERATED CODE !!! DO NOT CHANGE !!!
 */
public interface ToModelConverter<MODEL, DBO> {

	MODEL toModel(DBO dbo);

}