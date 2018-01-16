package com.harbor.anagramador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.harbor.anagramador.exceptions.AnagramadorExceptions;

/**
 * @author Jimmy Porto
 *
 */
public class Anagramador {
	
	private Logger logger;
	private List<Object> words;
	private static final Random RANDOM = new Random();
	private String wordKey;
	private String[] letters;

	public Anagramador() {
		this(false);
	}
	
	public Anagramador(Boolean doLog) {
		this.logger = new Logger(doLog);
	}
	
	/**
	 * This method generate an anagram with a minimum and maximum word size already configured.
	 * 
	 * @param minSize 3
	 * @param maxSize 7
	 */
	public void generate() {
		generate(3,7);
	}
	/**
	 * This method generate an anagram with a minimum and maximum word size that can be configured.
	 * 
	 * @param minSize
	 * @param maxSize
	 */
	public void generate(Integer minSize, Integer maxSize) {
		try {
			wordKey = getWordWithSize(maxSize);
			logger.show(wordKey);
			letters = wordKey.split("|");
			logger.show(letters);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method return a word with the sent parameter size.  
	 * 
	 * @param maxSize
	 * @return
	 * @throws IOException 
	 */
	private String getWordWithSize(Integer maxSize) throws IOException {
		logger.show(getClass().getResource("pt").getFile());
		words = Files.lines(new File(getClass().getResource("pt").getFile()).toPath()).collect(Collectors.toList());
		List<String> foundWords = new ArrayList<String>();
		for (Object value : words) {
			String word = (String) value;
			if(word.replace("-", "").length() == maxSize) {
				foundWords.add(word);
			}
		}
		if(foundWords.isEmpty()) {
			throw new AnagramadorExceptions("Not found a word with size: " + maxSize);
		}
		return foundWords.get(RANDOM.nextInt(foundWords.size()));
	}

}
