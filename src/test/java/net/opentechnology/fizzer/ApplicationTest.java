package net.opentechnology.fizzer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.opentechnology.fizzer.config.AppConfig;

@ContextConfiguration(classes = { Application.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({ "integration"})
class ApplicationTest {

	@Autowired
	private Application app;

	@Autowired
	private ApplicationContext ctx;

	@Test
	public void testApplication(){
		// Check everything wires up and it runs without exceptions
		app.commandLineRunner( ctx );
	}

}