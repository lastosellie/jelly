package application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import vo.TaskVO;

public class ProjectListController {
	
	Map<String, Integer> paramMap;

	@FXML
	ListView<TaskVO> listView;
	@FXML
	Label label;

	private ObservableList<TaskVO> projectList;
	
	public ProjectListController() {
		
	}

	@FXML
	public void moveProjectViewClicked(MouseEvent event) {
		TaskVO tv = (TaskVO) listView.getSelectionModel().getSelectedItem();
		if (tv == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Warning");
			alert.setContentText("Please, Select Project Item");
			alert.showAndWait();
			return;
		}
		Stage stage = (Stage) ((Button)(event.getSource())).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Project.fxml"));
		loader.setController(new ProjectController(tv.getTask_ID()));
		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setParamMap(Map<String, Integer> paramMap) {
		this.paramMap = paramMap;
	}

	public void setLabel(String title) {
		label.setText(title);
	}

	public void setProjectList(List<TaskVO> projectList) {
		this.projectList = FXCollections.observableArrayList(projectList);
		listView.setItems(this.projectList);
	}

}
