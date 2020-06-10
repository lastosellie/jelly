package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.swing.SwingUtilities;
import Client.JChatClient;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.JChatData;
import server.JChatServer;
import server.JData;

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

	private ArrayList<Todo> todoList = new ArrayList<>();
	private ObservableList<Member> memberObservableList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		memberObservableList = FXCollections.observableArrayList();

		// add some Students
		memberObservableList.addAll(new Member("John Doe", Member.GENDER.MALE),
				new Member("Donte Dunigan", Member.GENDER.MALE), new Member("Gavin Genna", Member.GENDER.MALE),
				new Member("Darin Dear", Member.GENDER.MALE), new Member("Pura Petty", Member.GENDER.FEMALE),
				new Member("Herma Hines", Member.GENDER.FEMALE));
		memberList.setItems(memberObservableList);
		memberList.setCellFactory(memberList -> new MemberListCell());

		todoList.add(new Todo(1, 1, "설계1", LocalDate.now(), LocalDate.now().plusDays(30), "구현하기 전, 설계 검토 step1", 0.6));
		todoList.add(new Todo(1, 2, "설계2", LocalDate.now(), LocalDate.now().plusDays(5), "구현하기 전, 설계 검토 step2", 0.1));
		todoList.add(new Todo(1, 3, "설계3", LocalDate.now(), LocalDate.now().plusDays(10), "구현하기 전, 설계 검토 step3", 0.9));

		TreeTableColumn<Todo, String> treeTableColumn1 = new TreeTableColumn<>("Title");
		TreeTableColumn<Todo, String> treeTableColumn2 = new TreeTableColumn<>("Assinee");
		TreeTableColumn<Todo, String> treeTableColumn3 = new TreeTableColumn<>("Start Date");
		TreeTableColumn<Todo, String> treeTableColumn4 = new TreeTableColumn<>("End Date");
//		treeTableColumn1.setPrefWidth(50);
//		treeTableColumn2.setPrefWidth(50);
		treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
		treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("assignee"));
		treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("startDate"));
		treeTableColumn4.setCellValueFactory(new TreeItemPropertyValueFactory<>("endDate"));
		treeTableView.getColumns().add(treeTableColumn1);
		treeTableView.getColumns().add(treeTableColumn2);
		treeTableView.getColumns().add(treeTableColumn3);
		treeTableView.getColumns().add(treeTableColumn4);

		TreeItem root = new TreeItem(new Todo());

		for (Todo todo : todoList) {
			root.getChildren().add(new TreeItem(todo));
		}

		treeTableView.setRoot(root);
//		TreeItem<Todo> root = new TreeItem<Todo>(todo0);
//		root.setExpanded(true);
//		root.getChildren().add(new TreeItem<Todo>(todo1));
//		root.getChildren().add(new TreeItem<Todo>(todo2));
//		root.getChildren().add(new TreeItem<Todo>(todo3));
//		treeView.setRoot(root);
//		treeView.setShowRoot(false);
//		MultipleSelectionModel msm = treeView.getSelectionModel();
//		msm.select(1);
		treeTableView.setShowRoot(false);
		root.setExpanded(true);
		treeTableView.resizeColumn(treeTableColumn1, 60);

		final SwingNode swingNode = new SwingNode();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				swingNode.setContent(new GanttChart(todoList).getChartPanel());
			}
		});
		gridPane.add(swingNode, 1, 1);
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
	public void sendJChatDataClicked(MouseEvent event) {
		String message = textField.getText();
		textField.setText("");
		JChatClient.getInstance().sendToServer(this, new JChatData(ClientInfo.UserName, message, JChatData.CHAT_MESSAGE, ""));
	}
	
	@FXML
	public void sendJellyClicked(MouseEvent event) {
		JChatClient.getInstance().sendToServer(this, new JData(JData.ADD_TODO));
	}

	@Override
	public void receive(Object data) {
		if (data instanceof JChatData) {
			JChatData jd = (JChatData)data;
			if (jd.getState() == JChatData.CHAT_MESSAGE) {
				textArea.appendText(jd.getName() + " : " + jd.getMessage());
				textArea.appendText("\n");
			}
		} else if (data instanceof JData) {
			
		}
	}
}
