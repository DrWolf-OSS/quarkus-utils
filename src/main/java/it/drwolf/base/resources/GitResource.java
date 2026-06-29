package it.drwolf.base.resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.drwolf.base.utils.HasLogger;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/git")
public class GitResource implements HasLogger {

	private static Map<String, Object> info = new HashMap<>();

	public static void loadInfo(InputStream inputStream) {
		try {
			info = new ObjectMapper().readValue(inputStream, Map.class);
		} catch (Exception e) {
			HasLogger.logger(GitResource.class).error(e.getMessage(), e);
		}
	}

	@PermitAll
	@GET
	public Map<String, Object> info() {
		return info;
	}
}
