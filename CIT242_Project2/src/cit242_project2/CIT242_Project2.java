/*
 *** @author: Adil Merribi                                    ***
 *** @date : March 28, 2019                                   ***
 *** @course: CIT 242                                         ***
 *** @assignment : Project 2  Matching Parentheses,           ***
 *** Curly Braces, and Square Brackets                        ***
 *** @discription: This program will perform the out put by   ***
 *** given file name, and it will print the data from file    ***
 *** and will show the result.                                ***
 *** @professor: Morgan                                       ***
 */
package cit242_project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CIT242_Project2 {

    
    
    
// ArrayList is created to store information from the text file
    private static ArrayList<String> list = new ArrayList<String>();
    private static int errorCount = 0;
    private static GenericStack<Occurence> stack = new GenericStack<Occurence>();

    public static void main(String[] args) {
        boolean restartProgram = true;
        
        //Scanner class object created


        Scanner scannner = new Scanner(System.in);
        while (restartProgram) {
            System.out.print("InputFilename(or 'q' to exit): ");
            String fileName = scannner.nextLine();
            try {
                if (fileName.equals("q")) {
                    restartProgram = false;
                } else {
                    Scanner scan = new Scanner(new File(fileName));
                    //Loops till data available
                    while (scan.hasNextLine()) {
                        //Reads data from file and stores it

                        String line = scan.nextLine();
                        CIT242_Project2.addToList(line);
                    }//End of while
                    CIT242_Project2.completeAll();
                }
                //Catch block to handle file not found exception
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
        System.out.println("Exit program.");
    }
    
        public static void showFileInfo() {
        int sizeOfList = 0;
        sizeOfList = list.size();
        System.out.println(sizeOfList + " records loaded.");
        int num = 0;
        for (String text : list) {
            num++;
            System.out.println("   " + num + ": " + text);
        }
    }
        
    public static void completeAll() {
        showFileInfo();
        balanceCheck();
        deleteStack();
        resetAll();
    }



    public static void balanceCheck() {
        for (int i = 0; i < list.size(); i++) {

            // adds a 1 to the line number so it starts at 1 instead of 0
            int lineNum = i + 1;
            String str = list.get(i);
            for (int j = 0; j < str.length(); j++) {
                // checking each character 
                char c = str.charAt(j);
                // check for left delimiter 
                if (c == '{' || c == '(' || c == '[') {
                    // create a new Occurrence object to place in the stack
                    stack.push(new Occurence(i, j, c));
                } else if (c == '}' || c == ')' || c == ']') {
                    if (stack.isEmpty()) {
                        System.out.println("ERROR: stack is empty" + ", but input data= " + "'" + c + "' at line " + lineNum + ", position " + j + ".");
                        errorCount++; // increment error count
                    } else {
                        Occurence occur = stack.peek();
                        // check if balanced
                        if (occur.isBalanced(c)) {
                            System.out.println("MATCH: '" + c + "' at line " + lineNum + ", position " + j + " balances " + "'" + occur.getLeftValue() + "' at " + occur + ".");
                            // balance is found so pop first in stack
                            stack.pop();
                        } else {
                            System.out.println("MISMATCH: '" + c + "' at line " + lineNum + ", position " + j + " does NOT balance " + "'" + occur.getLeftValue() + "' at " + occur + ", expected= " + "'" + occur.getExpectedRightValue() + "'.");
                            errorCount++; // increment error count
                        }
                    }

                }

            }
        }
    }

    // fully delete remaining stacks
    public static void deleteStack() {

        while (!stack.isEmpty()) {

            Occurence occur = stack.peek();
            System.out.println("Unresolved Occurence: " + occur + ": " + occur.getLeftValue() + " " + occur.getExpectedRightValue() + ".");
            // pop remaining stacks(if any) in a loop
            stack.pop();
            // increment error count
            errorCount++;
        }
        //
        System.out.println(getErrorCount() + " error(s) found.");
    }

    // full reset of variables 
    public static void resetAll() {
        list.clear();
        errorCount = 0;
    }

    public static int getErrorCount() {
        return errorCount;
    }

    public static void addToList(String line) {
        list.add(line);
    }

}// End of class
