package application;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.Data;
import server.JChatData;
import Client.JChatClient;
import server.JChatServer;
import server.JData;

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
	private ListView<Message> lvChatWindow;
	
	@FXML
	private Button Exit;

	private ChatClient client;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	String time =dateFormat.format(calendar.getTime());
	
	
	JChatClient jc = JChatClient.getInstance();
	ObservableList<Message> chatMessages = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		//Inputmsg.requestFocus();
		
		lvChatWindow.setItems(chatMessages);
		lvChatWindow.setCellFactory(param -> {
	            ListCell<Message> cell = new ListCell<Message>(){                
	                Label lblUserLeft = new Label();
	                Label lblTextLeft = new Label();
	                Label lblTimeLeft = new Label();
	                HBox hBoxLeft = new HBox(lblUserLeft, lblTextLeft, lblTimeLeft);

	                Label lblUserRight = new Label();
	                Label lblTextRight = new Label();
	                Label lblTimeRight = new Label();
	                HBox hBoxRight = new HBox(lblTextRight, lblUserRight, lblTimeRight);

	                {
	                    hBoxLeft.setAlignment(Pos.CENTER_LEFT);
	                    hBoxLeft.setSpacing(5);
	                    hBoxRight.setAlignment(Pos.CENTER_RIGHT);
	                    hBoxRight.setSpacing(5);
	                }
	                @Override
	                protected void updateItem(Message item, boolean empty) {
	                    super.updateItem(item, empty);

	                    if(empty)
	                    {
	                        setText(null);
	                        setGraphic(null);
	                    }
	                    else{
	                        System.out.println(item.getUser());
	                        if(item.getUser().equals("User 1"))
	                        {
	                            lblUserLeft.setText(item.getUser() + ":");
	                            lblTextLeft.setText(item.getText());
	                            lblTextLeft.setText(item.getTime());
	                            setGraphic(hBoxLeft);
	                        }
	                        else{
	                            lblUserRight.setText(":" + item.getUser());
	                            lblTextRight.setText(item.getText());
	                            lblTextRight.setText(item.getTime());
	                            setGraphic(hBoxRight);
	                        }
	                    }
	                }

	            };

	            return cell;
	        });
	    }      
	

	@FXML
	public void sendMsg(MouseEvent event) {
		
		chatMessages.add(new Message(Inputmsg.getText(), "null", time));
		Inputmsg.setText("");

		/*String message = Inputmsg.getText();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String time =dateFormat.format(calendar.getTime());
		textarea.appendText(client+"  "+message +"   "+time +"\n");
		//ChatClient.getInstance().Send(, message);
		Inputmsg.setText("");
		Inputmsg.requestFocus();*/
		
		
	}
	
	
	

	
	
		@FXML
		public void pressedKey(KeyEvent e) {
	
		Inputmsg.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				
				/*String message = Inputmsg.getText();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time =dateFormat.format(calendar.getTime());*/

				if (event.getCode() == KeyCode.ENTER) {
					chatMessages.add(new Message(Inputmsg.getText(), "null", time));
					Inputmsg.setText("");
				}
			}

		});
	}
		@FXML
		public void Disconnect(MouseEvent event) {
		
			
			
		}
			
		
	
}
