package it.drwolf.base.utils;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<Exception>, HasLogger {





	@Override
	public Response toResponse(Exception e) {
		Response.ResponseBuilder builder;


		HttpServerRequest request = ResteasyProviderFactory.getInstance().getContextData(HttpServerRequest.class);

		//this.logger().error(e.getMessage());
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		this.logger().error(Stream.concat(Stream.of(request.uri()),sw.toString().lines().limit(10)).collect(Collectors.joining("\n")) );

		if (e instanceof WebApplicationException) {
			WebApplicationException wae = (WebApplicationException) e;
			builder = Response.status(wae.getResponse().getStatus());
		} else {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.header("content-type", "plain/text").entity(e.getMessage()).build();
	}
}
