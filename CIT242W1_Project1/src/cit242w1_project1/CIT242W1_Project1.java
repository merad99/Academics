/*
* CIT-242 Project 1: RGB Color Grid
* Student: Adil Merribi
* Date: 02/17/2019
* Description: This program will Display RGB Color Array.
*/
package cit242w1_project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CIT242W1_Project1 extends Application {
    
    static int Height = 500;
    static int Width = 500;
    static int Padding = 10;
    
    static int NUM_ROWS = 5;
    static int NUM_COLS = 5;
    // Array index values for primary colors
    static int Rx = 0;
    static int Gx = 1;
    static int Bx = 2;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    
        try {
            
            //Setting height and width
            primaryStage.setHeight(Height);
            primaryStage.setMaxWidth(Width);
            
            //Pane to arrange grid
            GridPane grid = new GridPane();
            grid.setHgap(Padding / 2);
            grid.setVgap(Padding / 2);
            
            //Calculating the radius of one circle 
            double radius = ((Width / NUM_COLS) - Padding) / 2;
            
            //Creating an array to display
            Circle[][] displayGrid = new Circle[NUM_ROWS][NUM_COLS];
            int[][][] RGB_Data = new int[NUM_ROWS][NUM_COLS][3];
            
            //Scanning and Reading the input file
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the file name: ");
            
            //Getting file name
            String fileName = scanner.nextLine();
            File file = new File(fileName);
            
            //Gathering Informations
            scanner = new Scanner(file);
            for (int i = 0; i < NUM_ROWS; i++){
                for(int j = 0; j < NUM_COLS; j++){
                    String line = scanner.nextLine();
                    
                    //Spliting the lines
                    String[] rgb = line.split(",");
                    
                    //Extracting and converting color values
                    int r = Integer.parseInt(rgb[Rx].trim());
                    int b = Integer.parseInt(rgb[Bx].trim());
                    int g = Integer.parseInt(rgb[Gx].trim());
                    
                    //Adding data to the three dimensional array
                    RGB_Data[i][j][Rx] = r;
                    RGB_Data[i][j][Bx] = b;
                    RGB_Data[i][j][Gx] = g;
                    
                }
            }
            
            //Display color data
            for (int i = 0; i < NUM_ROWS; i++){
                for(int j = 0; j < NUM_COLS; j++){
                    System.out.printf("%d,%d: %d, %d, %d ", i, j, RGB_Data[i][j][Rx], RGB_Data[i][j][Gx], RGB_Data[i][j][Bx]);
                    
                    //initializing the circle array
                    Color C = Color.rgb(RGB_Data[i][j][Rx], RGB_Data[i][j][Gx], RGB_Data[i][j][Bx]);
                    displayGrid[i][j] = new Circle(radius, C);
                    
                    //Adding circles to the grid
                    grid.add(displayGrid[i][j], j, i);
                }
                
                System.out.println("");
            }
           
            StackPane stack = new StackPane();
            
            //Ading the grid
            stack.getChildren().add(grid);
            Scene scene = new Scene (stack, 500, 500);
            primaryStage.setTitle(" RGB Color Grid");
            primaryStage.setScene(scene);
            primaryStage.show();

                  
        } catch (FileNotFoundException ex) {

            System.err.println("file not found");
            
        }catch (NumberFormatException e) {

            System.err.println(e.getMessage());

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
