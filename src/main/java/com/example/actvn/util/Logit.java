package com.example.actvn.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.*;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;


public class Logit {
	public static final String DEFAULT_LOG4J = "log4j.properties";
	private static String propertiesFilename;
	private Log log;
	public static Log errorLog;
	private static final AtomicBoolean enableLog = new AtomicBoolean(true);
	static final String ERROR_LOG = "smd.med.ext.eco.api.ErrorMonitor";
	private final String clazzName;
	static {
		resetLog4j();
	}

	public static void resetLog4j() {
		resetLog4j(false);
	}

	public static void resetLog4j(boolean enableSystemErrorLog) {
		final String log4jName = System.getProperty("log4jName");
		if (log4jName == null || log4jName.length() == 0) {
			propertiesFilename = DEFAULT_LOG4J;
		} else {
			propertiesFilename = log4jName;
		}
		Properties p = null;
		try {
			p = PropertiesUtils.getProperties(propertiesFilename);
			if (enableSystemErrorLog) {
				errorLog = LogFactory.getLog(ERROR_LOG);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		if (p != null) {
			enableLog.set("TRUE".equalsIgnoreCase(p.getProperty("log4j.enable.log", "true")));
			PropertyConfigurator.configure(p);

		} else {
			System.err.println("Logit could not find " + propertiesFilename);
			System.err.println("Logit will use default configuration");
			BasicConfigurator.configure();
		}
	}

	/**
	 * Get an instance of Logit.
	 *
	 * @param classInstance
	 *            Class to hold the instance.
	 * @retrun An instance of Logit.
	 */
	public static Logit getInstance(Class<?> classInstance) {
		return new Logit(classInstance.getName());
	}

	/**
	 * Get an instance of Logit.
	 *
	 * @param className
	 *            to hold the instance.
	 * @retrun An instance of Logit.
	 */
	public static Logit getInstance(String className) {
		return new Logit(className);
	}

	/**
	 * Constructor with a class name.
	 *
	 * @param className
	 *            Class name to hold the instance.
	 */
	private Logit(String className) {
		log = LogFactory.getLog(className);
		this.clazzName = className;
	}

	/**
	 * Check if DEBUG priority enabled.
	 *
	 * @return true if enabled, false otherwise.
	 */
	public boolean ison() {
		return log.isDebugEnabled();
	}

	/**
	 * Log a text debug message.
	 *
	 * @param formatter
	 *
	 */
	public void debug(String formatter, Object... args) {
		if (enableLog.get() && log.isDebugEnabled()) {
			log.debug(String.format(formatter, args));
		}
	}

	/**
	 * Log a text debug message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void debug(String message) {
		if (enableLog.get() && log.isDebugEnabled()) {
			log.debug(message);
		}
	}

	/**
	 * Log a object debug message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void debug(Object message) {
		if (enableLog.get() && log.isDebugEnabled()) {
			log.debug(message);
		}
	}

	/**
	 * Log a text debug message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void debug(String message, Throwable t) {
		if (enableLog.get() && log.isDebugEnabled()) {
			log.debug(message, t);
		}
	}

	/**
	 * Log a object debug message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void debug(Object message, Throwable t) {
		if (enableLog.get() && log.isDebugEnabled()) {
			log.debug(message, t);
		}
	}

	public void info(String formatter, Object... args) {
		if (enableLog.get() && log.isInfoEnabled()) {
			log.info(String.format(formatter, args));
		}
	}

	/**
	 * Log a text info message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void info(String message) {
		if (enableLog.get() && log.isInfoEnabled()) {
			log.info(message);
		}
	}

	/**
	 * Log a object info message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void info(Object message) {
		if (enableLog.get() && log.isInfoEnabled()) {
			log.info(message);
		}
	}

	/**
	 * Log a text info message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void info(String message, Throwable t) {
		if (enableLog.get() && log.isInfoEnabled()) {
			log.info(message, t);
		}
	}

	/**
	 * Log a object info message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void info(Object message, Throwable t) {
		if (enableLog.get() && log.isInfoEnabled()) {
			log.info(message, t);
		}
	}

	public void warn(String formatter, Object... args) {
		if (enableLog.get() && log.isWarnEnabled()) {
			log.warn(String.format(formatter, args));
		}
	}

	/**
	 * Log a text warn message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void warn(String message) {
		if (enableLog.get() && log.isWarnEnabled()) {
			log.warn(message);
		}
	}

	/**
	 * Log a object warn message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void warn(Object message) {
		if (enableLog.get() && log.isWarnEnabled()) {
			log.warn(message);
		}
	}

	/**
	 * Log a text warn message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void warn(String message, Throwable t) {
		if (enableLog.get() && log.isWarnEnabled()) {
			log.warn(message, t);
		}
	}

	/**
	 * Log a object warn message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void warn(Object message, Throwable t) {
		if (enableLog.get() && log.isWarnEnabled()) {
			log.warn(message, t);
		}
	}

	private void systemErrorLog(Object message) {
		if (errorLog != null) {
			errorLog.error(String.format("%s - %s", clazzName, String.valueOf(message)));
		}
	}

	private void systemErrorLog(Object message, Throwable t) {
		if (errorLog != null) {
			errorLog.error(String.format("%s - %s", clazzName, String.valueOf(message)), t);
		}
	}

	public void error(String formatter, Object... args) {
		log.error(String.format(formatter, args));
		systemErrorLog(String.format(formatter, args));
	}

	/**
	 * Log a text error message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void error(String message) {
		log.error(message);
		systemErrorLog(message);
	}

	/**
	 * Log a object error message.
	 *
	 * @param message
	 *            Message to log.
	 */
	public void error(Object message) {
		log.error(message);
		systemErrorLog(message);
	}

	/**
	 * Log a text error message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void error(String message, Throwable t) {
		log.error(message, t);
		systemErrorLog(message, t);
	}

	public void error(Throwable t) {
		log.error(t.getMessage(), t);
		systemErrorLog(t.getMessage(), t);
	}

	/**
	 * Log a object error message with a stack trace.
	 *
	 * @param message
	 *            Message to log.
	 * @param t
	 *            Throwable instance to log.
	 */
	public void error(Object message, Throwable t) {
		log.error(message, t);
		systemErrorLog(message, t);
	}

	public void alwaysLog(Object message, Throwable t) {
		log.info(message, t);
	}

	public void alwaysLog(Object message) {
		log.info(message);
	}

	public void alwaysLog(String message) {
		log.info(message);
	}

	public String getAppenderFile() {
		FileAppender fileAppender = null;
		@SuppressWarnings("rawtypes")
		Enumeration appenders = Logger.getRootLogger().getAllAppenders();
		while (appenders.hasMoreElements()) {

			Appender currAppender = (Appender) appenders.nextElement();
			if (currAppender instanceof FileAppender) {
				fileAppender = (FileAppender) currAppender;
			}
		}

		if (fileAppender != null) {
			return fileAppender.getFile();
		}
		return null;
	}

	public static boolean isEnableLog() {
		return enableLog.get();
	}

	public static void setEnableLog(boolean enable) {
		enableLog.set(enable);
	}


}
