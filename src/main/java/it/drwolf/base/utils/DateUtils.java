package it.drwolf.base.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

import jakarta.ws.rs.BadRequestException;

public class DateUtils {

	public static Optional<Date> parseOptional(String isoString) {
		try {
			return Optional.ofNullable(isoString).map(DateUtils::parseDate);
		} catch (Exception e) {
			throw new BadRequestException(e);
		}
	}

	public static Date parse(String isoString) {
		return parseOptional(isoString).orElseThrow(BadRequestException::new);
	}

	private static Date parseDate(String isoString) {
		try {
			return Date.from(ZonedDateTime.parse(isoString).toInstant());
		} catch (DateTimeParseException ignored) {
			return Date.from(LocalDateTime.parse(isoString).toInstant(ZoneOffset.UTC));
		}
	}
}
