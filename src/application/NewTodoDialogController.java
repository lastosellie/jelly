package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Client.JChatClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.JRequestData;
import vo.Todo;

public class NewTodoDialogController implements Initializable {

	private int projectId;

	@FXML
	private TextField titleTfd;
	@FXML
	private TextField assigneeTfd;
	@FXML
	private TextField parentTfd;
	@FXML
	private DatePicker StartDpr;
	@FXML
	private DatePicker EndDpr;
	@FXML
	private TextArea ContentTaa;
	@FXML
	private Slider progressSdr;

	private List<Todo> todoList;

	public NewTodoDialogController(int projectId, List<Todo> todoList) {
		super();
		this.projectId = projectId;
		this.todoList = todoList;
	}

	// alert창
	public void errorMsg(String head, String msg) {
		Alert info = new Alert(AlertType.ERROR);
		info.setHeaderText(head);
		info.setContentText(msg);

		info.showAndWait();
	}

	public void FinishClicked(MouseEvent event) {
		String asignee = "";

		if (assigneeTfd.getText().trim().equals(""))
			asignee = ClientInfo.member.getName();
		else
			asignee = assigneeTfd.getText().trim();

		String title = titleTfd.getText().trim();

		if (title.isEmpty()) {
			errorMsg("TITLE 입력 오류 !", "Title을 입력해 주세요.");
			return;
		}

		for (Todo todo : todoList) {
			if (title == todo.getTitle()) {
				errorMsg("TITLE 입력 오류 !", "이미 등록되어 있는 TITLE 입니다.");
				return;
			}
		}

		if (StartDpr.getValue() == null) {
			errorMsg("Start Date 입력 오류 !", "Start Date를 입력해 주세요.");
			return;
		}

		if (EndDpr.getValue() == null) {
			errorMsg("End Date 입력 오류 !", "End Date를 입력해 주세요.");
			return;
		}

		if (ContentTaa.getText().trim() == null) {
			errorMsg("Content 입력 오류 !", "Content를 입력해 주세요.");
			return;
		}

		Todo todo = new Todo(projectId, title, asignee, StartDpr.getValue(), EndDpr.getValue(), ContentTaa.getText(),
				progressSdr.getValue());

//		if (!parentTfd.getText().trim().equals("")) {
//			todo.setParentId(Integer.parseInt(parentTfd.getText()));
//		}

		JChatClient.getInstance().sendToServer(new JRequestData(JRequestData.ADD_TODO, todo));

		Stage stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StartDpr.setValue(LocalDate.now());
		EndDpr.setOnAction(e -> {
			if (EndDpr.getValue() == null) {
				return;
			}
			if (StartDpr.getValue().isAfter(EndDpr.getValue())) {
				errorMsg("날짜 입력 오류 !", "종료날짜가 시작날짜보다 빠릅니다.");
				EndDpr.getEditor().setText("");
			}
		});
	}

}
