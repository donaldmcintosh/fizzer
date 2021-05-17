package net.opentechnology.fizzer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.opentechnology.fizzer.service.DefaultTranslator;
import net.opentechnology.fizzer.service.Translator;

@Configuration
@SpringBootApplication(scanBasePackages = { "net.opentechnology.fizzer.config" })
public class Application {

	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication( Application.class );
		app.run( args );
	}

	@Bean
	public CommandLineRunner commandLineRunner( ApplicationContext ctx ) {
		return args -> {
			Translator<String> translator = ctx.getBean( DefaultTranslator.class );
			System.out.println( translator.getTranslation() );
		};
	}

}
