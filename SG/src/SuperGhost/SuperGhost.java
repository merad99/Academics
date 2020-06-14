package SuperGhost;
 
import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class SuperGhost extends Application{
	
	static String sharedFilePath; // = System.getProperty("user.dir")+File.separator+"monitor.txt";  //?????
	 static String teamName = new java.io.File(BackEnd.class.getProtectionDomain()
			.getCodeSource()
			.getLocation()
			.getPath())
			.getName().replaceAll(".jar", "");
	//static String teamName = "Patrick";
	
	public static void main(String[] args) {
		
		 sharedFilePath = args[0];   //??????
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		
	    GUI gui = new GUI(primaryStage);

	    BackEnd backEnd = new BackEnd(sharedFilePath, teamName);

	    //gui.setBackEnd(backEnd);

	    backEnd.setGui(gui);

	  Thread thread = new Thread(backEnd);
	  thread.start();
	}


}



 