package me.adilMerribi.GhostCode;
 
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.HashSet;
 
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class MyFileReader implements TextFileReader<Set<String>>
{
	
	
		/**
		 * Reads all the text in text file at the specified path.
		 * @param path the location of a file that the text will be read
		 * @return A string of all the text contained in the specified text file
		 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
		 */
		public String readText(String path) throws IOException{
			
	
	        // This will reference one line at a time
	        String line = null, theStory = "";
	      

	        try {
	 		BufferedReader br = new BufferedReader(new FileReader(path)); 
 			
	 			while ((line = br.readLine()) != null) {
	 				theStory += line + "\n";
	 			}
	          //   System.out.println(theStory);
	              br.close();         
	        }
	       
	        catch(IOException ex) {
	            System.out.println("Error reading file '"+ path + "'");                  
	     
	        }
			
			return theStory;
		}
		
		/**
		 * Reads all the lines of the text file at the specified path. Adds each line of the text to the a collection and returns that collection.
		 * @param <T>
		 * @param <T>
		 * @param path the location of a file that the text will be read
		 * @return A collection of all the lines of text contained in the specified text file
		 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
		 */
		public  Set<String> getAllLines(String path) throws IOException{
			
		  Set<String> result = new HashSet<String>();
		  
		  try {
		  Scanner in = new Scanner(new File(path));
		  	 
				while (in.hasNextLine()) {
					String line = in.nextLine();
					result.add(line);
				}
		  }	
			  	
		     catch(IOException ex) {
		            System.out.println("Error reading file '"+ path + "'");                  
		     
		        }
		  
		
		  
	       return result;
		}
		
		/**
		 * Reads the last non-empty (contains at least one character) line of the file at the specified path
		 * @param path the location of a file that the string will be written to
		 * @return The text of the last non-empty (Meaning contains at least one characters) line of the specified file
		 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
		 */
		public String getLastLine(String path) throws IOException{
			
			 String lastLine = "";
			 String sCurrentLine;

			    
			    try {
			        
			 		BufferedReader br = new BufferedReader(new FileReader(path)); 
			         
		 			
			 		 while ((sCurrentLine = br.readLine()) != null) 
					    {
					      //  System.out.println(sCurrentLine);
					        lastLine = sCurrentLine;
					    }
			              br.close();         
			        }
			       
			        catch(IOException ex) {
			            System.out.println("Error reading file '"+ path + "'");                  
			     
			        }
			    
			    return lastLine;
		}

	
	

}