package it.drwolf.base.utils;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Locale;
import java.util.TimeZone;

public class StartUp implements HasLogger {

	@ConfigProperty(name = "quarkus-utils.timezone", defaultValue = "UTC")
	String tz;
	@ConfigProperty(name = "quarkus-utils.locale", defaultValue = "en")
	String locale;

	void onStart(@Observes StartupEvent event) {
		TimeZone.setDefault(TimeZone.getTimeZone(tz));
		Locale.setDefault(Locale.forLanguageTag(locale));
		logger().infof("Timezone set to %s", tz);
	}

}
