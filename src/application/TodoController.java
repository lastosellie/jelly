package application;

import java.awt.Event;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TodoController {

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

	public TodoController(int projectId) {
		this.projectId = projectId;
	}

	public void FinishClicked(MouseEvent event) {
		System.out.println(assigneeTfd.getText());
		System.out.println(parentTfd.getText());
		Todo todo = new Todo(projectId, titleTfd.getText(), StartDpr.getValue(), EndDpr.getValue(), ContentTaa.getText(), progressSdr.getValue());

		// 팝업 닫기 => 닫고나서 클라이언트 갱신해주면 좋은데 ㅜㅜ
		Stage stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
		stage.close();
	}

}
