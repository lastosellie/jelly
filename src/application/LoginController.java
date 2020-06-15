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
			errorMsg(AlertType.ERROR, "아이디 입력 오류 !", "아이디를 입력하세요.");
			return false;
		}

		if (id.length() > 10) {
			errorMsg(AlertType.ERROR, "아이디 입력 오류 !", "아이디는 최대 10글자 입니다.");
			return false;
		}

		String pw = pwTfd.getText().trim();
		if (pw.isEmpty()) {
			errorMsg(AlertType.ERROR, "비밀번호 입력 오류 !", "비밀번호를 입력하세요.");
			return false;
		}

		if (pw.length() > 20) {
			errorMsg(AlertType.ERROR, "비밀번호 입력 오류 !", "비밀번호는 최대 20글자 입니다.");
			return false;
		}

		Member member = new MemberBiZ().getSelectVO(id);
		if (member == null) {
			errorMsg(AlertType.ERROR, "아이디 입력 오류 !", "등록된 아이디가 아닙니다.");
			return false;
		}

		if (!member.getPw().equals(pwTfd.getText().trim())) {
			errorMsg(AlertType.ERROR, "비밀번호 입력 오류 !", "비밀번호가 일치하지 않습니다.");
			return false;
		}
		ClientInfo.member = member;
		ClientInfo.member.setId(id);
		return true;
	}

	public boolean isSeverAlive() {
		if (!JChatClient.getInstance().isLoaded()) {
			errorMsg(AlertType.ERROR, "서버 연결 오류 !", "서버와의 통신에 실패하였습니다. 서버 연결 정보를 확인해 주세요.");
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

	// alert창
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
