package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import Client.JChatClient;
import biz.MemberBiZ;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import server.JChatData;
import server.JRequestData;
import vo.Member;
import vo.Todo;

public class ProjectController implements Initializable, IClient {

	boolean loaded;
	private String projectTitle;
	private int projectId;

	@FXML
	private GridPane gridPane;
	@FXML
	private TreeTableView<Todo> treeTableView;
	@FXML
	private ListView memberList;
	@FXML
	private TextArea textArea, contentTaa;
	@FXML
	private Label projectNameLb;

	private List<Todo> todoList = new ArrayList<Todo>();
	private ObservableList<Member> memberObservableList;
	private Stage chatDialog;

	private SwingNode swingNode;

	public ProjectController(String projectTitle, int projectId) {
		this.projectTitle = projectTitle;
		this.projectId = projectId;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		projectNameLb.setText(projectTitle);

		TreeTableColumn<Todo, String> treeTableColumn1 = new TreeTableColumn<>("Title");
		TreeTableColumn<Todo, String> treeTableColumn2 = new TreeTableColumn<>("Assinee");
		TreeTableColumn<Todo, String> treeTableColumn3 = new TreeTableColumn<>("Start Date");
		TreeTableColumn<Todo, String> treeTableColumn4 = new TreeTableColumn<>("End Date");

		treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
		treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("assignee"));
		treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("startDate"));
		treeTableColumn4.setCellValueFactory(new TreeItemPropertyValueFactory<>("endDate"));

		treeTableColumn1.setPrefWidth(100);
		treeTableColumn2.setPrefWidth(80);
		treeTableColumn3.setPrefWidth(120);
		treeTableColumn4.setPrefWidth(120);

		treeTableView.getColumns().add(treeTableColumn1);
		treeTableView.getColumns().add(treeTableColumn2);
		treeTableView.getColumns().add(treeTableColumn3);
		treeTableView.getColumns().add(treeTableColumn4);

		TreeItem root = new TreeItem(new Todo());
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
		// treeTableView.getRoot().setExpanded(true);
		// treeTableView.resizeColumn(treeTableColumn1, 60);

		JRequestData jd = new JRequestData(JRequestData.GET_TODO);
		jd.setProjectId(this.projectId);
		JChatClient.getInstance().sendToServer(this, jd);

		swingNode = new SwingNode();
		gridPane.add(swingNode, 1, 1);
	}

	void refreshTodoList() {
		Platform.runLater(new Runnable() {
			public void run() {

				// 처음 한번만 실행
				if (!loaded) {
					memberObservableList = FXCollections.observableArrayList();
					List<String> ids = new MemberBiZ().getSelectAll(projectId);
					for (String id : ids) {
						memberObservableList.add(new MemberBiZ().getSelectVO(id));
					}

					memberList.setItems(memberObservableList);
					memberList.setCellFactory(param -> new MemberListCell());
					loaded = true;
				}

				treeTableView.getRoot().getChildren().clear();

				for (Todo todo : todoList) {
					treeTableView.getRoot().getChildren().add(new TreeItem(todo));
				}

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						swingNode.setContent(new GanttChart(todoList).getChartPanel());
					}
				});
//				if (todoList.size() > 0) {
//					MultipleSelectionModel msm = treeTableView.getSelectionModel();
//					msm.select(0);
//				}

//				final SwingNode swingNode = new SwingNode();
//				SwingUtilities.invokeLater(new Runnable() {
//					@Override
//					public void run() {
//						swingNode.setContent(new GanttChart(todoList).getChartPanel());
//					}
//				});
//				gridPane.add(swingNode, 1, 1);
			}
		});
	}

	@FXML
	public void treeTableViewSelected(MouseEvent event) {
		TreeItem<Todo> treeItem = treeTableView.getSelectionModel().getSelectedItem();

		if (treeItem == null) {
			return;
		}

		Todo todo = treeItem.getValue();
		contentTaa.setText(todo.getContent());
	}

	@FXML
	public void newButtonClicked(MouseEvent event) {
		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.DECORATED);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTodoDialog.fxml"));
			fxmlLoader.setController(new NewTodoDialogController(projectId, todoList));
			Scene scene = new Scene(fxmlLoader.load());
			dialog.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setResizable(false);
		dialog.setTitle("New Todo");
		dialog.getIcons().add(new Image("file:image/jicon.png"));
		dialog.show();
	}

	@FXML
	public void deleteButtonClicked(MouseEvent event) {
		TreeItem<Todo> treeItem = treeTableView.getSelectionModel().getSelectedItem();

		if (treeItem == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Warning");
			alert.setContentText("Please, Select Todo Item");
			alert.showAndWait();
			return;
		}

		Todo todo = treeItem.getValue();
		JRequestData jd = new JRequestData(JRequestData.DEL_TODO);
		jd.setTodoId(todo.getId());
		JChatClient.getInstance().sendToServer(this, jd);
	}

	@Override
	public void receive(Object data) {
		if (data instanceof JRequestData) {
			JRequestData jd = (JRequestData) data;
			switch (jd.getCommand()) {
			case JRequestData.ADD_TODO:
			case JRequestData.DEL_TODO:
				JRequestData jd1 = new JRequestData(JRequestData.GET_TODO);
				jd1.setProjectId(this.projectId);
				JChatClient.getInstance().sendToServer(this, jd1);
				System.out.println(this.projectId + "투두리스트 갱신 요청");
				break;
			case JRequestData.GET_TODO:
				System.out.println(this.projectId + todoList.size() + "투두리스트 get이벤트 옴");
				if (jd.getProjectId() == this.projectId) {
					todoList = jd.getTodoList();
					System.out.println(this.projectId + todoList.size() + "투두리스트 갱신됨");
					refreshTodoList();
				}
				break;
			}
		}
	}

	@FXML
	public void moveChatClicked(MouseEvent event) {
		if (chatDialog == null) {
			chatDialog = new Stage();
			chatDialog.initStyle(StageStyle.DECORATED);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chatting.fxml"));
			ChatController controller = new ChatController(projectTitle);
			fxmlLoader.setController(controller);
			try {
				Scene scene = new Scene(fxmlLoader.load());
				chatDialog.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
			chatDialog.setResizable(false);
			chatDialog.setTitle("Chatting");
			chatDialog.getIcons().add(new Image("file:image/jicon.png"));
			chatDialog.show();
			chatDialog.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							chatDialog = null;
							JChatClient.getInstance()
									.sendToServer(new JChatData(ClientInfo.member.getId(), ClientInfo.member.getName(),
											"님이 접속종료하셨습니다. ", JChatData.DISCONNECTION, LocalDate.now().toString()));
							JChatClient.getInstance().getSubscribers().remove(controller);
						}
					});
				}
			});
		}
		chatDialog.toFront();
	}
}
