package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

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
	private TreeTableView <Todo> treeTableView;
//	@FXML
//	private Button btn1;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

        final IntervalCategoryDataset dataset = createSampleDataset();

        // create the chart...
        final JFreeChart chart = ChartFactory.createGanttChart(
            "Gantt Chart Demo",  // chart title
            "Task",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );
        chart.getTitle().setFont(new Font("나눔바른고딕", Font.PLAIN, 0));
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        // X 축의 라벨 설정입니다. (보조 타이틀)
        plot.getDomainAxis().setLabelFont(new Font("나눔바른고딕", Font.BOLD, 13));
        // X 축의 도메인 설정입니다.
        plot.getDomainAxis().setTickLabelFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        // Y 축의 라벨 설정입니다. (보조 타이틀)
        plot.getRangeAxis().setLabelFont(new Font("나눔바른고딕", Font.BOLD, 13));
        // Y 축의 도메인 설정입니다.
        plot.getRangeAxis().setTickLabelFont(new Font("나눔바른고딕", Font.PLAIN, 13));

  //      plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        
		final SwingNode swingNode = new SwingNode();
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(chartPanel);
            }
        });
		
		gridPane.add(swingNode, 1, 0);
		
		Todo todo0 = new Todo();
		todo0.setTitle("전체업무");
		
		Todo todo1 = new Todo("홍길동", "설계1", LocalDate.now(), LocalDate.now().plusDays(30),
				"구현하기 전, 설계 검토 step1");
		
		Todo todo2 = new Todo("정길동", "설계2", LocalDate.now(), LocalDate.now().plusDays(5),
				"구현하기 전, 설계 검토 step2");
		
		Todo todo3 = new Todo("이길동", "설계3", LocalDate.now(), LocalDate.now().plusDays(10),
				"구현하기 전, 설계 검토 step3");
		
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
		TreeItem mercedes1 = new TreeItem(todo1);
		TreeItem mercedes2 = new TreeItem(todo2);
		TreeItem mercedes3 = new TreeItem(todo3);

		TreeItem mercedes = new TreeItem(todo0);
		mercedes.getChildren().add(mercedes1);
		mercedes.getChildren().add(mercedes2);
		mercedes.getChildren().add(mercedes3);

		treeTableView.setRoot(mercedes);
//		TreeItem<Todo> root = new TreeItem<Todo>(todo0);
//		root.setExpanded(true);
//		root.getChildren().add(new TreeItem<Todo>(todo1));
//		root.getChildren().add(new TreeItem<Todo>(todo2));
//		root.getChildren().add(new TreeItem<Todo>(todo3));
//		treeView.setRoot(root);
//		// treeView.setShowRoot(false);
//		MultipleSelectionModel msm = treeView.getSelectionModel();
//		msm.select(1);
		treeTableView.setShowRoot(false);
		mercedes.setExpanded(true);
		treeTableView.resizeColumn(treeTableColumn1, 60);
	}

	@FXML
	public void TreeTableViewSelected(MouseEvent event){
		System.out.println("selected");
	}
	
	// ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample dataset for a Gantt chart, using sub-tasks.  In general, you won't 
     * hard-code the dataset in this way - it's done here so that the demo is self-contained.
     *
     * @return The dataset.
     */
    private IntervalCategoryDataset createSampleDataset() {

        final TaskSeries s1 = new TaskSeries("Scheduled");
        
        final Task t1 = new Task(
            "Write Proposal", date(1, Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001)
        );
        t1.setPercentComplete(1.00);
        s1.add(t1);
        
        final Task t2 = new Task(
            "Obtain Approval", date(9, Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001)
        );
        t2.setPercentComplete(1.00);
        s1.add(t2);

        // here is a task split into two subtasks...
        final Task t3 = new Task(
            "Requirements Analysis", 
            date(10, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001)
        );
        final Task st31 = new Task(
            "Requirements 1", 
            date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001)
        );
        st31.setPercentComplete(1.0);
        final Task st32 = new Task(
            "Requirements 2", 
            date(1, Calendar.MAY, 2001), date(5, Calendar.MAY, 2001)
        );
        st32.setPercentComplete(1.0);
        t3.addSubtask(st31);
        t3.addSubtask(st32);
        s1.add(t3);

        // and another...
        final Task t4 = new Task(
            "Design Phase", 
            date(6, Calendar.MAY, 2001), date(30, Calendar.MAY, 2001)
        );
        final Task st41 = new Task(
             "Design 1", 
             date(6, Calendar.MAY, 2001), date(10, Calendar.MAY, 2001)
        );
        st41.setPercentComplete(1.0);
        final Task st42 = new Task(
            "Design 2", 
            date(15, Calendar.MAY, 2001), date(20, Calendar.MAY, 2001)
        );
        st42.setPercentComplete(1.0);
        final Task st43 = new Task(
            "Design 3", 
            date(23, Calendar.MAY, 2001), date(30, Calendar.MAY, 2001)
        );
        st43.setPercentComplete(0.50);
        t4.addSubtask(st41);
        t4.addSubtask(st42);
        t4.addSubtask(st43);
        s1.add(t4);

        final Task t5 = new Task(
            "Design Signoff", date(2, Calendar.JUNE, 2001), date(2, Calendar.JUNE, 2001)
        ); 
        s1.add(t5);
                        
        final Task t6 = new Task(
            "Alpha Implementation", date(3, Calendar.JUNE, 2001), date(31, Calendar.JULY, 2001)
        );
        t6.setPercentComplete(0.60);
        
        s1.add(t6);
        
        final Task t7 = new Task(
            "Design Review", date(1, Calendar.AUGUST, 2001), date(8, Calendar.AUGUST, 2001)
        );
        t7.setPercentComplete(0.0);
        s1.add(t7);
        
        final Task t8 = new Task(
            "Revised Design Signoff", 
            date(10, Calendar.AUGUST, 2001), date(10, Calendar.AUGUST, 2001)
        );
        t8.setPercentComplete(0.0);
        s1.add(t8);
        
        final Task t9 = new Task(
            "Beta Implementation", 
            date(12, Calendar.AUGUST, 2001), date(12, Calendar.SEPTEMBER, 2001)
        );
        t9.setPercentComplete(0.0);
        s1.add(t9);
        
        final Task t10 = new Task(
            "Testing", date(13, Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001)
        );
        t10.setPercentComplete(0.0);
        s1.add(t10);
        
        final Task t11 = new Task(
            "Final Implementation", 
            date(1, Calendar.NOVEMBER, 2001), date(15, Calendar.NOVEMBER, 2001)
        );
        t11.setPercentComplete(0.0);
        s1.add(t11);
        
        final Task t12 = new Task(
            "Signoff", date(28, Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001)
        );
        t12.setPercentComplete(0.0);
        s1.add(t12);

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }

}
