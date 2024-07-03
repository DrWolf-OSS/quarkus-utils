package it.drwolf.base.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<Exception>, HasLogger {

	@ConfigProperty(name = "quarkus-utils.stack-trace-limit",
			defaultValue = "20")
	Integer limit;

	@Override
	public Response toResponse(Exception e) {
		Response.ResponseBuilder builder;

		HttpServerRequest request = ResteasyProviderFactory.getInstance().getContextData(HttpServerRequest.class);

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));

		String logTxt = sw.toString();

		if (!logTxt.contains("SRJWT07000")) {
			this.logger()
					.error(Stream.concat(Stream.of(request.method() + " " + request.uri()), logTxt.lines().limit(limit))
							.collect(Collectors.joining("\n")));
		}

		if (e instanceof WebApplicationException wae) {
			builder = Response.status(wae.getResponse().getStatus());
		} else {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.header("content-type", "plain/text").entity(e.getMessage()).build();
	}
}
