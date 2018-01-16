package com.harbor.anagramador;

/**
 * @author Jimmy Porto
 *
 */
public class Logger {

	private Boolean doLog;

	public Logger(Boolean doLog) {
		super();
		this.doLog = doLog;
	}
	
	public void show(String... values){
		if(doLog) {
			for (String value : values) {
				System.err.println(value);
			}
		}
	}

}
