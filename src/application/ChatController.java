package application;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import Client.ChatClient;
import Client.Receive;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import server.Data;
import java.net.*;
import Client.*;

public class ChatController implements Initializable {

	@FXML
	private ComboBox Teamlist;
	@FXML
	private TextField Inputmsg;
	@FXML
	private ImageView Send;
	@FXML
	private TextArea textarea;

	private ChatClient client;

	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		//ChatClient.getInstance().start();
		Inputmsg.requestFocus();
	}

	@FXML
	public void sendMsg(MouseEvent event) {

		String message = Inputmsg.getText();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String time =dateFormat.format(calendar.getTime());
		textarea.appendText(client+"  "+message +"   "+time +"\n");
		//ChatClient.getInstance().Send(, message);
		Inputmsg.setText("");
		Inputmsg.setAlignment(Pos.BOTTOM_RIGHT);
		Inputmsg.requestFocus();
		ChatClient.getInstance().copyText(message, Data.CHAT_MESSAGE);
	
		/*Inputmsg.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {
					try{
						ChatClient.getInstance().copyText(message, Data.CHAT_MESSAGE);
						textarea.appendText(message +"\n");
						Inputmsg.setText("");
						Inputmsg.requestFocus();
					}catch(Exception e) {
						System.out.println("exception");
					}
				}
			}

		});*/
		
		
	}
	
	@FXML
	public void receiveMsg(MouseEvent e) {
		
	}

}
