package it.drwolf.base.utils;

import org.jboss.logging.Logger;

public interface HasLogger {
	default Logger logger() {
		return Logger.getLogger(this.getClass());
	}
	static Logger logger(Class<?> cls) {
		return Logger.getLogger(cls);
	}
}
