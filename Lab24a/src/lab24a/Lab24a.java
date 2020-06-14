/*
*  CIT242 Lab24a:   Add enhancements to linked list class.
*/
package lab24a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Lab24a {

    private static boolean verboseMode = false; // Debug aid (== false by default) 
    static String userInput;
    private static MyLinkedList<String> list;

    /**
     * Output information regarding user commands.
     */
    public static void outputHelpText() {

        System.out.println("    COMMAND  PARAMETER(S)   DESCRIPTION");
        System.out.println("    help                    Output this help text.");
        System.out.println("    contains string         Test if list contains string (incomplete).");
        System.out.println("    first    string         Get the first index of specified string (incomplete).");
        System.out.println("    get      index          Get the list item at specified index (incomplete).");
        System.out.println("    last     string         Get the last  index of specified string (incomplete).");
        System.out.println("    load     filename       Read input file into memory.");
        System.out.println("    print                   Output memory data to console.");
        System.out.println("    verbose                 Turn VERBOSE mode ON or OFF (optional feature).");
        System.out.println("    q                       exit the program.");
        System.out.println();
    }
//_________________________________________________________________________
// Main method.    

    public static void main(String[] args)
            throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("This program accepts the following inputs and performs the corresponding actions:");
        outputHelpText();
        do {
            System.out.print("Command: ");
            userInput = input.nextLine();
            if (Lab24a.getVerboseMode()) {
                System.out.printf("userInput=%s\n", userInput);
            }
            if (userInput.charAt(0) == 'h') {
                outputHelpText();
            } else if (userInput.toUpperCase().equals("VERBOSE")) {
                Lab24a.setOrClearVerboseMode();
                if (Lab24a.getVerboseMode()) {
                    System.out.println("Verbose mode is now ON.");
                } else {
                    System.out.println("Verbose mode is now OFF.");
                }
            } else if (userInput.charAt(0) != 'q') {
                processCommand(userInput.split("[ \n]"));
            }

        } while (userInput.charAt(0) != 'q');
        System.out.println("Exit program.");
    } // end main

    static void processCommand(String[] commandArgs) {
        String command;
        if (commandArgs.length < 1) {
            System.out.println("Ignoring empty command.");
        } else if (commandArgs[0].equalsIgnoreCase("LOAD")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: LOAD filename");
            } else {
                ArrayList<String> inputStrings = loadFromFile(commandArgs[1]);
                list = new MyLinkedList(inputStrings.toArray());
                
            }
        } else if (list == null) {
            System.out.printf("Please LOAD data file before attempting %s command.\n",
                    commandArgs[0]);
        } else if (commandArgs[0].equalsIgnoreCase("PRINT")) {
            System.out.println(list);
        } else if (commandArgs[0].equalsIgnoreCase("CONTAINS")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: CONTAINS string");
            } else {
                boolean result = list.contains(commandArgs[1]);
                System.out.printf("Result = %b for command: CONTAINS %s\n", result, commandArgs[1]);
            }
        } else if (commandArgs[0].equalsIgnoreCase("GET")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: GET index");
            } else {
                String result = list.get(Integer.parseInt(commandArgs[1]));
                System.out.printf("Result = %s for command: GET %s\n", result, commandArgs[1]);
            }
        } else if (commandArgs[0].equalsIgnoreCase("FIRST")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: FIRST string");
            } else {
                int result = list.indexOf(commandArgs[1]);
                System.out.printf("Result = %d for command: FIRST %s\n", result, commandArgs[1]);
            }
        } else if (commandArgs[0].equalsIgnoreCase("LAST")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: LAST string");
            } else {
                int result = list.lastIndexOf(commandArgs[1]);
                System.out.printf("Result = %d for command: LAST %s\n", result, commandArgs[1]);
            }
        } else {
            System.out.printf("Unknown command: %s\n", commandArgs[0]);
        }

    }// end processCommand

    /**
     * Read the contents of a text file into an ArrayList of String objects.
     *
     * @param fName = file name
     * @return
     */
    static ArrayList<String> loadFromFile(String fName) {
        File documentFile = new File(fName);
        ArrayList<String> contents;
        contents = new ArrayList<>();
        Scanner input;
        int lineNum = 0;
        try {
            input = new Scanner(documentFile);
            while (input.hasNextLine()) {
                String inputText = input.nextLine();
                if (Lab24a.getVerboseMode()) {
                    System.out.println("inputText=" + inputText);
                }
                lineNum++;
                contents.add(inputText.trim());
            }
            System.out.printf("%d records loaded.\n", lineNum);
        } catch (FileNotFoundException FNF) {
            System.out.printf("file not found: %s\n", fName);
        }
        return contents;
    }// end loadFromFile

//________________________________________________________________________________________
    /**
     *
     * @return the current value of the Boolean 'verboseMode' attribute.
     */
    static Boolean getVerboseMode() {
        return verboseMode;
    }

    //________________________________________________________________________________________
    /**
     *
     * @param value = new value for the Boolean 'verboseMode' attribute.
     */
    static void setVerboseMode(Boolean value) {
        verboseMode = value;
    }

    //________________________________________________________________________________________
    /**
     * Turn "verbose mode" ON (true) or OFF (false).
     */
    static void setOrClearVerboseMode() {
        if (verboseMode) {
            verboseMode = false;
        } else {
            verboseMode = true;
        }
    }
} // end class Lab24a

