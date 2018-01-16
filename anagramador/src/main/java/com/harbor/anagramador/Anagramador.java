package com.harbor.anagramador;

/**
 * @author Jimmy Porto
 *
 */
public class Anagramador {
	
	private Logger logger;

	public Anagramador() {
		this(false);
	}
	
	public Anagramador(Boolean doLog) {
		this.logger = new Logger(doLog);
	}

}
