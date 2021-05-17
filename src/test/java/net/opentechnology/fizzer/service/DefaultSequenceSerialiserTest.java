package net.opentechnology.fizzer.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultSequenceSerialiserTest {

	@Test
	@DisplayName( "Confirm test is returned separate by new lines" )
	void testSerialise_success() {
		DefaultSequenceSerialiser defaultSequenceSerialiser = new DefaultSequenceSerialiser();
		List<String> inList = new ArrayList<>();
		inList.add("red");
		inList.add("green");
		inList.add("blue");

		assertThat(defaultSequenceSerialiser.serialise( inList  ), equalTo("red\ngreen\nblue"));
	}
}