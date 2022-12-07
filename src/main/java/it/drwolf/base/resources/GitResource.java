package it.drwolf.base.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.drwolf.base.utils.HasLogger;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Path("/git")
public class GitResource implements HasLogger {

	public static void loadInfo(InputStream inputStream){
		try {
			info =new ObjectMapper().readValue(inputStream,Map.class);
		} catch (Exception e) {
			HasLogger.logger(GitResource.class).error(e.getMessage(),e);
		}
	}

	private static Map<String, Object> info = new HashMap<>() ;

	@GET
	public Map<String, Object> info() {
			return info;
	}
}
