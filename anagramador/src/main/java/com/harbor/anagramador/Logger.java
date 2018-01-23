package com.harbor.anagramador;

import java.util.List;

/**
 * @author Jimmy Porto
 *
 */
public class Logger {

	private Boolean doLog;
	private Long[] times = new Long[2];
	private Boolean counter = false;

	public Logger(Boolean doLog) {
		super();
		this.doLog = doLog;
	}
	
	/**
	 * This methods print in console the log that the developer want to print.
	 * 
	 * @param values
	 */
	public void show(List<Object> values) {
		if (doLog) {
			for (Object value : values) {
				System.err.println("Log_" + System.currentTimeMillis() + ".:" + value);
			}
		}
	}

	/**
	 * This methods print in console the log that the developer want to print.
	 * 
	 * @param values
	 */
	public void show(String... values) {
		if (doLog) {
			for (String value : values) {
				System.err.println("Log_" + System.currentTimeMillis() + ".:" + value);
			}
		}
	}

	/**
	 * This method add a time of now.
	 * If the times already have two values, the method will override the next position (0,1) like a cycle.
	 * 
	 * @param timeInMillis
	 */
	public void time() {
		time(System.currentTimeMillis());
	}
	
	/**
	 * This method add a time that the user want to save.
	 * If the times already have two values, the method will override the next position (0,1) like a cycle.
	 * 
	 * @param timeInMillis
	 */
	public void time(Long timeInMillis) {
		times[counter ? 0 : 1] = timeInMillis;
		counter = !counter;
	}

	/**
	 * This method print in console the time spent over the times added to be calculate.
	 */
	public void showTime() {
		String log = "No time to show";
		if (times.length == 2) {
			log = "Time spent: " + (times[1] - times[0]) + "milliseconds.";
		}
		show(log);
	}
	
	/**
	 * This method print in console the time spent over the times added to be calculate.
	 * And add a message to show with.
	 */
	public void showTime(String message) {
		String log = "No time to show";
		if (times.length == 2) {
			log = "Time spent: " + (times[1] - times[0]) + "milliseconds.";
		}
		show(message + " - " + log);
	}

}
