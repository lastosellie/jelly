package application;

import Client.JChatClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.JRequestData;
import vo.Todo;

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
		String asignee = "";
		System.out.println(parentTfd.getText());

		if (assigneeTfd.getText().trim().equals(""))
			asignee = ClientInfo.UserName;
		else
			asignee = assigneeTfd.getText();

		Todo todo = new Todo(projectId, titleTfd.getText(), asignee, StartDpr.getValue(), EndDpr.getValue(),
				ContentTaa.getText(), progressSdr.getValue());

		if (!parentTfd.getText().trim().equals("")) {
			todo.setParentId(Integer.parseInt(parentTfd.getText()));
		}
		JChatClient.getInstance().sendToServer(new JRequestData(JRequestData.ADD_TODO, todo));

		Stage stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
		stage.close();
	}

}
