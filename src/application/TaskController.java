package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import service.Calservice;
import vo.Member;
import vo.TaskVO;

import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TaskController implements Initializable {

	// Service
	Calservice service;

	// Calendar Contents
	private Label[] labelDList, labelList1, labelList2;
	private HBox[] hboxList1, hboxList2;
	private VBox[] vboxList;
	private String[] colorList;
	int colorIdx;
	int[][] CalTaskID = new int[42][2];

	// private Button[] btnList;

	// Click Data
	static String clickDate;
	static int clickYear;
	static int clickMonth;

	// 캘린더 업데이트 시 변경되는 데이터
	private List<TaskVO> taskList;
	Calendar cal = Calendar.getInstance();
	private int nYear, nMonth; // 현재 조회중인 Year, Month
	private int startDay, lastDay, inputDate; // 1일

	// FXML Binding
	@FXML
	private ResourceBundle resources;

	@FXML
	private GridPane grid;

	@FXML
	private URL location;

	// hbox 7x6
	@FXML
	private HBox hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, // 첫번째 줄
			hbox7, hbox8, hbox9, hbox10, hbox11, hbox12, hbox13, hbox14, hbox15, hbox16, hbox17, hbox18, hbox19, hbox20,
			hbox21, hbox22, hbox23, hbox24, hbox25, hbox26, hbox27, hbox28, hbox29, hbox30, hbox31, hbox32, hbox33,
			hbox34, hbox35, hbox36, hbox37, hbox38, hbox39, hbox40, hbox41,

			hbox42, hbox43, hbox44, hbox45, hbox46, hbox47, hbox48, // 두번째 줄
			hbox49, hbox50, hbox51, hbox52, hbox53, hbox54, hbox55, hbox56, hbox57, hbox58, hbox59, hbox60, hbox61,
			hbox62, hbox63, hbox64, hbox65, hbox66, hbox67, hbox68, hbox69, hbox70, hbox71, hbox72, hbox73, hbox74,
			hbox75, hbox76, hbox77, hbox78, hbox79, hbox80, hbox81, hbox82, hbox83;

	// Label 7x6
	@FXML
	private Label lbl0, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, // 날짜
			lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22,
			lbl23, lbl24, lbl25, lbl26, lbl27, lbl28, lbl29, lbl30, lbl31, lbl32, lbl33, lbl34, lbl35, lbl36, lbl37,
			lbl38, lbl39, lbl40, lbl41,

			lbl42, lbl43, lbl44, lbl45, lbl46, lbl47, lbl48, // 첫번째 줄
			lbl49, lbl50, lbl51, lbl52, lbl53, lbl54, lbl55, lbl56, lbl57, lbl58, lbl59, lbl60, lbl61, lbl62, lbl63,
			lbl64, lbl65, lbl66, lbl67, lbl68, lbl69, lbl70, lbl71, lbl72, lbl73, lbl74, lbl75, lbl76, lbl77, lbl78,
			lbl79, lbl80, lbl81, lbl82, lbl83,

			lbl84, lbl85, lbl86, lbl87, lbl88, lbl89, lbl90, // 두번째 줄
			lbl91, lbl92, lbl93, lbl94, lbl95, lbl96, lbl97, lbl98, lbl99, lbl100, lbl101, lbl102, lbl103, lbl104,
			lbl105, lbl106, lbl107, lbl108, lbl109, lbl110, lbl111, lbl112, lbl113, lbl114, lbl115, lbl116, lbl117,
			lbl118, lbl119, lbl120, lbl121, lbl122, lbl123, lbl124, lbl125;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnBMonth;

	@FXML
	private Button btnToday;

	@FXML
	private Button btnNMonth;

	private Stage dialog;

	private ProjectListController projectController;

	private Stage stage;

	/*
	 * @FXML private Button btn1, btn2;
	 */
	/*
	 * =============================================================== Method :
	 * initialize Description : 최초 FXML 로드 시 수행되며 CalService, 객체 초기화, 현재 연/월로 달력 초기화
	 * 진행 ===============================================================
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// DB 데이터 조작을 위한 Calservice 인스턴스 가져옴
		service = Calservice.getInstance();

		// HBox, Label 객체들을 List 배열로 넣음
		hboxList1 = new HBox[] { hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10, hbox11,
				hbox12, hbox13, hbox14, hbox15, hbox16, hbox17, hbox18, hbox19, hbox20, hbox21, hbox22, hbox23, hbox24,
				hbox25, hbox26, hbox27, hbox28, hbox29, hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37,
				hbox38, hbox39, hbox40, hbox41 };
		hboxList2 = new HBox[] { hbox42, hbox43, hbox44, hbox45, hbox46, hbox47, hbox48, hbox49, hbox50, hbox51, hbox52,
				hbox53, hbox54, hbox55, hbox56, hbox57, hbox58, hbox59, hbox60, hbox61, hbox62, hbox63, hbox64, hbox65,
				hbox66, hbox67, hbox68, hbox69, hbox70, hbox71, hbox72, hbox73, hbox74, hbox75, hbox76, hbox77, hbox78,
				hbox79, hbox80, hbox81, hbox82, hbox83 };
		labelDList = new Label[] { lbl0, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12,
				lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, lbl25, lbl26, lbl27,
				lbl28, lbl29, lbl30, lbl31, lbl32, lbl33, lbl34, lbl35, lbl36, lbl37, lbl38, lbl39, lbl40, lbl41 };
		labelList1 = new Label[] { lbl42, lbl43, lbl44, lbl45, lbl46, lbl47, lbl48, lbl49, lbl50, lbl51, lbl52, lbl53,
				lbl54, lbl55, lbl56, lbl57, lbl58, lbl59, lbl60, lbl61, lbl62, lbl63, lbl64, lbl65, lbl66, lbl67, lbl68,
				lbl69, lbl70, lbl71, lbl72, lbl73, lbl74, lbl75, lbl76, lbl77, lbl78, lbl79, lbl80, lbl81, lbl82,
				lbl83 };
		labelList2 = new Label[] { lbl84, lbl85, lbl86, lbl87, lbl88, lbl89, lbl90, lbl91, lbl92, lbl93, lbl94, lbl95,
				lbl96, lbl97, lbl98, lbl99, lbl100, lbl101, lbl102, lbl103, lbl104, lbl105, lbl106, lbl107, lbl108,
				lbl109, lbl110, lbl111, lbl112, lbl113, lbl114, lbl115, lbl116, lbl117, lbl118, lbl119, lbl120, lbl121,
				lbl122, lbl123, lbl124, lbl125 };
		// 막대 색상
		colorIdx = 0;
		colorList = new String[] { "yellow", "cyan", "gray", "pink", "green", "magenta", "lightGray", "blue" };

		for (int i = 0; i < hboxList1.length; i++) {
			hboxList1[i].setOnMouseClicked((e) -> {
				hboxClicked(e);
			});
			hboxList2[i].setOnMouseClicked((e) -> {
				hboxClicked(e);
			});
		}
		/*
		 * btnList = new Button[] { btn1, btn2 };
		 * 
		 * for(int i=0; i<btnList.length; i++) { int testi = i+15;
		 * btnList[i].setOnAction(e -> { if(CalTaskID[testi][0] > 0) {
		 * service.deleteCal(CalTaskID[testi][0]); } }); }
		 */
		// 현재 조회중인 연/월을 오늘 날짜로 초기화
		today();

		// 초기화된 오늘 연/월 날짜로 달력 업데이트
		changeCalendar(nYear, nMonth);
	}

	public void hboxClicked(MouseEvent event) {
		Object obj = ((HBox) event.getSource()).getUserData();
		if (obj == null) {
			return;
		}
		stage = (Stage) ((HBox) (event.getSource())).getScene().getWindow();
		Map<String, Integer> paramMap = (Map<String, Integer>) obj;

		if (dialog == null) {
			dialog = new Stage();
			dialog.initStyle(StageStyle.DECORATED);
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectListDialog.fxml"));
				if (projectController == null) {
					projectController = new ProjectListController();
				}
				fxmlLoader.setController(projectController);
				Scene scene = new Scene(fxmlLoader.load());
				dialog.setTitle("PROJECT LIST");
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.getIcons().add(new Image("/jicon.png"));
				dialog.show();
				dialog.setOnHidden(e -> {
					this.dialog = null;
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dialog.toFront();
		String title = String.format("%s년 %s월 %s일", paramMap.get("nYear"), paramMap.get("nMonth"),
				paramMap.get("inputDate"));
		projectController.setLabel(title);
		List<TaskVO> taskList = service.getAllCal(paramMap);
		projectController.setProjectList(taskList);
	}

	/*
	 * =============================================================== Method :
	 * changeCalendar Description : 호출 시 입력인자로 전달받은 연/월 정보로 달력 내용 업데이트
	 * ===============================================================
	 */
	public void changeCalendar(int nYear, int nMonth) {
		colorIdx = 0;
		// 이전 Cell에 입력했던 데이터 기록 : 배열 초기화
		int[] prevTaskID = new int[] { -1, -1 };
		boolean[] isRemoved = new boolean[] { true, true };
		boolean[] isEmpty = new boolean[] { true, true };
		int[] IDtoRemove = new int[] { -1, -1 };
		int curID = -1;
		int removed = 0;

		// 현재 조회하고자 하는 연/월 달력의 Start/End date 생성
		inputDate = 1;
		cal.set(Calendar.YEAR, nYear);
		cal.set(Calendar.MONTH, nMonth);
		cal.set(Calendar.DATE, inputDate);
		startDay = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DATE);

		// 기존 설정되어있던 Label, HBox의 비활성화 여부, Text, 색상 초기화
		for (int i = 0; i < labelDList.length && i < labelList1.length && i < labelList2.length; i++) {
			labelDList[i].setDisable(false);
			labelDList[i].setText(" ");
			labelList1[i].setDisable(false);
			labelList1[i].setText(" ");
			labelList2[i].setDisable(false);
			labelList2[i].setText(" ");
		}
		for (int i = 0; i < hboxList1.length && i < hboxList2.length; i++) {
			hboxList1[i]
					.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			hboxList1[i].setStyle("-fx-border-color: white;");
			hboxList2[i]
					.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			hboxList2[i].setStyle("-fx-border-color: white;");
		}
		for (int i = 0; i < CalTaskID.length; i++) {
			for (int j = 0; j < 2; j++) {
				CalTaskID[i][j] = -1;
			}
		}

		// Today Button의 Text를 조회하고자 하는 연/월로 변경
		btnToday.setText(nYear + "년 " + (nMonth + 1) + "월");

		// Cell Contents 입력
		for (int i = 0; inputDate <= lastDay; i++) {
			// 이번 달의 StartDay보다 인덱스가 작은 경우 공백으로 유지
			if (i < startDay - 1) {
				// lableList[i].setText(" "); // 위에서 이미 setTest(" ") 했기 때문에 불필요
				labelDList[i].setDisable(true);
				labelList1[i].setDisable(true);
				labelList2[i].setDisable(true);
			}

			// 이번 달에 유효한 날짜 Cell에 대한 처리
			else {
				// Cell에 해당하는 Date 입력
				labelDList[i].setText(inputDate + "");

				// DB에서 오늘의 Task 데이터 가져옴
				Map<String, Integer> paramMap = new HashMap<String, Integer>(); // 날짜 전달을 위한 Map
				paramMap.put("nYear", nYear);
				paramMap.put("nMonth", nMonth + 1);
				paramMap.put("inputDate", inputDate);
				taskList = service.getAllCal(paramMap);

				//paramMap.put("inputDate", i);
				hboxList1[i].setUserData(paramMap);
				hboxList2[i].setUserData(paramMap);

				if (taskList.size() != 0) {
					isRemoved[0] = isRemoved[1] = false;
					isEmpty[0] = isEmpty[1] = true;
					IDtoRemove[0] = IDtoRemove[1] = -1;

					// hbar에 색칠되어있는 Task 삭제
					if (prevTaskID[0] > 0 || prevTaskID[1] > 0) {
						for (int j = 0; j < taskList.size(); j++) {
							// 2개 삭제 완료했으면 그만 탐색하기
							if (isRemoved[0] == true && isRemoved[1] == true)
								break;

							// 둘중 하나라도 같은 ID가 있으면
							curID = taskList.get(j).getTask_ID();
							if (curID == prevTaskID[0]) {
								IDtoRemove[0] = j;
								isRemoved[0] = true;
								isEmpty[0] = false;

								continue;
							}
							if (curID == prevTaskID[1]) {
								IDtoRemove[1] = j;
								isRemoved[1] = true;
								isEmpty[1] = false;

								continue;
							}
						}
					}

					// 삭제
					if (IDtoRemove[0] > IDtoRemove[1]) {
						if (isRemoved[0] == true && IDtoRemove[0] >= 0)
							taskList.remove(IDtoRemove[0]);
						if (isRemoved[1] == true && IDtoRemove[1] >= 0)
							taskList.remove(IDtoRemove[1]);
					} else {
						if (isRemoved[1] == true && IDtoRemove[1] >= 0)
							taskList.remove(IDtoRemove[1]);
						if (isRemoved[0] == true && IDtoRemove[0] >= 0)
							taskList.remove(IDtoRemove[0]);
					}

					if (taskList.size() > 0) {
						// 빈칸이 있고, 남은 Task가 있는 경우
						if (isRemoved[0] == false || isRemoved[1] == false) {
							String sDate = String.format("%d-%02d-%02d", nYear, nMonth + 1, inputDate);
							TaskVO curTask = null;

							// 첫 줄
							if (isEmpty[0] == true) {
								prevTaskID[0] = -1;

								curTask = popTodayStartTask(sDate);
								if (curTask != null) {
									labelList1[i].setText(curTask.getTask_Title());
									curID = curTask.getTask_ID();

									int count = curTask.getTask_Du();
									for (int j = 0; j < count; j++) {
										if (i + j >= hboxList1.length)
											break;

										hboxList1[i + j].setStyle("-fx-background-color:" + colorList[colorIdx] + ";");
										CalTaskID[i + j][0] = curID;
									}
									colorIdx = colorIdx + 1 < colorList.length ? colorIdx + 1 : 0;

									prevTaskID[0] = curID;
									isEmpty[0] = false;
								}
							}

							// 둘째 줄
							if (isEmpty[1] == true) {
								prevTaskID[1] = -1;

								curTask = popTodayStartTask(sDate);
								if (curTask != null) {
									labelList2[i].setText(curTask.getTask_Title());
									curID = curTask.getTask_ID();

									int count = curTask.getTask_Du();
									for (int j = 0; j < count; j++) {
										if (i + j >= hboxList2.length)
											break;

										hboxList2[i + j].setStyle("-fx-background-color:" + colorList[colorIdx] + ";");
										CalTaskID[i + j][1] = curID;
									}
									colorIdx = colorIdx + 1 < colorList.length ? colorIdx + 1 : 0;

									prevTaskID[1] = curID;
									isEmpty[1] = false;
								}
							}
						}

						// 2개가 삭제됐으면 +n만 표시해주면 됨
						if (taskList.size() > 0) {
							labelDList[i].setText(inputDate + "\t\t+" + taskList.size());
						}
					}
				}
				inputDate++;
			}
		}
	}

	public void today() {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		nYear = calendar.get(Calendar.YEAR);
		nMonth = calendar.get(Calendar.MONTH);

		changeCalendar(nYear, nMonth);
	}

	private TaskVO popTodayStartTask(String sDate) {
		for (int i = 0; i < taskList.size(); i++) {
			// if(taskList.get(i).getTask_sDate() == sDate) {
			if (taskList.get(i).getTask_sDate().substring(0, 10).equals(sDate)) {
				TaskVO ret = taskList.get(i);
				taskList.remove(i);
				return ret;
			}
		}
		return null;
	}

	@FXML
	void bMonthClick(ActionEvent event) {
		if (nMonth == 0) {
			nMonth = 11;
			nYear -= 1;
		} else {
			nMonth -= 1;
		}
		changeCalendar(nYear, nMonth);
	}

	@FXML
	void nMonthClick(ActionEvent event) {
		if (nMonth == 11) {
			nMonth = 0;
			nYear += 1;
		} else {
			nMonth += 1;
		}
		changeCalendar(nYear, nMonth);
	}

	@FXML
	void todayClick(ActionEvent event) {
		today();
	}

	// get
	public String getClickDate() {
		return clickDate;
	}

	@FXML
	public void NewButtonClicked(ActionEvent event) {
		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.DECORATED);
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("NewProjectDialog.fxml"));
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setResizable(false);
		dialog.getIcons().add(new Image("file:image/jicon.png"));
		dialog.setTitle("New Project");
		dialog.showAndWait();

		changeCalendar(nYear, nMonth);
	}

	// @FXML
	// void addclcik(ActionEvent event) throws IOException {
	// //새창
	// Parent add = FXMLLoader.load(getClass().getResource("Task2.fxml"));
	// Scene scene = new Scene(add);
	//
	// Stage adding = new Stage();
	//
	// adding.setScene(scene);
	// adding.showAndWait();
	//
	// //insert후 changeCalendar()부르기
	// today();
	// changeCalendar(nYear, nMonth);
	// }

}