import java.lang.Integer;
import java.util.Map;
import java.util.Scanner;
import cs310.util.HashMap1;
import cs310.util.HashMap2;
import cs310.util.HashMap3;

public class FrequencyCounter
{
    // Do not instantiate.
    private FrequencyCounter() { }
     /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     */

    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);  // key-length cutoff
	Map<String, Integer> map = new HashMap3<String, Integer>();
        Scanner scan = new Scanner(System.in); // uses whitespace delim.
        while (scan.hasNext()) { // Build symbol table and count frequencies
	    String word = scan.next();
	    if (word.length() < minLen) continue;
	    if (!map.containsKey(word)) map.put(word, 1);
	    else map.put(word, map.get(word) + 1);
	}
        // find a key with highest frequency count
        int maxCount = 0;  // using two variables
        String maxWord = "";  //... instead of trick
	for (String word: map.keySet()) {
	    if (map.get(word)> maxCount) {
		maxCount = map.get(word);
		maxWord = word;
	    }
	}
	System.out.println(maxWord + " " + maxCount);
    }
}
