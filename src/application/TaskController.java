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
	
	// Ķ���� ������Ʈ �� ����Ǵ� ������
	private List<TaskVO> taskList;
	Calendar cal = Calendar.getInstance();
    private int nYear, nMonth;	// ���� ��ȸ���� Year, Month
    private int startDay, lastDay, inputDate;  //1��
	
	// FXML Binding	
	@FXML
    private ResourceBundle resources;

    @FXML
    private GridPane grid;
    
    @FXML
    private URL location;
        
	// hbox 7x6
	@FXML
	private HBox hbox0,	hbox1,	hbox2,	hbox3,	hbox4,	hbox5,	hbox6,	// ù��° ��
				hbox7,	hbox8,	hbox9,	hbox10,	hbox11,	hbox12,	hbox13,
				hbox14,	hbox15,	hbox16,	hbox17,	hbox18,	hbox19,	hbox20,
				hbox21,	hbox22,	hbox23,	hbox24,	hbox25,	hbox26,	hbox27,
				hbox28,	hbox29,hbox30,	hbox31,	hbox32,	hbox33,	hbox34,	
				hbox35,	hbox36,	hbox37,	hbox38,	hbox39,	hbox40,	hbox41,
				
				hbox42,	hbox43,	hbox44,	hbox45,	hbox46,	hbox47,	hbox48,	// �ι�° ��
				hbox49,	hbox50,	hbox51,	hbox52,	hbox53,	hbox54,	hbox55,
				hbox56,	hbox57,	hbox58,	hbox59,	hbox60,	hbox61,	hbox62,	
				hbox63,	hbox64,	hbox65,	hbox66,	hbox67,	hbox68,	hbox69,	
				hbox70,	hbox71,	hbox72,	hbox73,	hbox74,	hbox75,	hbox76,	
				hbox77,	hbox78,	hbox79,	hbox80,	hbox81,	hbox82,	hbox83;

				
	
	// Label 7x6
	@FXML
	private Label lbl0,	lbl1,	lbl2,	lbl3,	lbl4,	lbl5,	lbl6,	// ��¥
				lbl7,	lbl8,	lbl9,	lbl10,	lbl11,	lbl12,	lbl13,
				lbl14,	lbl15,	lbl16,	lbl17,	lbl18,	lbl19,	lbl20,
				lbl21,	lbl22,	lbl23,	lbl24,	lbl25,	lbl26,	lbl27,
				lbl28,	lbl29,lbl30,	lbl31,	lbl32,	lbl33,	lbl34,	
				lbl35,	lbl36,	lbl37,	lbl38,	lbl39,	lbl40,	lbl41,
	
				lbl42,	lbl43,	lbl44,	lbl45,	lbl46,	lbl47,	lbl48,	// ù��° ��
				lbl49,	lbl50,	lbl51,	lbl52,	lbl53,	lbl54,	lbl55,	
				lbl56,	lbl57,	lbl58,	lbl59,	lbl60,	lbl61,	lbl62,	
				lbl63,	lbl64,	lbl65,	lbl66,	lbl67,	lbl68,	lbl69,	
				lbl70,	lbl71,	lbl72,	lbl73,	lbl74,	lbl75,	lbl76,	
				lbl77,	lbl78,	lbl79,	lbl80,	lbl81,	lbl82,	lbl83,

				lbl84,	lbl85,	lbl86,	lbl87,	lbl88,	lbl89,	lbl90,	// �ι�° ��
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
	 * Description	: ���� FXML �ε� �� ����Ǹ�
	 * 				  CalService, ��ü �ʱ�ȭ, ���� ��/���� �޷� �ʱ�ȭ ���� 
	 *===============================================================*/
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// DB ������ ������ ���� Calservice �ν��Ͻ� ������
		service = Calservice.getInstance();
		
		// HBox, Label ��ü���� List �迭�� ���� 
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
			
		// ���� ��ȸ���� ��/���� ���� ��¥�� �ʱ�ȭ
		today();
		
		// �ʱ�ȭ�� ���� ��/�� ��¥�� �޷� ������Ʈ
		changeCalendar(nYear, nMonth);	    
	}
	
	/*===============================================================
	 * Method		: changeCalendar
	 * Description	: ȣ�� �� �Է����ڷ� ���޹��� ��/�� ������ �޷� ���� ������Ʈ
	 *===============================================================*/
	public void changeCalendar(int nYear, int nMonth) 
	{
		// ���� Cell�� �Է��ߴ� ������ ��� : �迭 �ʱ�ȭ
		int[] prevTaskID = new int[] {-1, -1};	
		
		// ���� ��ȸ�ϰ��� �ϴ� ��/�� �޷��� Start/End date ����
		inputDate = 1;
		cal.set(Calendar.YEAR, nYear);
		cal.set(Calendar.MONTH, nMonth);
		cal.set(Calendar.DATE, inputDate);
		startDay = cal.get(Calendar.DAY_OF_WEEK); 
		lastDay = cal.getActualMaximum(Calendar.DATE); 
		
		// ���� �����Ǿ��ִ� Label, HBox�� ��Ȱ��ȭ ����, Text, ���� �ʱ�ȭ
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
				
		// Today Button�� Text��  ��ȸ�ϰ��� �ϴ� ��/���� ���� 
		btnToday.setText(nYear + "�� " + (nMonth+1) + "��");
		
		// Cell Contents �Է�
		for(int i = 0; inputDate <= lastDay; i++){
			// �̹� ���� StartDay���� �ε����� ���� ��� �������� ����
			if(i<startDay-1) {
				//lableList[i].setText(" ");  // ������ �̹� setTest(" ") �߱� ������ ���ʿ�
				labelDList[i].setDisable(true);
				labelList1[i].setDisable(true);
				labelList2[i].setDisable(true);
			}
			
			// �̹� �޿� ��ȿ�� ��¥ Cell�� ���� ó��
			else{
				// Cell�� �ش��ϴ� Date �Է�
				labelDList[i].setText(inputDate+"");
				
/*				// DB���� ������ Task ������ ������
				Map<String, Integer> paramMap = new HashMap<String, Integer>();	// ��¥ ������ ���� Map	    			
				paramMap.put("nYear", nYear);
				paramMap.put("nMonth", nMonth+1);
				paramMap.put("inputDate", inputDate);
				taskList = service.getAllCal(paramMap);
				
				int ColoredCnt = 0;
				int TaskStartCell = i;
				if(taskList.size() != 0) {
					// ���� HBox ��ĥ�Ǿ��ִ� ���� Ȯ��
					Background bg = hboxList[i].getBackground();
					Paint bg_p = 
							///// �� ��׶��� ��ĥ���ִ��� Ȯ���ϱ� + TaskID Ȯ���ϱ�
					
					
    				// List�� �ִ� Task �� ���� 2���� Ķ������ ǥ��
    				for(TaskVO curTask : calList) {
    					curID = curTask.getTaskID();
    					for(int i = 0 ;i<3; i++) {
    						if(prevTaskID[i] == curTaskID) {
    							// ĭ�� ��ĥ�ϴ°�
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
		//Ŭ���� ��¥�� ������ ��
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
							 //detail�並 ���� ��
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
	//	    	//��â 
	//	    	Parent add = FXMLLoader.load(getClass().getResource("Task2.fxml"));
	//	    	Scene scene = new Scene(add);
	//	    	
	//	    	Stage adding = new Stage();
	//	    	
	//	    	adding.setScene(scene);
	//	    	adding.showAndWait();
	//	    	
	//	    	//insert�� changeCalendar()�θ���
	//	    	today();
	//	    	changeCalendar(nYear, nMonth);
	//	    }
	
	
	
	    
}