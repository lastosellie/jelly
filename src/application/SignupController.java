package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import biz.MemberBiZ;
import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import vo.Member;

public class SignupController implements Initializable {

	@FXML
	private GridPane grid;
	@FXML
	private PasswordField pw;
	@FXML
	private TextField name;
	@FXML
	private TextField id;
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
	
	
	private int gender;
	
	
	
	
	@FXML
	//회원가입 name, gender, id, pw, teamid, deptno
	public void handleSignup(ActionEvent event) {
		
		Window owner = submit.getScene().getWindow();
		System.out.println("SignUpController : hanldeSignup()");
		
		
		if(id.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner,"Form Error!", "아이디를 입력하세요.");
			return ;
		}
		
		if(name.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner,"Form Error!", "이름을 입력하세요.");
			return ;
		}
		
		
		if(teamid.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner,"Form Error!", "Team ID를 입력하세요.");
			return ;
		}
		
		
		if(deptno.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner,"Form Error!", "부서번호를 입력하세요.");
			return ;
		}
		
		
		 ToggleGroup pickGender = new ToggleGroup();
		 RadioButton pickMale = new RadioButton("Male");
		 male.setToggleGroup(pickGender);
		RadioButton pickFemale = new RadioButton("Female");
		 female.setToggleGroup(pickGender);
		    
	
		if(male.isSelected()) {
			male.setUserData(0);
			gender = (int) pickGender.getUserData();
			female.setSelected(false);
		}if(female.isSelected()){
			female.setUserData(1);
			gender = (int) pickGender.getUserData();
			
		}
		
		/*if(id.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner,"Form Error!", "아이디를 입력하세요.");
			return ;
		}*/
		
			
		}
	
	@FXML
	//회원가입 name, gender, id, pw, teamid, deptno
	public void SubmitSignup(MouseEvent event) throws Exception {
		Window owner = submit.getScene().getWindow();
		
		String Name = name.getText();
		String Id = id.getText();
		String Pw = pw.getText();
		int Teamid = teamid.getAnchor();
		int Deptno = deptno.getAnchor();
		
		
		Member member = new Member();
		member.setName(Name);
		member.setGender(gender);
		member.setId(Id);
		member.setpw(Pw);
		member.setProject_id(Teamid);
		member.setDeptno(Deptno);
		

		MemberBiZ.getInstance().getInsertVO(member);
		
		showAlert(Alert.AlertType.CONFIRMATION,owner,"회원가입을 축하합니다.", "로그인하면으로 이동됩니다.");
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)submit.getScene().getWindow();
		
		Parent asd = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene sc = new Scene(asd);
		primaryStage.setScene(sc);
		primaryStage.show();
		stage.close();
	}
	
	
	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.requestFocus();
		
	}
	}



