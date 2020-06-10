package application;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import service.Calservice;
import vo.TaskVO;

import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TaskController implements Initializable {
	
	// Service
	Calservice service;
	
	// Calendar Contents
	private Label[] labelDList, labelList1, labelList2;
	private HBox[] hboxList1, hboxList2;
	private VBox[] vboxList;
	
	// Click Data
	static String clickDate;
	static int clickYear;
	static int clickMonth;
	
	// 캘린더 업데이트 시 변경되는 데이터
	private List<TaskVO> taskList;
	Calendar cal = Calendar.getInstance();
    private int nYear, nMonth;	// 현재 조회중인 Year, Month
    private int startDay, lastDay, inputDate;  //1일
	
	// FXML Binding	
	@FXML
    private ResourceBundle resources;

    @FXML
    private GridPane grid;
    
    @FXML
    private URL location;
        
	// hbox 7x6
	@FXML
	private HBox hbox0,	hbox1,	hbox2,	hbox3,	hbox4,	hbox5,	hbox6,	// 첫번째 줄
				hbox7,	hbox8,	hbox9,	hbox10,	hbox11,	hbox12,	hbox13,
				hbox14,	hbox15,	hbox16,	hbox17,	hbox18,	hbox19,	hbox20,
				hbox21,	hbox22,	hbox23,	hbox24,	hbox25,	hbox26,	hbox27,
				hbox28,	hbox29,hbox30,	hbox31,	hbox32,	hbox33,	hbox34,	
				hbox35,	hbox36,	hbox37,	hbox38,	hbox39,	hbox40,	hbox41,
				
				hbox42,	hbox43,	hbox44,	hbox45,	hbox46,	hbox47,	hbox48,	// 두번째 줄
				hbox49,	hbox50,	hbox51,	hbox52,	hbox53,	hbox54,	hbox55,
				hbox56,	hbox57,	hbox58,	hbox59,	hbox60,	hbox61,	hbox62,	
				hbox63,	hbox64,	hbox65,	hbox66,	hbox67,	hbox68,	hbox69,	
				hbox70,	hbox71,	hbox72,	hbox73,	hbox74,	hbox75,	hbox76,	
				hbox77,	hbox78,	hbox79,	hbox80,	hbox81,	hbox82,	hbox83;

				
	
	// Label 7x6
	@FXML
	private Label lbl0,	lbl1,	lbl2,	lbl3,	lbl4,	lbl5,	lbl6,	// 날짜
				lbl7,	lbl8,	lbl9,	lbl10,	lbl11,	lbl12,	lbl13,
				lbl14,	lbl15,	lbl16,	lbl17,	lbl18,	lbl19,	lbl20,
				lbl21,	lbl22,	lbl23,	lbl24,	lbl25,	lbl26,	lbl27,
				lbl28,	lbl29,lbl30,	lbl31,	lbl32,	lbl33,	lbl34,	
				lbl35,	lbl36,	lbl37,	lbl38,	lbl39,	lbl40,	lbl41,
	
				lbl42,	lbl43,	lbl44,	lbl45,	lbl46,	lbl47,	lbl48,	// 첫번째 줄
				lbl49,	lbl50,	lbl51,	lbl52,	lbl53,	lbl54,	lbl55,	
				lbl56,	lbl57,	lbl58,	lbl59,	lbl60,	lbl61,	lbl62,	
				lbl63,	lbl64,	lbl65,	lbl66,	lbl67,	lbl68,	lbl69,	
				lbl70,	lbl71,	lbl72,	lbl73,	lbl74,	lbl75,	lbl76,	
				lbl77,	lbl78,	lbl79,	lbl80,	lbl81,	lbl82,	lbl83,

				lbl84,	lbl85,	lbl86,	lbl87,	lbl88,	lbl89,	lbl90,	// 두번째 줄
				lbl91,	lbl92,	lbl93,	lbl94,	lbl95,	lbl96,	lbl97,	
				lbl98,	lbl99,	lbl100,	lbl101,	lbl102,	lbl103,	lbl104,	
				lbl105,	lbl106,	lbl107,	lbl108,	lbl109,	lbl110,	lbl111,	
				lbl112,	lbl113,	lbl114,	lbl115,	lbl116,	lbl117,	lbl118,	
				lbl119,	lbl120,	lbl121,	lbl122,	lbl123,	lbl124,	lbl125;

	
	@FXML
	private Button btnAdd;
	
	@FXML
	private Button btnBMonth;
	
	@FXML
	private Button btnToday;
	
	@FXML
	private Button btnNMonth;
		

	/*===============================================================
	 * Method		: initialize
	 * Description	: 최초 FXML 로드 시 수행되며
	 * 				  CalService, 객체 초기화, 현재 연/월로 달력 초기화 진행 
	 *===============================================================*/
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// DB 데이터 조작을 위한 Calservice 인스턴스 가져옴
		service = Calservice.getInstance();
		
		// HBox, Label 객체들을 List 배열로 넣음 
		hboxList1 = new HBox[] {
				hbox0,	hbox1,	hbox2,	hbox3,	hbox4,	hbox5,	hbox6,
				hbox7,	hbox8,	hbox9,	hbox10,	hbox11,	hbox12,	hbox13,
				hbox14,	hbox15,	hbox16,	hbox17,	hbox18,	hbox19,	hbox20,
				hbox21,	hbox22,	hbox23,	hbox24,	hbox25,	hbox26,	hbox27,
				hbox28,	hbox29,	hbox30,	hbox31,	hbox32,	hbox33,	hbox34,	
				hbox35,	hbox36,	hbox37,	hbox38,	hbox39,	hbox40,	hbox41
		};
		hboxList2 = new HBox[] {
				hbox42,	hbox43,	hbox44,	hbox45,	hbox46,	hbox47,	hbox48,
				hbox49,	hbox50,	hbox51,	hbox52,	hbox53,	hbox54,	hbox55,
				hbox56,	hbox57,	hbox58,	hbox59,	hbox60,	hbox61,	hbox62,	
				hbox63,	hbox64,	hbox65,	hbox66,	hbox67,	hbox68,	hbox69,	
				hbox70,	hbox71,	hbox72,	hbox73,	hbox74,	hbox75,	hbox76,	
				hbox77,	hbox78,	hbox79,	hbox80,	hbox81,	hbox82,	hbox83
		};
		labelDList = new Label[] {
				lbl0,	lbl1,	lbl2,	lbl3,	lbl4,	lbl5,	lbl6,
				lbl7,	lbl8,	lbl9,	lbl10,	lbl11,	lbl12,	lbl13,
				lbl14,	lbl15,	lbl16,	lbl17,	lbl18,	lbl19,	lbl20,
				lbl21,	lbl22,	lbl23,	lbl24,	lbl25,	lbl26,	lbl27,
				lbl28,	lbl29,	lbl30,	lbl31,	lbl32,	lbl33,	lbl34,	
				lbl35,	lbl36,	lbl37,	lbl38,	lbl39,	lbl40,	lbl41
		};
		labelList1 = new Label[] {
				lbl42,	lbl43,	lbl44,	lbl45,	lbl46,	lbl47,	lbl48,
				lbl49,	lbl50,	lbl51,	lbl52,	lbl53,	lbl54,	lbl55,	
				lbl56,	lbl57,	lbl58,	lbl59,	lbl60,	lbl61,	lbl62,	
				lbl63,	lbl64,	lbl65,	lbl66,	lbl67,	lbl68,	lbl69,	
				lbl70,	lbl71,	lbl72,	lbl73,	lbl74,	lbl75,	lbl76,	
				lbl77,	lbl78,	lbl79,	lbl80,	lbl81,	lbl82,	lbl83
		};
		labelList2 = new Label[] {
				lbl84,	lbl85,	lbl86,	lbl87,	lbl88,	lbl89,	lbl90,
				lbl91,	lbl92,	lbl93,	lbl94,	lbl95,	lbl96,	lbl97,	
				lbl98,	lbl99,	lbl100,	lbl101,	lbl102,	lbl103,	lbl104,	
				lbl105,	lbl106,	lbl107,	lbl108,	lbl109,	lbl110,	lbl111,	
				lbl112,	lbl113,	lbl114,	lbl115,	lbl116,	lbl117,	lbl118,	
				lbl119,	lbl120,	lbl121,	lbl122,	lbl123,	lbl124,	lbl125
		};

//   	if(Main.curEmp.getDept_id()!=20) {
//    		btnAdd.setDisable(true);
//    	}
			
		// 현재 조회중인 연/월을 오늘 날짜로 초기화
		today();
		
		// 초기화된 오늘 연/월 날짜로 달력 업데이트
		changeCalendar(nYear, nMonth);	    
	}
	
	/*===============================================================
	 * Method		: changeCalendar
	 * Description	: 호출 시 입력인자로 전달받은 연/월 정보로 달력 내용 업데이트
	 *===============================================================*/
	public void changeCalendar(int nYear, int nMonth) 
	{
		// 이전 Cell에 입력했던 데이터 기록 : 배열 초기화
		int[] prevTaskID = new int[] {-1, -1};	
		
		// 현재 조회하고자 하는 연/월 달력의 Start/End date 생성
		inputDate = 1;
		cal.set(Calendar.YEAR, nYear);
		cal.set(Calendar.MONTH, nMonth);
		cal.set(Calendar.DATE, inputDate);
		startDay = cal.get(Calendar.DAY_OF_WEEK); 
		lastDay = cal.getActualMaximum(Calendar.DATE); 
		
		// 기존 설정되어있던 Label, HBox의 비활성화 여부, Text, 색상 초기화
		for(int i = 0; i < labelDList.length && i < labelList1.length && i < labelList2.length; i++) {
			labelDList[i].setDisable(false);	labelDList[i].setText(" ");
			labelList1[i].setDisable(false);	labelList1[i].setText(" ");
			labelList2[i].setDisable(false);	labelList2[i].setText(" ");
		} 
		for(int i=0; i<hboxList1.length && i<hboxList2.length ; i++) {
		    hboxList1[i].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
			hboxList1[i].setStyle("-fx-border-color: white;");			
			hboxList2[i].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
			hboxList2[i].setStyle("-fx-border-color: white;");			
		}
				
		// Today Button의 Text를  조회하고자 하는 연/월로 변경 
		btnToday.setText(nYear + "년 " + (nMonth+1) + "월");
		
		// Cell Contents 입력
		for(int i = 0; inputDate <= lastDay; i++){
			// 이번 달의 StartDay보다 인덱스가 작은 경우 공백으로 유지
			if(i<startDay-1) {
				//lableList[i].setText(" ");  // 위에서 이미 setTest(" ") 했기 때문에 불필요
				labelDList[i].setDisable(true);
				labelList1[i].setDisable(true);
				labelList2[i].setDisable(true);
			}
			
			// 이번 달에 유효한 날짜 Cell에 대한 처리
			else{
				// Cell에 해당하는 Date 입력
				labelDList[i].setText(inputDate+"");
				
/*				// DB에서 오늘의 Task 데이터 가져옴
				Map<String, Integer> paramMap = new HashMap<String, Integer>();	// 날짜 전달을 위한 Map	    			
				paramMap.put("nYear", nYear);
				paramMap.put("nMonth", nMonth+1);
				paramMap.put("inputDate", inputDate);
				taskList = service.getAllCal(paramMap);
				
				int ColoredCnt = 0;
				int TaskStartCell = i;
				if(taskList.size() != 0) {
					// 현재 HBox 색칠되어있는 개수 확인
					Background bg = hboxList[i].getBackground();
					Paint bg_p = 
							///// ★ 백그라운드 색칠돼있는지 확인하기 + TaskID 확보하기
					
					
    				// List에 있는 Task 중 상위 2개만 캘린더에 표출
    				for(TaskVO curTask : calList) {
    					curID = curTask.getTaskID();
    					for(int i = 0 ;i<3; i++) {
    						if(prevTaskID[i] == curTaskID) {
    							// 칸에 색칠하는거
    						}
    					}
    				}
					
					
					hboxList[k].getBackground().
					//lableList[i].setText(inputDate + "\n" + calList.get(0).getTask_Title());
					lableList[i].setText(Integer.toString(inputDate));
					lableList[i].setStyle("-fx-font-size: 19px ;");
					
    				if(!calList.get(0).getCal_edate().equals(calList.get(0).getCal_sdate())) {
    					int count = (Integer.parseInt(calList.get(0).getCal_edate())-Integer.parseInt(calList.get(0).getCal_sdate()))+1;
    					for(int j=0; j<count; j++) {
    						hboxList[k].setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
    						k++;
    					}
    				}
    				
				}*/
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
	
	
	
	@FXML
	void bMonthClick(ActionEvent event) {
		if(nMonth==0) {
			nMonth = 11;
			nYear -=1;
		}else {
			nMonth -=1;
		}
		changeCalendar(nYear, nMonth);
	}
	
	@FXML
	void nMonthClick(ActionEvent event) {
		if(nMonth==11) {
			nMonth =0;
			nYear +=1;
		}else {
			nMonth +=1;
		}
		changeCalendar(nYear, nMonth);
	}
	
	@FXML
	void todayClick(ActionEvent event) {
		today();
	}
	
	@FXML
	void clickGrid(MouseEvent e)  throws IOException {
		//클릭한 날짜를 얻어오는 것
		for( Node node : grid.getChildren()) {
	        if( node instanceof VBox) {
	            ((VBox)node).setOnMouseClicked(ee->{
	            	clickDate = ((Label)(((VBox)(ee.getSource())).getChildren().get(1))).getText();
					 if(clickDate.length()>2) {
						 clickDate = clickDate.substring(0,2);
						 try {
							Integer.parseInt(clickDate);
						} catch (Exception e2) {
							clickDate = clickDate.substring(0,1);
						}
					 }
					 clickYear = nYear;
					 clickMonth = nMonth;
					 
					 if(e.getClickCount()==2) {
						 node.setStyle("-fx-border-color: #fe4371; -fx-border-width: 2;");
						 try {
							 //detail뷰를 띄우는 곳
							 Parent detail = FXMLLoader.load(getClass().getResource("detailCalendar.fxml"));
							 Scene scene = new Scene(detail);
							 Stage stage = new Stage();
							 stage.setX(1000);
							 stage.setY(200);
							 stage.setScene(scene);
							 stage.setTitle("datailMail");
							 stage.showAndWait();
							 
							 changeCalendar(clickYear, clickMonth);
							 node.setStyle("-fx-border-color: white black black white; -fx-border-style:  segments(3, 3, 3, 3);");
						 } catch (IOException e1) {
							 e1.printStackTrace();
						 }
					 }
	            });
	        }
	    }
		
	}

	//get
	public String getClickDate() {
		return clickDate;
	}
	
	//	    @FXML
	//	    void addclcik(ActionEvent event) throws IOException {
	//	    	//새창 
	//	    	Parent add = FXMLLoader.load(getClass().getResource("Task2.fxml"));
	//	    	Scene scene = new Scene(add);
	//	    	
	//	    	Stage adding = new Stage();
	//	    	
	//	    	adding.setScene(scene);
	//	    	adding.showAndWait();
	//	    	
	//	    	//insert후 changeCalendar()부르기
	//	    	today();
	//	    	changeCalendar(nYear, nMonth);
	//	    }
	
	
	
	    
}