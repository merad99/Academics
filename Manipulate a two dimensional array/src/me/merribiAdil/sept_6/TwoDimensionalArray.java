package me.merribiAdil.sept_6;

import java.util.Random;



public class TwoDimensionalArray {

	public static void main(String[] args) {
		// Create the array
				Random random = new Random();

				int[][] twoDimensionalArray = { { random.nextInt(4), random.nextInt(4), random.nextInt(4) }, { random.nextInt(4), random.nextInt(4), random.nextInt(4) }, { random.nextInt(4), random.nextInt(4), random.nextInt(4) } };
                
				
				int count = 0;
				for(int[] subArray: twoDimensionalArray) {
					
					for(int i=0; i < twoDimensionalArray.length; i++) {
						
						int value = subArray[i];
						
						System.out.println("\t" + subArray[i]);
						
						if (value == 3) {
						count++;
					    }
						if (value == 0) {
							subArray[i] = 7;
						}
					}
					
				}
				
				System.out.println("\n the 3 occur " + count + " time in the 2-D array");
				
				
				for(int[] subArray: twoDimensionalArray) {
					
					for(int i=0; i < twoDimensionalArray.length; i++) {
						
						
						System.out.println("\t" + subArray[i]);

					}
					
				}
					
				


	}

}
