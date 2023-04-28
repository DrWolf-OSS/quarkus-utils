package it.drwolf.base.filters;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.Optional;

public class QueryFilter {

	@RouteFilter(1000)
	void filter(RoutingContext rc) {
		Optional.ofNullable(rc.request().getParam("token")).ifPresent(token -> {
			if (!ConfigProvider.getConfig()
					.getOptionalValue("quarkus-utils.disable-query-filter", Boolean.class)
					.orElse(false)) {
				rc.request().headers().add("Authorization", "Bearer %s".formatted(token));
			}
		});
		rc.next();
	}
}