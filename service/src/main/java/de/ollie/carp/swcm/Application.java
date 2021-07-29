package de.ollie.carp.swcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application starter class (service only).
 *
 * @author ollie (29.07.2021)
 */
@SpringBootApplication
@ComponentScan("de.ollie")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}