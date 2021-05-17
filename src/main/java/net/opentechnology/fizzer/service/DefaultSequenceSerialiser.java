package net.opentechnology.fizzer.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DefaultSequenceSerialiser implements SequenceSerialiser<String> {

	@Override
	public String serialise( final List<String> sequence ) {
		return String.join( "\n", sequence );
	}
}
