package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ProjectController implements Initializable {

	@FXML
	private GridPane gridPane;
	@FXML
	private TreeTableView<Todo> treeTableView;

	private ArrayList<Todo> todoList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		todoList.add(new Todo("홍길동", "설계1", LocalDate.now(), LocalDate.now().plusDays(30), "구현하기 전, 설계 검토 step1"));
		todoList.add(new Todo("정길동", "설계2", LocalDate.now(), LocalDate.now().plusDays(5), "구현하기 전, 설계 검토 step2"));
		todoList.add(new Todo("이길동", "설계3", LocalDate.now(), LocalDate.now().plusDays(10), "구현하기 전, 설계 검토 step3"));

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
		
		TreeItem root = new TreeItem(new Todo("전체업무"));
		
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
		gridPane.add(swingNode, 1, 0);
	}

	@FXML
	public void TreeTableViewSelected(MouseEvent event) {
		System.out.println("selected");
	}

}
