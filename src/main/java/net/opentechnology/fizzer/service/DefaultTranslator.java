package net.opentechnology.fizzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.opentechnology.fizzer.properties.SequenceProperties;

@Component
public class DefaultTranslator implements Translator<String> {

	private final SequenceTranslator translator;
	private final SequenceSerialiser<String> serialiser;

	@Autowired
	public DefaultTranslator( final SequenceTranslator translator,
			final SequenceSerialiser<String> serialiser ) {
		this.translator = translator;
		this.serialiser = serialiser;
	}

	public String getTranslation() {
		return serialiser.serialise( translator.parseSequence() );
	}

	public String getTranslation( SequenceProperties config ) {
		return serialiser.serialise( translator.parseSequence( config ) );
	}

}
