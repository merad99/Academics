package Stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sharedCode.AddLocation;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;

public class GameView extends Application {
	
	static Label labelLetter;
	static Label labelPosition;
	static Label labelFragement;
	static TextField textInput;


	@Override
	public void start(Stage primaryStage) throws Exception {
		//Sets the text on the top bar of the application
		primaryStage.setTitle(" Ghost Runner App ");
		
		GridPane gridPane = new GridPane();		
		

		//Creates a buttons
		Button button1 = new Button();
		button1.setText("Add to Front");
		
		Button button2 = new Button();
		button2.setText("Add to Back");
		
		
		//create the letter and the position
		 labelLetter = new Label("A");
		 labelLetter.setFont(Font.font("Tahoma", FontWeight.BOLD, 70));
		 labelLetter.setTextFill(Color.RED);
		 GridPane.setHalignment(labelLetter, HPos.CENTER);

		
		labelPosition = new Label("Add to Back");
		labelLetter.setFont(Font.font("Tahoma", FontWeight.BOLD, 70));
	    GridPane.setHalignment(labelPosition, HPos.CENTER);
	    labelPosition.setTextFill(Color.RED);

		
		labelFragement = new Label("NO");
		labelFragement.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18.0D));
		labelFragement.setTextFill(Color.RED);
	    GridPane.setHalignment(labelFragement, HPos.CENTER);

		
		//Create a text input
		textInput = new TextField();
	    GridPane.setValignment(textInput, VPos.BOTTOM);
	    GridPane.setHalignment(textInput, HPos.CENTER);

		//Create a Label
		Label label = new Label("Current Fragment:");
		label.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 18.0D));
		label.setTextFill(Color.BLACK);



        GridPane.setConstraints(button1, 1, 4);	
		GridPane.setConstraints(button2, 3, 4);
		GridPane.setConstraints(textInput, 2, 3);	
		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(labelLetter, 2, 1);
		GridPane.setConstraints(labelPosition, 2, 2);
		GridPane.setConstraints(labelFragement, 1, 0);

		gridPane.getChildren().addAll(label, button1, button2, textInput, labelLetter, labelPosition, labelFragement);
		
		GridPane.setMargin(button1, new Insets(15));
		GridPane.setMargin(button2, new Insets(15));
		GridPane.setMargin(textInput, new Insets(15));
		GridPane.setMargin(label, new Insets(15));
		GridPane.setMargin(labelPosition, new Insets(15));
		GridPane.setMargin(labelLetter, new Insets(15));

		
		Scene scene = new Scene(gridPane, 700, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}