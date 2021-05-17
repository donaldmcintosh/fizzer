package net.opentechnology.fizzer.service;

import static net.opentechnology.fizzer.properties.FeatureFlag.ENABLE_MATCH_NUMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import net.opentechnology.fizzer.properties.FeatureFlag;
import net.opentechnology.fizzer.properties.SequenceProperties;

@Component
public class SequenceTranslator {

	private final SequenceProperties yamlConfig;

	public SequenceTranslator( SequenceProperties sequenceProperties ) {
		this.yamlConfig = sequenceProperties;
	}

	public List<String> parseSequence() {
		return parseSequence( yamlConfig );
	}

	public List<String> parseSequence( SequenceProperties config ) {
		List<String> translation = new ArrayList<>();
		for ( int i = config.getStart(); i <= config.getMax(); i++ ) {
			translation.add( translate( i, config.getMagicNumbers(), config.getFeatures() ) );
		}

		return translation;
	}

	private String translate( int i, Map<Integer, String> magicNumbers,
			List<FeatureFlag> features ) {
		String translated = magicNumbers.keySet()
				.stream()
				.sorted()
				.filter( num -> matches( i, num, features ) )
				.map( num -> magicNumbers.get( num ) )
				.reduce( "", ( running, next ) -> running + next );
		return !StringUtils.isBlank( translated ) ? translated : Integer.toString( i );
	}

	private boolean matches( final int i, final Integer num, List<FeatureFlag> features ) {
		return i % num == 0 || ( numberMatchEnabled( features ) && Integer.toString( i )
				.contains( num.toString() ) );
	}

	private boolean numberMatchEnabled( List<FeatureFlag> features ) {
		return features != null && features.contains( ENABLE_MATCH_NUMBER );
	}

}
