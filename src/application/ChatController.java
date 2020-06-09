package application;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ChatClient;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import server.Data;
import java.net.*;

public class ChatController implements Initializable  {

	@FXML
	private Pane Chatroom;
	@FXML
	private ComboBox Teamlist;
	@FXML
	private TextField Inputmsg;
	@FXML
	private ImageView Send;
	@FXML
	private ChatClient client;
	@FXML
	private TextArea textarea;
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resource) {
	
	   
	}
	@FXML
	public void sendMsg(MouseEvent event) {
		
		String message = Inputmsg.getText();
		System.out.println(message);
		//이거 이제 서버로 보내서 textarea에 띄워야함
	}
	    	
	   
		
	}
	
	
	
	
	

	
	
