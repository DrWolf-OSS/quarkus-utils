package it.drwolf.base.utils;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import java.util.TimeZone;

public class StartUp implements HasLogger {
	void onStart(@Observes StartupEvent event) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		logger().infof("Timezone set to UTC");
	}

}
