package de.ollie.carp.swcm.gui.web.configuration;

import de.ollie.carp.corelib.service.AppConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration for the application.
 *
 * @author ollie (11.08.2021)
 */
@Configuration
public class AppConfigurationImpl implements AppConfiguration {

	@Value("${app.version}")
	private String appVersion;

	@Override
	public String getName() {
		return "CARP-SWCM";
	}

	@Override
	public String getVersion() {
		return appVersion;
	}

}
