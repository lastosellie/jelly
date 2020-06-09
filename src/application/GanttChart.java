package application;

import org.jfree.chart.ChartPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

public class GanttChart {

	private final ChartPanel chartPanel;
	private final JFreeChart chart;
	private final IntervalCategoryDataset dataset;

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public GanttChart(ArrayList<Todo> todoList) {
		dataset = createSampleDataset(todoList);
		chart = ChartFactory.createGanttChart("Gantt Chart Demo", // chart title
				"Task", // domain axis label
				"Date", // range axis label
				dataset, // data
				true, // include legend
				true, // tooltips
				false // urls
		);

		chart.getTitle().setFont(new Font("나눔바른고딕", Font.PLAIN, 0));
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// X 축의 라벨 설정입니다. (보조 타이틀) => "Task"
		plot.getDomainAxis().setLabelFont(new Font("나눔바른고딕", Font.BOLD, 0));

		// X 축의 도메인 설정입니다.
		plot.getDomainAxis().setTickLabelFont(new Font("나눔바른고딕", Font.PLAIN, 13));

		// Y 축의 라벨 설정입니다. (보조 타이틀) => "Date"
		plot.getRangeAxis().setLabelFont(new Font("나눔바른고딕", Font.BOLD, 0));

		// Y 축의 도메인 설정입니다.
		plot.getRangeAxis().setTickLabelFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		DateAxis va = (DateAxis) plot.getRangeAxis();
		va.setDateFormatOverride(new SimpleDateFormat("MM월 dd일,yyyy년"));

//		plot.getDomainAxisForDataset(1).setLabel("aaa");
//		plot.getRangeAxisForDataset(2).setLabel("bbb");

		LegendTitle lt = chart.getLegend();
		lt.visible = false;

		// plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.DARK_GRAY);

		// add the chart to a panel...
		chartPanel = new ChartPanel(chart);
		chartPanel.isMouseWheelEnabled();
		chartPanel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				System.out.println(arg0.getWheelRotation());
				if (arg0.getWheelRotation() > 0) {
	                   chartPanel.zoomOutDomain(0.5, 0.5);
	               } else if (arg0.getWheelRotation() < 0) {
	                   chartPanel.zoomInDomain(1.5, 1.5);
	               }
			}

		});

	}

	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Creates a sample dataset for a Gantt chart, using sub-tasks. In general, you
	 * won't hard-code the dataset in this way - it's done here so that the demo is
	 * self-contained.
	 * 
	 * @param todoList
	 *
	 * @return The dataset.
	 */
	private IntervalCategoryDataset createSampleDataset(ArrayList<Todo> todoList) {

		final TaskSeries ts = new TaskSeries("Scheduled");

		for (Todo todo : todoList) {
			Task task = new Task(todo.getTitle(), date(todo.getStartDate()), date(todo.getEndDate()));
			task.setPercentComplete(0.5);
			ts.add(task);
		}
		// s1.
//		final Task t1 = new Task("Write Proposal", date(1, Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001));
//		t1.setPercentComplete(1.00);
//		s1.add(t1);
//
//		final Task t2 = new Task("Obtain Approval", date(9, Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001));
//		t2.setPercentComplete(1.00);
//		s1.add(t2);

		// here is a task split into two subtasks...
//		final Task t3 = new Task("Requirements Analysis", date(10, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001));
//		final Task st31 = new Task("Requirements 1", date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
//		st31.setPercentComplete(1.0);
//		final Task st32 = new Task("Requirements 2", date(1, Calendar.MAY, 2001), date(5, Calendar.MAY, 2001));
//		st32.setPercentComplete(1.0);
//		t3.addSubtask(st31);
//		t3.addSubtask(st32);
//		s1.add(t3);
//
//		// and another...
//		final Task t4 = new Task("Design Phase", date(6, Calendar.MAY, 2001), date(30, Calendar.MAY, 2001));
//		final Task st41 = new Task("Design 1", date(6, Calendar.MAY, 2001), date(10, Calendar.MAY, 2001));
//		st41.setPercentComplete(1.0);
//		final Task st42 = new Task("Design 2", date(15, Calendar.MAY, 2001), date(20, Calendar.MAY, 2001));
//		st42.setPercentComplete(1.0);
//		final Task st43 = new Task("Design 3", date(23, Calendar.MAY, 2001), date(30, Calendar.MAY, 2001));
//		st43.setPercentComplete(0.50);
//		t4.addSubtask(st41);
//		t4.addSubtask(st42);
//		t4.addSubtask(st43);
//		s1.add(t4);
//
//		final Task t5 = new Task("Design Signoff", date(2, Calendar.JUNE, 2001), date(2, Calendar.JUNE, 2001));
//		s1.add(t5);
//
//		final Task t6 = new Task("Alpha Implementation", date(3, Calendar.JUNE, 2001), date(31, Calendar.JULY, 2001));
//		t6.setPercentComplete(0.60);
//
//		s1.add(t6);
//
//		final Task t7 = new Task("Design Review", date(1, Calendar.AUGUST, 2001), date(8, Calendar.AUGUST, 2001));
//		t7.setPercentComplete(0.0);
//		s1.add(t7);
//
//		final Task t8 = new Task("Revised Design Signoff", date(10, Calendar.AUGUST, 2001),
//				date(10, Calendar.AUGUST, 2001));
//		t8.setPercentComplete(0.0);
//		s1.add(t8);
//
//		final Task t9 = new Task("Beta Implementation", date(12, Calendar.AUGUST, 2001),
//				date(12, Calendar.SEPTEMBER, 2001));
//		t9.setPercentComplete(0.0);
//		s1.add(t9);
//
//		final Task t10 = new Task("Testing", date(13, Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001));
//		t10.setPercentComplete(0.0);
//		s1.add(t10);
//
//		final Task t11 = new Task("Final Implementation", date(1, Calendar.NOVEMBER, 2001),
//				date(15, Calendar.NOVEMBER, 2001));
//		t11.setPercentComplete(0.0);
//		s1.add(t11);
//
//		final Task t12 = new Task("Signoff", date(28, Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001));
//		t12.setPercentComplete(0.0);
//		s1.add(t12);

		final TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(ts);

		return collection;
	}

	/**
	 * Utility method for creating <code>Date</code> objects.
	 *
	 * @param day   the date.
	 * @param month the month.
	 * @param year  the year.
	 *
	 * @return a date.
	 */
	private static Date date(LocalDate date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
		final Date result = calendar.getTime();
		return result;

	}

}
