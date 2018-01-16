package com.harbor.anagramador;

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
	 * This methods print in console the log that the user want to print.
	 * 
	 * @param values
	 */
	public void show(String... values) {
		if (doLog) {
			for (String value : values) {
				System.err.println(value);
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
		times[counter ? 0 : 1] = System.currentTimeMillis();
		counter = !counter;
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

	public void showTime() {
		String log = "No time to show";
		if (times.length == 2) {
			log = "Time spent: " + (times[1] - times[0]) + "milliseconds.";
		}
		show(log);
	}

}
