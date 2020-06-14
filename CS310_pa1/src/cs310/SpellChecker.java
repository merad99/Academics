package cs310;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

//  cd cs310/pa1/src
//  javac -d ../bin cs310/*.java
//  java cs310.SpellChecker ../src/cs310/testfile ../src/cs310/words

public class SpellChecker {
    public static void main(String[] args) throws FileNotFoundException {
    	
        HashSet<String> dictionary = new HashSet<String>();
        
        //get the dictionary words.txt
        Scanner scan = new Scanner(new File(args[1]));
        while (scan.hasNextLine())
            dictionary.add(scan.nextLine());

        HashMap<String, ArrayList<Integer>> wrongMap = new HashMap<String, ArrayList<Integer>>();
        
        //get the inputfile 
        Scanner input = new Scanner(new File(args[0]));
        int lineNum = 1;
        while (input.hasNextLine()) { //continue to end of file
            String line = input.nextLine();
            Scanner scanLine = new Scanner(line);
            while (scanLine.hasNext()) { //continue to end of line
                String word = scanLine.next().toLowerCase();

                if (!dictionary.contains(word)) {
                    if (!wrongMap.containsKey(word))
                        wrongMap.put(word, new ArrayList<Integer>());
                    wrongMap.get(word).add(lineNum);
                }
            }
            lineNum++;
        }

        for (Map.Entry<String, ArrayList<Integer>> entry : wrongMap.entrySet()) {
            StringBuilder output = new StringBuilder();
            output.append(entry.getKey() + "\t is incorrect on lines: ");
            for (int i = 0; i < entry.getValue().size(); i++)
                output.append(entry.getValue().get(i) + ","); //add line number


            /*  a. Add one character
                b. Remove one character
                c. Exchange adjacent characters.
            */
            output.append("\t and the suggested correction is: ");
            StringBuilder word = new StringBuilder(entry.getKey());
            int i = 0;
            boolean keepGoing = true;
            while (i < word.length()-1 && keepGoing){ //swap characters
                char temp = word.charAt(i);
                word.setCharAt(i,word.charAt(i+1));
                word.setCharAt(i+1,temp);
                if (dictionary.contains(word.toString()))
                    keepGoing = false;
                else
                    word.replace(0,word.length(),entry.getKey()); //if not a match, undo the change
                i++;
            }
            i = 0;
            while (i < word.length() && keepGoing){ //remove one character
                word.deleteCharAt(i);
                if (dictionary.contains(word.toString()))
                    keepGoing = false;
                else
                    word.replace(0,word.length(),entry.getKey()); //if not a match, undo the change
                i++;
            }
            i = 0;
            while (i <= word.length() && keepGoing){ //add one character
                char newChar = 'a';
                while (newChar <= 'z' && keepGoing) {
                    word.insert(i,newChar);
                    if (dictionary.contains(word.toString()))
                        keepGoing = false;
                    else
                        word.replace(0, word.length(), entry.getKey()); //if not a match, undo the change
                    newChar++;
                }
                i++;
            }
            if (keepGoing)
                word.replace(0,word.length(),"<NONE>");
            output.append(word.toString());
            System.out.println(output.toString());
        }
    }
}
