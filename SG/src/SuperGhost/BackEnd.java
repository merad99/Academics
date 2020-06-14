package SuperGhost;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javafx.application.Platform;
import sharedCode.AddLocation;
import sharedCode.GameAction;
import sharedCode.SharedGameData;

public class BackEnd implements Runnable{
	
   char letter;
   static AddLocation location;
	private GUI gui;
	static String fragment;
	static String shared_File_Path;
	static FileManager fileManager;   
	static Random randomizer;
	SharedGameData currentGameData;
	static String teamName;
	private static String DICTIONARY_PATH = System.getProperty("user.dir")+File.separator+"Dictionary.txt";
	private Set<String> allWords;
	private static final int MIN_WORD_LENGTH = 6;
	 	
	public BackEnd( String sharedFilePath, String teamNameOrg){
		shared_File_Path = sharedFilePath;
		teamName=teamNameOrg;
		letter = '\0';
		fragment="";
		allWords = new HashSet<>();
	}
	
	
	public void run(){
		
        boolean ourTurnFirst = false;
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String fullalphabet = alphabet.toLowerCase();
	    Random random = new Random();
	    char code = '\0';
	    System.out.println( "lena DICTIONARY_PATH " + DICTIONARY_PATH);
	    try {
			Reader.getAllLines(DICTIONARY_PATH, allWords);
		 
			
			
		} catch (IOException e3) {
		 
			e3.printStackTrace();
		}
	  		
		try {
			fileManager = new FileManager(shared_File_Path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		randomizer = new Random();

		System.out.println(teamName +" STARTED");
		
		while(true) {
			try {
				fileManager.update();
			} catch (IllegalStateException e2) {
		 		e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			if (fileManager.hasChanged()) {
				//We want to get the most recent version of the file
				try {
					currentGameData = fileManager.read();
				} catch (ClassNotFoundException e1) {
 					e1.printStackTrace();
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if(fileManager.isGameOver(currentGameData)) {
					System.out.println(teamName +" TERMINATED");
					System.exit(0);
				}
				else if(fileManager.isMyTurn(teamName, currentGameData)) {
					//Update the current word fragment this is useful for when deciding the next letter to play
					updateWordFragment(currentGameData);
					
					
					if (fragment.length() == 0) {	
						ourTurnFirst=true;
						letter = fullalphabet.charAt(random.nextInt(9));
						location= AddLocation.Front;
						 System.out.println("Location: ");
						 System.out.println(location);
				
					}
					else {
												 
						try {
							//Retrieve the next letter and set location
							letter = getNextLetter(ourTurnFirst);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
 							
					}

					 boolean isFront=false;
					 if (location == AddLocation.Front )
						 isFront = true;
			
					updateRecommendation(letter, isFront);
					 				
				}
			}
		}
	}
	
	 	public char getNextLetter(boolean isFirstPlayer) throws IOException {
	 		
			Set<String> oddWords = new HashSet<>();
			Set<String> evenWords = new HashSet<>();
			Set<String> currentWords = new HashSet<>();
			Set<String> losingWords= new HashSet<>();
			char winningLetter = '\0';
			int indexFirstLetter=0;
			int sizeEnd = 0;
			int winningIndex=0;
			
			location= AddLocation.Back;
		 ;
			
			allWords.parallelStream().forEach(word -> {
				if((word.length() & 1) == 0) {
					evenWords.add(word);
				}
				else {
					oddWords.add(word);
				}
			});
			
			
		//	System.out.println("even Words: ");
			
		//	System.out.println(evenWords);
			

			//System.out.println("odd Words: ");
			
			//System.out.println(oddWords);
				
			
			currentWords.clear();
			
			currentWords.addAll(!isFirstPlayer ? oddWords : evenWords);   
			losingWords.addAll(isFirstPlayer ? oddWords : evenWords);
			
			
			//System.out.println("current Words: ");
			
		//	System.out.println( currentWords);
			
         //   System.out.println("losing Words: ");
			
		//	System.out.println( losingWords);
			
		//	System.out.println("Removed Small Words");
			
			losingWords.removeIf(word -> word.length() < Math.max(fragment.length(), MIN_WORD_LENGTH));
			currentWords.removeIf(word -> word.length() < Math.max(fragment.length(), MIN_WORD_LENGTH));
			
			System.out.println("Removed Non-contains Words");
			
			losingWords.removeIf(word -> !formatedContained(word, fragment));
			currentWords.removeIf(word -> !formatedContained(word, fragment));
			
			/* System.out.println("current Words: ");
			
				System.out.println( currentWords);
				
	            System.out.println("losing Words: ");
				
				System.out.println( losingWords);
				
				System.out.println("Removed Small Words");*/
								
			System.out.println("Winners Size: "+ currentWords.size());
			System.out.println("Losers Size: "+ losingWords.size());
			
	        System.out.println("Removed all losing words");		
			System.out.println("");	
			
			currentWords.removeIf(word -> containsSubword(word, losingWords));
			
			// System.out.println("current Words after contain: ");
				
			//	System.out.println( currentWords);
			//Lets Sort the list by size smallest first
			
			List<String> sortedListWinning = new ArrayList<>(currentWords);
			Collections.sort(sortedListWinning, (w1, w2) -> w1.length() - w2.length());
			
			//sortedListWinning.forEach(word -> System.out.println(word));
			
			List<String> sortedListLosing = new ArrayList<>(losingWords);
			Collections.sort(sortedListLosing, (w1, w2) -> w1.length() - w2.length());
			
			//sortedListLosing.forEach(word -> System.out.println(word));
	
			 
			String winningWord ="";
			
	 		 if (currentWords.size() > 0) {
	 			winningWord = sortedListWinning.get(0);
	 		 			 
	 		 }
	 		 else if (sortedListLosing.size() > 0){
	 			winningWord = sortedListLosing.get(0);
	 			 
	 		 }else {
	 			 String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				 
	 		    String fullalphabet = alphabet.toLowerCase();
	 		    Random random = new Random();
 		  	    winningLetter = fullalphabet.charAt(random.nextInt(9));
	 		 }
	 			if (winningLetter =='\0') {
	 				 indexFirstLetter=winningWord.indexOf(fragment);
	 				 sizeEnd =  winningWord.length() - indexFirstLetter - fragment.length();
	 				 
	 				 if (indexFirstLetter > 0 && sizeEnd > 0 && indexFirstLetter < sizeEnd ) {
	 					 winningIndex = indexFirstLetter - 1;
	 					location= AddLocation.Front;
	 				 }
	 				 else if(indexFirstLetter > 0 && sizeEnd > 0 && indexFirstLetter > sizeEnd ) {
	 					winningIndex = fragment.length();
	 					location= AddLocation.Back;
	 				 }
	 				 
	 				 else if(indexFirstLetter == 0) {
	 					winningIndex = fragment.length();
	 					location= AddLocation.Back;
	 				 }
	 				 else {
	 					winningIndex = indexFirstLetter - 1;
	 					location= AddLocation.Front;
	 				 }
	 				winningLetter = winningWord.charAt(winningIndex);	 
	 					 
	 			}
	 			 
	 			System.out.println("lena fragment: ");
	 			
				System.out.println( fragment);
	 			 System.out.println(" lena winning word: ");
	 			
					System.out.println( winningWord);
	 		
					 System.out.println(" lena winning letter: ");
			 			
						System.out.println( winningLetter);
			/*  if we want random...
			  String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			 
		    String fullalphabet = alphabet.toLowerCase();
		    Random random = new Random();
			winningLetter = fullalphabet.charAt(random.nextInt(9));  
			 */
			 
			return winningLetter;
			
			
		}

		public static boolean formatedContained(String word, String curFragment) {		
			word = word.toLowerCase();
			curFragment = curFragment.toLowerCase();
		//	return word.startsWith(prefix);
			return word.contains(curFragment);
		}
		
		public static boolean containsSubword(final String word, Collection<String> search) {		
			Optional<String> optional = search.parallelStream().filter(tempWord -> formatedContained(word, tempWord)).parallel().findFirst();
			return optional.isPresent();
		}

		public String getFragment() {
			return fragment;
		} 
		
		public void findNextLocation() {
			boolean isFront = randomizer.nextBoolean();
			setLocation(isFront);
			  
		}
		public void changeFragment(){
		  Platform.runLater(new Runnable() {

					@Override
					public void run() {
		     //   gui.changeFragment(fragment);
					}
			});
		}
		

		public void updateRecommendation(char letter, boolean isFront ){
		    		    
		    Platform.runLater(new Runnable() {

				@Override
				public void run() {
					//gui.updateRecommendation(letter,   isFront);
				}
		});
		    
		}

		public void PlayLetter(char letter, AddLocation position){
		 			
			GameAction gameAction = createNextAction(location, letter);
			 
			//Add the new game action to the current game shared data
			currentGameData.addAction(gameAction);
			
			boolean isFront =  gameAction.getAddLocation().equals(AddLocation.Front);

		//	updateRecommendation(letter, isFront);
			
			//Save game shared data
			try {
				fileManager.write(currentGameData);
			} catch (IllegalStateException e) {
				 
				e.printStackTrace();
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
			
			updateWordFragment(currentGameData);
			changeFragment();

		}
		
		
		public static GameAction createNextAction(AddLocation locationToAdd, char letterToAdd) {

		
			 System.out.println(teamName);
			 System.out.println("\t LETTER: "+letterToAdd);
			 System.out.println("\t LOCATION: "+locationToAdd);
			 System.out.println();

			return new GameAction(locationToAdd, letterToAdd, teamName);
		}
		
		public static void updateWordFragment(SharedGameData shareGameData) {
              
			fragment = "";
			
			shareGameData.gameActions().forEach(action -> {
				boolean isFront = action.getAddLocation().equals(AddLocation.Front);
           //     char addletter
				String letter = String.valueOf(action.getLetter());  
				 
				 fragment = isFront ? letter+fragment : fragment+letter;
				 
			});
		}
		
		 
		
		public static void setLocation(boolean isFront) {
			location = isFront ? AddLocation.Front : AddLocation.Back;
		}
		
		public AddLocation getLocation() {
			return location;
		}

		public void setGui(GUI gui){
			this.gui = gui;
		}

}
	
	
