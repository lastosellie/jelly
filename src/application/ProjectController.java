package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.SwingUtilities;
import Client.JChatClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.JChatData;
import server.JRequestData;
import vo.Member;
import vo.Todo;

public class ProjectController implements Initializable, IClient {

	private int projectId = 1;

	@FXML
	private GridPane gridPane;
	@FXML
	private TreeTableView<Todo> treeTableView;
	@FXML
	private ListView memberList;
	@FXML
	private Button sendBtn;
	@FXML
	private TextArea textArea;
	@FXML
	private TextField textField;

	private List<Todo> todoList = new ArrayList<Todo>();
	private ObservableList<Member> memberObservableList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		memberObservableList = FXCollections.observableArrayList();
		memberObservableList.addAll(new Member("John Doe", Member.Female), new Member("Donte Dunigan", Member.Male),
				new Member("Gavin Genna", Member.Female), new Member("Darin Dear", Member.Female),
				new Member("Pura Petty", Member.Female), new Member("Herma Hines", Member.Female));
		memberList.setItems(memberObservableList);
		memberList.setCellFactory(memberList -> new MemberListCell());

//		treeTableColumn1.setPrefWidth(50);
//		treeTableColumn2.setPrefWidth(50);

		TreeTableColumn<Todo, String> treeTableColumn1 = new TreeTableColumn<>("Title");
		TreeTableColumn<Todo, String> treeTableColumn2 = new TreeTableColumn<>("Assinee");
		TreeTableColumn<Todo, String> treeTableColumn3 = new TreeTableColumn<>("Start Date");
		TreeTableColumn<Todo, String> treeTableColumn4 = new TreeTableColumn<>("End Date");

		treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
		treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("assignee"));
		treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("startDate"));
		treeTableColumn4.setCellValueFactory(new TreeItemPropertyValueFactory<>("endDate"));
		treeTableView.getColumns().add(treeTableColumn1);
		treeTableView.getColumns().add(treeTableColumn2);
		treeTableView.getColumns().add(treeTableColumn3);
		treeTableView.getColumns().add(treeTableColumn4);

		treeTableView.setShowRoot(false);
		// treeTableView.getRoot().setExpanded(true);
		// treeTableView.resizeColumn(treeTableColumn1, 60);
		
		try {
			JChatClient.getInstance().sendToServer(this, new JRequestData(JRequestData.GET_TODO));
		} catch (Exception e) {
			System.out.println("서버와의 접속오류로 종료합니다.");
			System.exit(0);
		}	
	}

	void refreshTodoList() {
		Platform.runLater(new Runnable() {
			public void run() {
				TreeItem root = new TreeItem(new Todo());
				for (Todo todo : todoList) {
					root.getChildren().add(new TreeItem(todo));
				}
				treeTableView.setRoot(root);
				root.setExpanded(true);

				if (todoList.size() > 0) {
					MultipleSelectionModel msm = treeTableView.getSelectionModel();
					msm.select(0);
				}

				final SwingNode swingNode = new SwingNode();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						swingNode.setContent(new GanttChart(todoList).getChartPanel());
					}
				});
				gridPane.add(swingNode, 1, 1);
			}
		});
	}

	@FXML
	public void treeTableViewSelected(MouseEvent event) {
		System.out.println("selected");
	}

	@FXML
	public void newButtonClicked(MouseEvent event) {
		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.DECORATED);
		Parent parent;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TodoDialog.fxml"));
			fxmlLoader.setController(new TodoController(projectId));
			Scene scene = new Scene(fxmlLoader.load());
			dialog.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setResizable(false);
		dialog.setTitle("New Todo");
		dialog.show();

	}

	@FXML
	public void deleteButtonClicked(MouseEvent event) {
		TreeItem<Todo> treeItem = treeTableView.getSelectionModel().getSelectedItem();
		
		if (treeItem == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, a Warning Dialog");
			alert.setContentText("Please, Select Todo Item");
			alert.showAndWait();
			return;
		}
		
		Todo todo = treeItem.getValue();
		System.out.println(todo.getId());
	}

	@FXML
	public void sendJChatDataClicked(MouseEvent event) {
		String message = textField.getText();
		textField.setText("");
		JChatClient.getInstance().sendToServer(this,
				new JChatData(ClientInfo.UserName, message, JChatData.CHAT_MESSAGE, ""));
	}

	@FXML
	public void sendJellyClicked(MouseEvent event) {
		JChatClient.getInstance().sendToServer(this, new JRequestData(JRequestData.GET_TODO));
	}

	@Override
	public void receive(Object data) {
		if (data instanceof JChatData) {
			JChatData jd = (JChatData) data;
			if (jd.getState() == JChatData.CHAT_MESSAGE) {
				textArea.appendText(jd.getName() + " : " + jd.getMessage());
				textArea.appendText("\n");
			}
		} else if (data instanceof JRequestData) {
			JRequestData jd = (JRequestData) data;
			switch (jd.getCommand()) {
			case JRequestData.ADD_TODO:
				JChatClient.getInstance().sendToServer(this, new JRequestData(JRequestData.GET_TODO));
				break;
			case JRequestData.GET_TODO:
				todoList = jd.getTodoList();
				refreshTodoList();
				break;
			}
		}
	}
}
