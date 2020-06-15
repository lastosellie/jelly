package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sun.javafx.scene.control.skin.TextFieldSkin;

import Client.JChatClient;
import biz.MemberBiZ;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.Member;

public class LoginController {

	@FXML
	private TextField idTfd, pwTfd;

	public boolean isMemberInfoValid() {
		String id = idTfd.getText().trim();
		if (id.isEmpty()) {
			errorMsg(AlertType.ERROR, "���̵� �Է� ���� !", "���̵� �Է��ϼ���.");
			return false;
		}

		if (id.length() > 10) {
			errorMsg(AlertType.ERROR, "���̵� �Է� ���� !", "���̵�� �ִ� 10���� �Դϴ�.");
			return false;
		}

		String pw = pwTfd.getText().trim();
		if (pw.isEmpty()) {
			errorMsg(AlertType.ERROR, "��й�ȣ �Է� ���� !", "��й�ȣ�� �Է��ϼ���.");
			return false;
		}

		if (pw.length() > 20) {
			errorMsg(AlertType.ERROR, "��й�ȣ �Է� ���� !", "��й�ȣ�� �ִ� 20���� �Դϴ�.");
			return false;
		}

		Member member = new MemberBiZ().getSelectVO(id);
		if (member == null) {
			errorMsg(AlertType.ERROR, "���̵� �Է� ���� !", "��ϵ� ���̵� �ƴմϴ�.");
			return false;
		}

		if (!member.getPw().equals(pwTfd.getText().trim())) {
			errorMsg(AlertType.ERROR, "��й�ȣ �Է� ���� !", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return false;
		}
		ClientInfo.member = member;
		ClientInfo.member.setId(id);
		return true;
	}

	public boolean isSeverAlive() {
		if (!JChatClient.getInstance().isLoaded()) {
			errorMsg(AlertType.ERROR, "���� ���� ���� !", "�������� ��ſ� �����Ͽ����ϴ�. ���� ���� ������ Ȯ���� �ּ���.");
			return false;
		}

		return true;
	}

	@FXML
	public void MouseClicked(MouseEvent event) {
		if (!isSeverAlive()) {
			return;
		}

		if (isMemberInfoValid() == false) {
			return;
		}

		Stage stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Task.fxml"));

		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void signinClicked(MouseEvent event) {
		if (!isSeverAlive()) {
			return;
		}

		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.DECORATED);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sign_UP.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			dialog.setTitle("Sign Up");
			dialog.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setResizable(false);
		dialog.getIcons().add(new Image("file:image/jicon.png"));
		dialog.show();
	}

	// alertâ
	public void errorMsg(AlertType type, String head, String msg) {
		Alert info = new Alert(type);
		info.setHeaderText(head);
		info.setContentText(msg);

		info.showAndWait();
	}

	public void handle(KeyEvent event) {
		if (event.getCode().equals(KeyCode.TAB)) {
			Node node = (Node) event.getSource();
			if (node instanceof TextField) {
				TextFieldSkin skin = (TextFieldSkin) ((TextField) node).getSkin();
				if (event.isShiftDown()) {
					skin.getBehavior().traversePrevious();
				} else {
					skin.getBehavior().traverseNext();
				}
			}

			event.consume();
		}
	}
}
