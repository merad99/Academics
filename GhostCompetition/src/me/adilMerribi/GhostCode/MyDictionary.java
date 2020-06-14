package me.adilMerribi.GhostCode;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class MyDictionary extends AbstractDictionary {
	
	String dictionaryFilePath;
	 
//	TextFileReader<Set<String>> MyfileReader;

	public MyDictionary(String path, TextFileReader<Set<String>> dictionaryFileReader) throws IOException {
		super(path, dictionaryFileReader);
		
 	
	}

	@Override
	public void setDictionaryFilePath(String path) throws IllegalArgumentException {
		if(path == null || path.isEmpty()) {
			  throw new IllegalArgumentException("File path is null or empty");
		}
		
		dictionaryFilePath = path;
				
	}
	

	@Override
	public String getDictionaryFilePath() {
		 
		return dictionaryFilePath;
	}
	

	@Override
	public int countWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	 
		
		Set<String> wordsstartswith = getWordsThatStartWith(prefix, size, ignoreCase);
		
		 int countWords = wordsstartswith.size();
		return countWords;
	}

	@Override
	public boolean containsWordsThatStartWith(String prefix, int size, boolean ignoreCase)
			throws IllegalArgumentException {
		
		Set<String> wordsStartWith;
		wordsStartWith=getWordsThatStartWith(prefix, size, ignoreCase); 
		if (!wordsStartWith.isEmpty())
				return true;
				 
		return false;
	}

	
	@Override
	public Set<String> getWordsThatStartWith(String prefix, int size, boolean ignoreCase)
			throws IllegalArgumentException {
		
		Set<String> wordsWithPrefix = null, currentWords;
		
		currentWords = getAllWords();
		
		for(String word : currentWords) {
		  if(ignoreCase) {
			word = word.toUpperCase();
			prefix = prefix.toUpperCase();
		  }
		  if (word.startsWith(prefix)){
			  wordsWithPrefix.add(word);
		  }
		}  
		
		return wordsWithPrefix;
	}
 
	
	public String getMyNextLetter(String prefix,boolean ourTurnFirst, boolean ignoreCase){
		
		int prefixLength = prefix.length();
		Set<String> wordsWithPrefixOdd = null;
		Set<String> wordsWithPrefixOdd_num = null;
		Set<String> wordsWithPrefixEven = null;
		Set<String> wordsWithPrefixEven_num = null;
		 
		int minLength= 4;
		int startPoint = Math.max(minLength, prefixLength+1); 
		
		for ( int i = startPoint; i< 26; i++) {
			if (isEven(i)) {
				wordsWithPrefixEven_num = getWordsThatStartWith(prefix,i,ignoreCase);  
				wordsWithPrefixEven.addAll(wordsWithPrefixEven_num);
			}
			else {
				wordsWithPrefixOdd_num = getWordsThatStartWith(prefix,i,ignoreCase);  
				wordsWithPrefixOdd.addAll(wordsWithPrefixOdd_num);
			}
			
		}
		
		Set<String> wordsToRemove = null;
		String myLetter = null;
		String firstWord= null;
		//if we are first, = even
		if (ourTurnFirst == true) {
			
			for (String wordToCheck : wordsWithPrefixOdd) { 				 
				wordsToRemove = getWordsThatStartWithFromAnotherSet(wordToCheck, wordToCheck.length(), wordsWithPrefixEven, ignoreCase);	
			   if(wordsToRemove.size() > 0) {
				   wordsWithPrefixEven.removeAll(wordsToRemove);
			   }
			}
		 
		 // take letter from string according to prefix length
			 
			 firstWord = (String) wordsWithPrefixEven.toArray()[0];
		//	myLetter=firstWord.substring( prefixLength+1,  prefixLength+1);
			myLetter = Character.toString(firstWord.charAt(prefixLength + 1));
			
	 	}
		else {
			for (String wordToCheck : wordsWithPrefixEven) { 				 
				wordsToRemove = getWordsThatStartWithFromAnotherSet(wordToCheck, wordToCheck.length(), wordsWithPrefixOdd, ignoreCase);	
			   if(wordsToRemove.size() > 0) {
				   wordsWithPrefixOdd.removeAll(wordsToRemove);
			   }
			}
		 
		 // take letter from string according to prefix length
			 firstWord = (String) wordsWithPrefixOdd.toArray()[0];
			 myLetter = Character.toString(firstWord.charAt(prefixLength + 1));
			
		}
		
		 return myLetter;
	}
	
	
	public Set<String> getWordsThatStartWithFromAnotherSet(String prefix, int size, Set<String> setToLook, boolean ignoreCase)
			throws IllegalArgumentException {
		
		Set<String> wordsWithPrefix = null, currentWords;
		
		currentWords = setToLook;
		
		for(String word : currentWords) {
		  if(ignoreCase) {
			word = word.toUpperCase();
			prefix = prefix.toUpperCase();
		  }
		  if (word.startsWith(prefix)){
			  wordsWithPrefix.add(word);
		  }
		}  
		
		return wordsWithPrefix;
	}
	
	public static boolean isEven (int num) {
	    return (num & 1) == 0;
	}
	

}
