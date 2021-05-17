package net.opentechnology.fizzer.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.opentechnology.fizzer.properties.SequenceProperties;

@Configuration
@EnableConfigurationProperties(SequenceProperties.class)
@ComponentScan(basePackages = { "net.opentechnology.fizzer.properties",
								"net.opentechnology.fizzer.service" })
public class AppConfig {

}
