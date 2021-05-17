package net.opentechnology.fizzer.service;

import java.util.List;

public interface SequenceSerialiser<T> {

	T serialise( List<String> sequence );

}
