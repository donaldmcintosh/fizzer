package net.opentechnology.fizzer.service;

import net.opentechnology.fizzer.properties.SequenceProperties;

public interface Translator<T> {

	T getTranslation();

	T getTranslation( SequenceProperties config );

}
