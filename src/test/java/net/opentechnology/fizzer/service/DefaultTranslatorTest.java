package net.opentechnology.fizzer.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultTranslatorTest {

	public static final String OUTPUT = "blah\nnblah";
	@Mock
	private SequenceTranslator translator;

	@Mock
	private SequenceSerialiser<String> serialiser;

	@Mock
	private List<String> translated;

	@InjectMocks
	private DefaultTranslator defaultTranslator;

	@Test
	@DisplayName( "Confirm translation calls expected collaborators" )
	void getTranslation() {
		when( translator.parseSequence() ).thenReturn( translated );
		when( serialiser.serialise( translated ) ).thenReturn( OUTPUT );

		assertThat( defaultTranslator.getTranslation(), equalTo( OUTPUT ) );

		verify( translator, times( 1 ) ).parseSequence();
		verify( serialiser, times( 1 ) ).serialise( translated );
	}
}