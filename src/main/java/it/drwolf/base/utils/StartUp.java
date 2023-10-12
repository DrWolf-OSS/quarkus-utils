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
		logger().infof("Java version: %s", Runtime.version());
		logger().infof("Processors: %d", Runtime.getRuntime().availableProcessors());
		logger().infof("Total Memory: %d MB", Runtime.getRuntime().totalMemory() / 1048576);
		logger().infof("Max Memory: %d MB", Runtime.getRuntime().maxMemory() / 1048576);
		logger().infof("Free Memory: %d MB", Runtime.getRuntime().freeMemory() / 1048576);
	}

}
