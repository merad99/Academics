package me.adilMerribi.GhostCode;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

 

public class Main {
	public static void main(String[] args) throws Exception {
		
		MyFileReader myReaderDic = new MyFileReader();
		
		MyDictionary dictionary = new MyDictionary("~/Desktop/Dictionary.txt", myReaderDic);
		//MyDictionary dictionary = new MyDictionary("Dictionary.txt",  myReaderDic);
		
		String sharedFilePath = args[0];
		System.out.println(sharedFilePath);
		MyFileMonitor monitoring = new MyFileMonitor(sharedFilePath);
		String lastline = null;
		 
		Set<String> ourAllWords = dictionary.getAllWords();
		String teamName = "Patrick";
		TurnParser myparser = new TurnParser(teamName);
		MyFileReader myReader = new MyFileReader();
		MyFileWriter myWriter = new MyFileWriter();
		 
		
		String ourStringWord = null;
		String lastletter = null;
		String nextline = null;
		String mynextletter = null; //our next letter
		boolean ourTurnFirst = false;
		
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String fullalphabet = alphabet.toLowerCase();
	    Random random = new Random();

	    char code = '\0';

	    
		
		while(true) {
			
			monitoring.update();
			if (monitoring.hasChanged()) {
				 lastline=myReader.getLastLine(sharedFilePath);
				 
				 if (myparser.isGameOver(lastline) == false) {
					if( myparser.isMyTurn(lastline) == true ) {
				    //  if (ourStringWord == null) {
					   if (ourStringWord.length() == 0) {	
							ourTurnFirst=true;
							code = fullalphabet.charAt(random.nextInt(9));
							mynextletter=  Character.toString(code);
						}
						//add code  call function below. Check it was called correctly:
						if (!ourStringWord.isEmpty()) { 
						   mynextletter= dictionary.getMyNextLetter(ourStringWord,  ourTurnFirst,true);
						}
						nextline = myparser.getNextLine(mynextletter);
						myWriter.writeToFile(nextline, sharedFilePath);
						ourStringWord = ourStringWord + mynextletter;
						
					}
					else {
						if(myparser.didOtherPlayerFinishTurn(lastline) == true ) {
							lastletter = myparser.getOtherPlayersLetter(lastline);
							 ourStringWord = ourStringWord + lastletter;
						  //  ourStringWord.append( lastletter);
						}
					}
					 
					 
					 
					 
					 
				 }
				 else {
					 //exit
				 }
				 
				 
			}
			 
				
			
		}
 
	}

	 

}
