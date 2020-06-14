package application;

import java.net.URL;
import java.util.ResourceBundle;

import biz.MemberBiZ;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vo.Member;

public class SignupController implements Initializable {

	@FXML
	private GridPane grid;
	@FXML
	private PasswordField pwPf;
	@FXML
	private TextField nameTxd;
	@FXML
	private TextField idTxd;
	@FXML
	private TextField deptno;
	@FXML
	private TextField teamid;
	@FXML
	private Button submit;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;

	// ȸ������ name, gender, id, pw, teamid, deptno
	@FXML
	public void SubmitSignup(MouseEvent event) {

//		if (teamid.getText().isEmpty()) {
//			errorMsg("Team ID�� �Է��ϼ���.");
//			return;
//		}
//
//		if (deptno.getText().isEmpty()) {
//			errorMsg("�μ���ȣ�� �Է��ϼ���.");
//			return;
//		}
		String name = nameTxd.getText().trim();
		if (name.isEmpty()) {
			errorMsg(AlertType.ERROR, "�̸� �Է� ���� !", "�̸��� �Է��ϼ���.");
			return;
		}

		String id = idTxd.getText().trim();
		if (id.isEmpty()) {
			errorMsg(AlertType.ERROR, "���̵� �Է� ���� !", "���̵� �Է��ϼ���.");
			return;
		}

		String pw = pwPf.getText().trim();
		if (pw.isEmpty()) {
			errorMsg(AlertType.ERROR, "��й�ȣ �Է� ���� !", "��й�ȣ�� �Է��ϼ���.");
			return;
		}

//		ToggleGroup pickGender = new ToggleGroup();
//		RadioButton pickMale = new RadioButton("Male");
//		male.setToggleGroup(pickGender);
//		RadioButton pickFemale = new RadioButton("Female");
//		female.setToggleGroup(pickGender);

		int gender = 0;
		if (male.isSelected()) {
			gender = 0;
		} else if (female.isSelected()) {
			gender = 1;
		} else {
			errorMsg(AlertType.ERROR, "���� �Է� ���� !", "������ �Է��ϼ���.");
		}

//		int Teamid = teamid.getAnchor();
//		int Deptno = deptno.getAnchor();

		Member member = new Member();
		member.setName(name);
		member.setGender(gender);
		member.setId(id);
		member.setpw(pw);
//		member.setProject_id(Teamid);
//		member.setDeptno(Deptno);

		if (new MemberBiZ().getInsertVO(member) == -1) {
			errorMsg(AlertType.ERROR, "���̵� �Է� ���� !", "�̹� ��ϵ� ���̵��Դϴ�.");
			return;
		}

		errorMsg(Alert.AlertType.CONFIRMATION, "ȸ�������� �����մϴ�.", "�α����ϸ����� �̵��˴ϴ�.");
		Stage stage = (Stage) submit.getScene().getWindow();
		stage.close();
	}

	// alertâ
	public void errorMsg(AlertType type, String head, String msg) {
		Alert info = new Alert(type);
		info.setHeaderText(head);
		info.setContentText(msg);

		info.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameTxd.requestFocus();
	}
}
