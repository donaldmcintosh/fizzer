package bdd.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;

import net.opentechnology.fizzer.config.AppConfig;
import net.opentechnology.fizzer.properties.FeatureFlag;
import net.opentechnology.fizzer.properties.SequenceProperties;
import net.opentechnology.fizzer.service.Translator;

@CucumberContextConfiguration
@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest
public class Stepdefs {

	/*
	Normally we would inject a client to a remote public API but, in this case,
	our public API output is just a string list for printing to STDOUT, so we just inject
	the translator.  Unusual, but valid for this case I think.
	 */
	@Autowired
	private Translator<String> translator;

	// State
	private SequenceProperties sequenceProperties;
	private String output;

	@Before
	public void resetState(){
		output = null;
		sequenceProperties = new SequenceProperties();
	}

	@Given("sequence starts at {int}")
	public void setStart( int start ) {
		sequenceProperties.setStart( start );
	}

	@And("sequence ends at {int}")
	public void setMax( int max ) {
		sequenceProperties.setMax( max );
	}

	@And("magic number {int} translates to {word}")
	public void setMagicNumber( int magic, String convert ) {
		sequenceProperties.getMagicNumbers().put(magic, convert);
	}

	@And("feature {word} is enabled")
	public void enableFeature( String feature ) {
		sequenceProperties.getFeatures().add( FeatureFlag.valueOf( feature ));
	}

	@When("run translation")
	public void runTranslation( ) {
		output = translator.getTranslation(sequenceProperties);
	}

	@Then("output should match")
	public void matchOutput(String expectation){
		assertThat(output, equalTo(expectation));
	}

}
