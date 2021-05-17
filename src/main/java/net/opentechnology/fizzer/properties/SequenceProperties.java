package net.opentechnology.fizzer.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "fizz")
public class SequenceProperties {

	@Min(1)
	private int start = 1;

	@Min(1)
	private int max;
	private Map<Integer, String> magicNumbers = new HashMap<>();
	private List<FeatureFlag> features = new ArrayList<>();

	public int getStart() {
		return start;
	}

	public void setStart( int start ) {
		this.start = start;
	}

	public int getMax() {
		return max;
	}

	public void setMax( int max ) {
		this.max = max;
	}

	public Map<Integer, String> getMagicNumbers() {
		return magicNumbers;
	}

	public void setMagicNumbers( Map<Integer, String> magicNumbers ) {
		this.magicNumbers = magicNumbers;
	}

	public List<FeatureFlag> getFeatures() {
		return features;
	}

	public void setFeatures( final List<FeatureFlag> features ) {
		this.features = features;
	}
}
