package com.harbor.anagramador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
	private List<Object> wordsToWork;

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
			findWords(minSize, maxSize);
			logger.show(wordsToWork);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method find the words of the anagram.
	 * 
	 * @param minSize
	 * @param maxSize
	 */
	private void findWords(Integer minSize, Integer maxSize) {
		wordsToWork = words.stream().filter(w -> {
			String word = w.toString();
			return word.length() >= minSize
					&& word.length() <= maxSize
					&& hasAllNeedLetters(word)
					&& !hasMoreLettersThatCan(word);
		}).collect(Collectors.toList());
		wordsToWork.sort((w1,w2) -> w1.toString().length() - w2.toString().length());
		
	}
	
	/**
	 * Verify if the word have all the needed letters.
	 * 
	 * @param word
	 * @return
	 */
	private boolean hasAllNeedLetters(String word) {
		boolean have = true;
		for (String letter : word.split("|")) {
			if(!wordKey.contains(letter)) {
				have = false;
			}
		}
		return have;
	}
	

	/**
	 * Verify if the word have more letters that can be have.
	 * 
	 * @param word
	 * @return
	 */
	private boolean hasMoreLettersThatCan(String word) {
		Map<String, Long> values = Arrays.asList(letters).stream()
		        .collect(Collectors.groupingBy(String::toString,Collectors.counting()));
				
				Map<String, Long> valuesSS = Arrays.asList(word.split("|")).stream()
						.collect(Collectors.groupingBy(String::toString,Collectors.counting()));
		boolean hasMore = false;
		for (String letter : values.keySet()) {
			if(valuesSS.get(letter) != null && valuesSS.get(letter) > values.get(letter)) {
				hasMore = true;
			}
		}
		return hasMore;
	}
	
	/**
	 * Get the diferent letters of the word
	 * 
	 * @param letters
	 * @return
	 */
	private List<String> getDifLetters(String[] letters) {
		List<String> difLetter = new ArrayList<>();
		for (String letter : letters) {
			if(!difLetter.contains(letter)) {
				difLetter.add(letter);
			}
		}
		
		return difLetter;
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

	/**
	 * Get the wordKey.
	 * 
	 * @return
	 */
	public String getWordKey() {
		return wordKey;
	}

	/**
	 * Get the letters.
	 * 
	 * @return
	 */
	public String[] getLetters() {
		return letters;
	}

	/**
	 * Get the Words to work.
	 * 
	 * @return
	 */
	public List<Object> getWordsToWork() {
		return wordsToWork;
	}

}
