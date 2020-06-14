package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import biz.MemberBiZ;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Calservice;
import vo.Member;
import vo.TaskVO;

public class NewProjectController implements Initializable {

	private Calservice service;

	@FXML
	private TextField txtTitle;

	@FXML
	private DatePicker Sdate;

	@FXML
	private DatePicker Edate;

	@FXML
	private TextField TName;

	@FXML
	private Button addBtn;

	@FXML
	private CheckComboBox<Member> cbx;

	@FXML
	void addClick(MouseEvent event) {
		// 1. 화면에서 값들 가져오기
		String title = txtTitle.getText().trim();

		if (title.isEmpty()) {
			errorMsg("TITLE 입력 오류 !", "Title을 입력해 주세요.");
			return;
		}

		if (Sdate.getValue() == null) {
			errorMsg("Start Date 입력 오류 !", "Start Date를 입력해 주세요.");
			return;
		}

		if (Edate.getValue() == null) {
			errorMsg("End Date 입력 오류 !", "End Date를 입력해 주세요.");
			return;
		}

		String sdate = Sdate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String edate = Edate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// TaskVO 만들기
		TaskVO calVo = new TaskVO();
		calVo.setTask_ID(service.getNewPJID());
		calVo.setTask_Title(title);
		calVo.setTask_eDate(edate);
		calVo.setTask_sDate(sdate);
		// calVo.setEmp_id(Main.curEmp.getEmp_id()); //지금로그인한 사람의 사원번호

		service.insertCal(calVo);

		new MemberBiZ().getInsertVO(calVo.getTask_ID(), ClientInfo.member.getId());
		for (Object obj : cbx.getCheckModel().getCheckedItems()) {
			new MemberBiZ().getInsertVO(calVo.getTask_ID(), ((Member) obj).getId());
		}

		Stage thisForm = (Stage) addBtn.getScene().getWindow();
		thisForm.close();
	}

	// alert창
	public void errorMsg(String head, String msg) {
		Alert info = new Alert(AlertType.ERROR);
		info.setHeaderText(head);
		info.setContentText(msg);

		info.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = Calservice.getInstance();

		Sdate.setValue(LocalDate.now());
		Edate.setOnAction(e -> {

			if (Edate.getValue() == null) {
				return;
			}

			if (Sdate.getValue().isAfter(Edate.getValue())) {
				errorMsg("날짜 입력 오류 !", "종료날짜가 시작날짜보다 빠릅니다.");
				Edate.getEditor().clear();
			}
		});

		List<Member> members = new MemberBiZ().getSelectAll();
		for (Member member : members) {
			if (!member.getId().equals(ClientInfo.member.getId())) {
				cbx.getItems().add(member);
			}
		}
	}
}
