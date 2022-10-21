package it.drwolf.base.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/git")
public class GitResource {
	@GET
	public Map<String, Object> info() {
		try {
			return new ObjectMapper().readValue(this.getClass().getClassLoader().getResource("git.json"), Map.class);
		} catch (IOException e) {
			return new HashMap<>();
		}
	}
}
