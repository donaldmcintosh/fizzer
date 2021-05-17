package net.opentechnology.fizzer.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import static net.opentechnology.fizzer.properties.FeatureFlag.ENABLE_MATCH_NUMBER;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.opentechnology.fizzer.properties.SequenceProperties;

@ExtendWith(MockitoExtension.class)
public class SequenceTranslatorTest {

	@Mock
	private SequenceProperties sequenceProperties;
	private SequenceTranslator sequenceTranslator;

	@BeforeEach
	public void before() {
		sequenceTranslator = new SequenceTranslator( sequenceProperties );
	}

	@Test
	@DisplayName("Confirm configured sequence works as expected for single translation")
	public void testParseSequence_successOneTranslation() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 2, "Fizz" );

		when( sequenceProperties.getStart() ).thenReturn( 1 );
		when( sequenceProperties.getMax() ).thenReturn( 10 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );

		List<String> translated = sequenceTranslator.parseSequence();

		assertThat( translated.get( 0 ), equalTo( "1" ) );
		assertThat( translated.get( 1 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 2 ), equalTo( "3" ) );
		assertThat( translated.get( 3 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 4 ), equalTo( "5" ) );
		assertThat( translated.get( 5 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 6 ), equalTo( "7" ) );
		assertThat( translated.get( 7 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 8 ), equalTo( "9" ) );
		assertThat( translated.get( 9 ), equalTo( "Fizz" ) );
	}

	@Test
	@DisplayName(
			"Confirm configured sequence works as expected for two translations no intersection")
	public void testParseSequence_successTwoTranslationsNoIntersection() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 2, "Fizz" );
		translations.put( 7, "Buzz" );

		when( sequenceProperties.getStart() ).thenReturn( 1 );
		when( sequenceProperties.getMax() ).thenReturn( 10 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );

		List<String> translated = sequenceTranslator.parseSequence();

		assertThat( translated.get( 0 ), equalTo( "1" ) );
		assertThat( translated.get( 1 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 2 ), equalTo( "3" ) );
		assertThat( translated.get( 3 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 4 ), equalTo( "5" ) );
		assertThat( translated.get( 5 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 6 ), equalTo( "Buzz" ) );
		assertThat( translated.get( 7 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 8 ), equalTo( "9" ) );
		assertThat( translated.get( 9 ), equalTo( "Fizz" ) );
	}

	@Test
	@DisplayName(
			"Confirm configured sequence works as expected for two translations with intersection")
	public void testParseSequence_successTwoTranslationsWithIntersection() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 3, "Buzz" );
		translations.put( 2, "Fizz" );

		when( sequenceProperties.getStart() ).thenReturn( 1 );
		when( sequenceProperties.getMax() ).thenReturn( 10 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );

		List<String> translated = sequenceTranslator.parseSequence();

		assertThat( translated.get( 0 ), equalTo( "1" ) );
		assertThat( translated.get( 1 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 2 ), equalTo( "Buzz" ) );
		assertThat( translated.get( 3 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 4 ), equalTo( "5" ) );
		assertThat( translated.get( 5 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 6 ), equalTo( "7" ) );
		assertThat( translated.get( 7 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 8 ), equalTo( "Buzz" ) );
		assertThat( translated.get( 9 ), equalTo( "Fizz" ) );
	}

	@Test
	@DisplayName("Confirm sequence works as expected when config passed in")
	public void testParseSequence_successConfigPassedIn() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 2, "Fizz" );

		when( sequenceProperties.getStart() ).thenReturn( 1 );
		when( sequenceProperties.getMax() ).thenReturn( 10 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );

		List<String> translated = sequenceTranslator.parseSequence( sequenceProperties );

		assertThat( translated.size(), equalTo( 10 ) );

		assertThat( translated.get( 0 ), equalTo( "1" ) );
		assertThat( translated.get( 1 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 2 ), equalTo( "3" ) );
		assertThat( translated.get( 3 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 4 ), equalTo( "5" ) );
		assertThat( translated.get( 5 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 6 ), equalTo( "7" ) );
		assertThat( translated.get( 7 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 8 ), equalTo( "9" ) );
		assertThat( translated.get( 9 ), equalTo( "Fizz" ) );
	}

	@Test
	@DisplayName("Confirm sequence works as expected when feature ENABLE_MATCH_NUMBER enabled")
	public void testParseSequence_successEnableMatchNumberEnabled() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 2, "Fizz" );
		translations.put( 4, "Buzz" );

		when( sequenceProperties.getStart() ).thenReturn( 15 );
		when( sequenceProperties.getMax() ).thenReturn( 25 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );
		when( sequenceProperties.getFeatures() ).thenReturn(
				Collections.singletonList( ENABLE_MATCH_NUMBER ) );

		List<String> translated = sequenceTranslator.parseSequence( sequenceProperties );

		assertThat( translated.size(), equalTo( 11 ) );

		assertThat( translated.get( 0 ), equalTo( "15" ) );
		assertThat( translated.get( 1 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 2 ), equalTo( "17" ) );
		assertThat( translated.get( 3 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 4 ), equalTo( "19" ) );
		assertThat( translated.get( 5 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 6 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 7 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 8 ), equalTo( "Fizz" ) );
		assertThat( translated.get( 9 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 10 ), equalTo( "Fizz" ) );
	}

	@Test
	@DisplayName("Confirm sequence works as expected when feature ENABLE_MATCH_NUMBER enabled and no factors")
	public void testParseSequence_successEnableMatchNumberEnabledNoFactors() {
		Map<Integer, String> translations = new HashMap<>();
		translations.put( 2, "Fizz" );
		translations.put( 4, "Buzz" );

		when( sequenceProperties.getStart() ).thenReturn( 420 );
		when( sequenceProperties.getMax() ).thenReturn( 425 );
		when( sequenceProperties.getMagicNumbers() ).thenReturn( translations );
		when( sequenceProperties.getFeatures() ).thenReturn(
				Collections.singletonList( ENABLE_MATCH_NUMBER ) );

		List<String> translated = sequenceTranslator.parseSequence( sequenceProperties );

		assertThat( translated.size(), equalTo( 6 ) );

		assertThat( translated.get( 0 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 1 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 2 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 3 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 4 ), equalTo( "FizzBuzz" ) );
		assertThat( translated.get( 5 ), equalTo( "FizzBuzz" ) );
	}

}