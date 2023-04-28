package it.drwolf.base.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.drwolf.base.utils.HasLogger;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

	@GET
	public Map<String, Object> info() {
		return info;
	}
}
