package SuperGhost;

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
 




public class GUI extends Application {
	
	public static long startTime = System.currentTimeMillis();
	static int threadCount = 0;
	static Label labelLetter;
	static Label labelPosition;
	static Label labelFragement;
	static TextField textInput;
	private  BackEnd  backEnd;

	 
	
	public GUI(Stage primaryStage) {
		try {
			start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Sets the text on the top bar of the application
		primaryStage.setTitle(" Ghost Runner App ");
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10.0);
		gridPane.setVgap(10.0);
		
		

		//Creates a buttons
		Button button1 = new Button();
		button1.setText("Add to Front");
		
		Button button2 = new Button();
		button2.setText("Add to Back");
		
		//create the letter and the position
		 labelLetter = new Label("");
		 labelLetter.setFont(Font.font("Tahoma", FontWeight.BOLD, 70));
		 labelLetter.setTextFill(Color.RED);
		 GridPane.setHalignment(labelLetter, HPos.CENTER);

		
		labelPosition = new Label("");
	    GridPane.setHalignment(labelPosition, HPos.CENTER);
	    labelPosition.setTextFill(Color.RED);

		
		labelFragement = new Label("");
		labelFragement.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18.0D));
		labelFragement.setTextFill(Color.RED);
	    GridPane.setHalignment(labelFragement, HPos.CENTER);

		
		//Create a text input
		textInput = new TextField();
	    GridPane.setValignment(textInput, VPos.BOTTOM); 

		//Create a Label
		Label label = new Label("Current Fragment:");
		label.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 18.0D));
		label.setTextFill(Color.BLACK);
 
		
    /*	button1.setOnAction(value ->  {
			String mytext = textInput.getText();
			 labelFragement.setText(mytext + labelFragement.getText());
	          
	        }); 
		
		button2.setOnAction(value ->  {
			String mytext = textInput.getText();
			 labelFragement.setText(labelFragement.getText() + mytext);
	          
	        });
		
		
		 button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override public void handle(ActionEvent event) {
				String mytext = textInput.getText();
				 labelFragement.setText(mytext + labelFragement.getText());
				 
				 String stringLetter = labelLetter.getText();
				  char myletter = stringLetter.charAt(0);
				  String stringPosition = labelPosition.getText();
				  AddLocation stringlocation;
				  if (stringPosition == "Front")
					  stringlocation =   AddLocation.Front;
				  else
					  stringlocation = AddLocation.Back;
				  
				// PlayLetter(myletter,stringlocation);
		

			}
		});
		
		
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override public void handle(ActionEvent event) {
				String mytext = textInput.getText();
				
				 labelFragement.setText(labelFragement.getText() + mytext);
				 
				  String stringLetter = labelLetter.getText();
				  char myletter = stringLetter.charAt(0);
				  String stringPosition = labelPosition.getText();
				  AddLocation stringlocation;
				  if (stringPosition == "Front")
					  stringlocation =   AddLocation.Front ;
				  else
					  stringlocation = AddLocation.Back;
				  
					/* boolean isFront=false;
					 if (stringPosition ==  "Front" )
						 isFront = true;
			
				  updateRecommendation(myletter,isFront); 
				// PlayLetter(myletter,stringlocation);

			}
		});
		*/
		




		/*
		 * Create the stack pane. With a Stack pane, without alignment 
		 * and margin information everything will be stack on top of 
		 * each other
		 */

		//We set the alignment of an object in the stage
		GridPane.setConstraints(button1, 1, 4);
		
		GridPane.setConstraints(button2, 3, 4);
		
		GridPane.setConstraints(textInput, 2, 3);
		
		GridPane.setConstraints(label, 0, 0);

		GridPane.setConstraints(labelLetter, 2, 1);
		
		GridPane.setConstraints(labelPosition, 2, 2);
		
		GridPane.setConstraints(labelFragement, 1, 0);
		
		gridPane.getChildren().addAll(label, button1, button2, textInput, labelLetter, labelPosition, labelFragement);



		 

		 
		//We set the margin of the objects in the stage
				GridPane.setMargin(button1, new Insets(15));
				GridPane.setMargin(button2, new Insets(15));
				GridPane.setMargin(textInput, new Insets(15));
				GridPane.setMargin(label, new Insets(15));
				GridPane.setMargin(labelPosition, new Insets(15));
				GridPane.setMargin(labelLetter, new Insets(15));

		
		
		gridPane.setGridLinesVisible(true);



		Scene scene = new Scene(gridPane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	//static Label label;
		public static void main(String[] args) {
			launch(args);
		}

	
	/*public void PlayLetter(char letter, AddLocation position){
	    this.backEnd.PlayLetter(letter, position);
	  }

	  public void updateRecommendation(char letter, boolean isFront){
		  String stringLetter=String.valueOf(letter); 
		  labelLetter.setText(stringLetter);
		  
		  textInput.setText(stringLetter);
		  
		  String stringPosition = isFront ? "Front" : "Back";
		  
		  labelPosition.setText("Add to " + stringPosition);
		  
		   
	  }

	  public void changeFragment(String fragment){
		  System.out.println("GUI Fragment: "+  fragment);
		  labelFragement.setText(fragment);
	  }

	  public void setBackEnd(BackEnd backEnd){
	  this.backEnd = backEnd;
	  }*/

 
	 
}

