/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab14a;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

/**
 *
 * @author adilmerribi
 */
public class Lab14a extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Pane pane = new Pane();
        double paneWidth = 200;
        double paneHeight = 200;
        double width = paneWidth * 0.90 - 30;
        double height = paneWidth * 0.90 - 60;

        // Draw a rectangle 1
        Rectangle r1 = new Rectangle(10, 60, width, height);
        r1.setFill(new Color(1, 1, 1, 0));
        r1.setStroke(Color.BLACK);
        pane.getChildren().add(r1);
        
        // Draw a rectangle 2
        Rectangle r2 = new Rectangle(40, 20, width, height);
        r2.setFill(new Color(1, 1, 1, 0));
        r2.setStroke(Color.BLACK);
        pane.getChildren().add(r2);
        
        Line l1 = new Line();
        l1.setStartX(40);
        l1.setStartY(20);
        l1.setEndX(10);
        l1.setEndY(60);
        pane.getChildren().add(l1);
        
        Line l2 = new Line();
        l2.setStartX(40);
        l2.setStartY(140);
        l2.setEndX(10);
        l2.setEndY(180);
        pane.getChildren().add(l2);
        
        Line l3 = new Line();
        l3.setStartX(190);
        l3.setStartY(20);
        l3.setEndX(160);
        l3.setEndY(60);
        pane.getChildren().add(l3);
        
        Line l4 = new Line();
        l4.setStartX(190);
        l4.setStartY(140);
        l4.setEndX(160);
        l4.setEndY(180);
        pane.getChildren().add(l4);
        
        Scene scene = new Scene(pane, paneWidth, paneHeight);
        primaryStage.setTitle("Lab14a");
        primaryStage.setScene(scene);
        primaryStage.show(); 

    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
