package SuperGhost;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Streams {
private static final String DICTIONARY_PATH = System.getProperty("user.dir")+File.separator+"DICTIONARY.txt";
	
	private static final int MIN_WORD_LENGTH = 6;

	/*
	 * This shows you the value of steams as by walking you through a situation in ghost
	 * 
	 * Lets pretend your the second player (You attempt to create odd length words to win)
	 * 
	 * The current fragment is "cow"
	 * 
	 * How do you decide what letter to play next?
	 */

	public static void main (String args[]) throws IOException {
		
		Set<String> allWords = new HashSet<>();
		
		Set<String> oddWords = new HashSet<>();
		
		Set<String> evenWords = new HashSet<>();
		
		Set<String> currentWords = new HashSet<>();
		
		Set<String> losingWords;
		
		boolean isFirstPlayer = true; //Lets me know if I am the first or second play, used for word set selection
				
		//Adding all the words in the dictionary text file to the allWords set
		Reader.getAllLines(DICTIONARY_PATH, allWords);
		
		String currentFragment = "cow";
		
		System.out.println("Loaded Dictionary");
		/*
		 * Parallel Stream means that this iteration will occur concurrently. Meaning the work
		 * of iterating over the set will be broken and given to multiple threads
		 * 
		 * Iterates over each word in the allWords set and invokes the invokes the block of code 
		 * provided. 
		 * 
		 * When this is complete the oddWords set will contain all the odd words and the
		 * evenWords set will contain all the even words
		 */
		allWords.parallelStream().forEach(word -> {
			if((word.length() & 1) == 0) {
				evenWords.add(word);
			}
			else {
				oddWords.add(word);
			}
		});
		
		System.out.println("Split Words");
		
		currentWords.clear();
		
		currentWords.addAll(isFirstPlayer ? oddWords : evenWords);
		
		losingWords = !isFirstPlayer ? oddWords : evenWords;
		
		losingWords.removeIf(word -> word.length() < Math.max(currentFragment.length(), MIN_WORD_LENGTH));
		
		currentWords.removeIf(word -> word.length() < Math.max(currentFragment.length(), MIN_WORD_LENGTH));
		
		System.out.println("Removed Small Words");
		
		losingWords.removeIf(word -> !formatedStartsWith(word, currentFragment));
		
		currentWords.removeIf(word -> !formatedStartsWith(word, currentFragment));
		
		System.out.println("Removed Non-Prefix Words");
		
		System.out.println("Winners Size: "+currentWords.size());
		
		System.out.println("Losers Size: "+losingWords.size());
		
		System.out.println("Total Iterations: "+(currentWords.size()*losingWords.size()));
		
		currentWords.removeIf(word -> containsSubword(word, losingWords));
		
		System.out.println("Removed all losing words");		
		
		System.out.println("");	
		
		//Lets Sort the list by size smallest first
		
		List<String> sortedList = new ArrayList<>(currentWords);
		
		Collections.sort(sortedList, (w1, w2) -> w1.length() - w2.length());
		
		sortedList.forEach(word -> System.out.println(word));
	}
	
	public static void addWordThatBeginWith(String word, String prefix, Collection<String> conduit) {
		if(formatedStartsWith(word, prefix)) {
			conduit.add(word);
		}
	}
	
	public static boolean formatedStartsWith(String word, String prefix) {		
		word = word.toLowerCase();
		
		prefix = prefix.toLowerCase();
		
		return word.startsWith(prefix);
	}
	
	public static boolean containsSubword(final String word, Collection<String> search) {		
		Optional<String> optional = search.parallelStream().filter(tempWord -> formatedStartsWith(word, tempWord)).parallel().findFirst();
		
		return optional.isPresent();
	}
}
