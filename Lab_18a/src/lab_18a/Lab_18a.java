/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_18a;

import javafx.application.Application;
import java.util.Scanner;
import javafx.stage.Stage;

/**
 *
 * @author adilmerribi
 */
public class Lab_18a extends Application {
  
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number to find GCD");
        int number1 = scanner.nextInt();
        
        System.out.println("Enter the second number to find GCD");
        int number2 = scanner.nextInt();
        
        System.out.println("The Greatest Common Divisor of the number " + number1 + " and number " + number2 + " is " + recursionGCD(number1,number2));
    }
    
    
    private static int recursionGCD(int number1, int number2) {
        System.out.println("Entering 'gcd' method :  m=" + number1 + ", n=" + number2);
        int r = number1 % number2;
        
        if(r==0){
            System.out.println("Returning 'gcd' value = " + number2 + " (BASE CASE:       m=" + number1 + ", n=" + number2 + " )"  );
            return number2;
        }
        else 
        {
            int tempData = 0;
            tempData = recursionGCD(number2, r);
            System.out.println("Returning 'gcd' value = " + tempData + " (recursive case:  m=" + number1 + ", n=" + number2 + ")");
            return tempData;
        }        
        
    }
    
    @Override
    
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }
    
}
