package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private Button btn1;
	
	@FXML
	public void MouseClicked(MouseEvent event){
		System.out.println("You clicked me!");
		Stage stage = (Stage) ((Button)(event.getSource())).getScene().getWindow();
	    
	    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Project.fxml"));
        
		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
    }

}
