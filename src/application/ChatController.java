package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import Client.JChatClient;
import biz.MemberBiZ;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import server.JChatData;
import vo.Todo;

public class ChatController implements Initializable, IClient {

	private String projectTitle;
	@FXML
	private TextField Inputmsg;
	@FXML
	private ImageView Send;
	@FXML
	private ListView<JChatData> lvChatWindow;
	@FXML
	private Button Exit;
	@FXML
	private Label projectTitleLb;

	ObservableList<JChatData> chatMessages = FXCollections.observableArrayList();

	public ChatController(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		projectTitleLb.setText(projectTitle);
		Inputmsg.requestFocus();

		lvChatWindow.setItems(chatMessages);
		lvChatWindow.setCellFactory(param -> {
			ListCell<JChatData> cell = new ListCell<JChatData>() {
				Label lblUserLeft = new Label();
				Label lblTextLeft = new Label();
				Label lblTimeLeft = new Label();
				HBox hBoxLeft = new HBox(lblUserLeft, lblTextLeft, lblTimeLeft);

				Label lblUserRight = new Label();
				Label lblTextRight = new Label();
				Label lblTimeRight = new Label();
				HBox hBoxRight = new HBox(lblUserRight, lblTextRight, lblTimeRight);

				{
					hBoxLeft.setAlignment(Pos.CENTER_LEFT);
					hBoxLeft.setSpacing(5);
					hBoxRight.setAlignment(Pos.CENTER_RIGHT);
					hBoxRight.setSpacing(5);
				}

				@Override
				protected void updateItem(JChatData item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setText(null);
						setGraphic(null);
					} else {
						if (item.getId().equals(ClientInfo.member.getId())) {
							lblUserLeft.setText(item.getName() + ":");
							lblTextLeft.setText(item.getMessage());
							lblTimeLeft.setText(item.getTimestamp());
							setGraphic(hBoxLeft);
						} else {
							lblUserRight.setText(item.getName() + ":");
							lblTextRight.setText(item.getMessage());
							lblTimeRight.setText(item.getTimestamp());
							setGraphic(hBoxRight);
						}
					}
				}

			};

			return cell;
		});

		JChatClient.getInstance().sendToServer(this, new JChatData(ClientInfo.member.getId(),
				ClientInfo.member.getName(), "님이 접속하였습니다. ", JChatData.FIRST_CONNECTION, LocalDate.now().toString()));
	}

	public void send() {
		String message = Inputmsg.getText().trim();
		if (message.isEmpty()) {
			return;
		}
		JChatClient.getInstance().sendToServer(new JChatData(ClientInfo.member.getId(), ClientInfo.member.getName(),
				message, JChatData.CHAT_MESSAGE, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
		Inputmsg.setText("");
	}

	@FXML
	public void pressedKey(KeyEvent e) {

		if (e.getCode() == KeyCode.ENTER) {
			send();
		}
	}

	@FXML
	public void sendMouseClicked(MouseEvent e) {
		send();
	}

	@Override
	public void receive(Object data) {
		if (data instanceof JChatData) {
			Platform.runLater(new Runnable() {

				public void run() {
					JChatData jd = (JChatData) data;
					if (jd.getState() == JChatData.CHAT_MESSAGE) {
						chatMessages.add(jd);
					}
				}

			});
		}
	}

}
