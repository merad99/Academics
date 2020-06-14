/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cit242_lab25a;

/*
*  CIT242 Lab25a:   Binary Search Tree
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CIT242_Lab25a {

    static String userInput;
    static EnhancedBST<String> tree;

    /**
     * Output information regarding user commands.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void outputHelpText() {

        System.out.println("    COMMAND  PARAMETER(S)   DESCRIPTION");
        System.out.println("    help                    Output this help text.");
        System.out.println("    contains string         Test if tree contains string.");
        System.out.println("    height                  Output tree height (incomplete).");
        System.out.println("    inorder                 Execute IN-order traversal.");
        System.out.println("    load     filename       Read input file into memory.");
        System.out.println("    postorder               Execute POST-order traversal.");
        System.out.println("    preorder                Execute PRE-order traversal.");
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
            if (Debug.getVerboseMode()) {
                System.out.printf("userInput=%s\n", userInput);
            }
            if (userInput.equalsIgnoreCase("help")) {
                outputHelpText();
            } else if (userInput.toUpperCase().equals("VERBOSE")) {
                Debug.setOrClearVerboseMode();
                if (Debug.getVerboseMode()) {
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
                tree = new EnhancedBST();
                for (int i = 0; i < inputStrings.size(); i++) {
                    tree.insert(inputStrings.get(i));                    
                }
            }
        } else if (tree == null) {
            System.out.printf("Please LOAD data file before attempting %s command.\n",
                    commandArgs[0]);
        } else if (commandArgs[0].equalsIgnoreCase("INORDER")) {
            tree.inorder();
            System.out.println();
        } else if (commandArgs[0].equalsIgnoreCase("PREORDER")) {
            tree.preorder();
            System.out.println();
        } else if (commandArgs[0].equalsIgnoreCase("POSTORDER")) {
            tree.postorder();
            System.out.println();
        } else if (commandArgs[0].equalsIgnoreCase("CONTAINS")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: CONTAINS string");
            } else {
                boolean result = tree.contains(commandArgs[1]);
                System.out.printf("Result = %b for command: CONTAINS %s\n", result, commandArgs[1]);
            }
        } else if (commandArgs[0].equalsIgnoreCase("HEIGHT")) {
            if (commandArgs.length < 2) {
                System.out.println("Usage: HEIGHT string");
            } else {
                if (Debug.getVerboseMode()) {
                    System.out.printf("calling height(%s)\n", commandArgs[1]);
                }
                int height = tree.height(commandArgs[1]);
                System.out.printf("Height of \"%s\" node = %d.\n", commandArgs[1], height);
            }
        } else if (commandArgs[0].equalsIgnoreCase("WIDTH")) {
         int width = tree.getMaxWidth();
            System.out.println("Width of tree = " + width);
            } else {
            System.out.printf("Unknown command: %s\n", commandArgs[0]);
        }

    }
    
    

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
                if (Debug.getVerboseMode()) {
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

} // end class Lab25a

