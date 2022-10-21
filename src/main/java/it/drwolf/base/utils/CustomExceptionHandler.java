package it.drwolf.base.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<Exception>, HasLogger {
	@Override
	public Response toResponse(Exception e) {
		Response.ResponseBuilder builder;

		this.logger().error(e.getMessage(), e);

		if (e instanceof WebApplicationException) {
			WebApplicationException wae = (WebApplicationException) e;
			builder = Response.status(wae.getResponse().getStatus());
		} else {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.header("content-type", "plain/text").entity(e.getMessage()).build();
	}
}
