package it.drwolf.base.utils;

import javax.ws.rs.BadRequestException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class DateUtils {
	public static Optional<Date> parseOptional(String isoString){
		try {
			return Optional.ofNullable(isoString).map(s -> Date.from(ZonedDateTime.parse(s).toInstant()));
		} catch (Exception e) {
			throw new BadRequestException(e);
		}
	}
	public static Date parse(String isoString){
		return parseOptional(isoString).orElseThrow(BadRequestException::new);
	}
}
