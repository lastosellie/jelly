package application;


import java.io.IOException;
import java.net.URL;
import java.util.*;
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
	private List<TaskVO> calList;
	Calendar cal = Calendar.getInstance();

    private int nYear,	
    		    nMonth;
	private int startDay,	
	            lastDay,
	            inputDate;  //1일
    
	static String clickDate;
	static int clickYear,
			   clickMonth;
	//get
	public String getClickDate() {
		return clickDate;
	}

	Label[] lableList;
	HBox[] hboxList;
	VBox[] vboxList;
	
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private GridPane grid;
	    
	    @FXML
	    private URL location;
	    
	    @FXML
	    private HBox hbox1,hbox11 ,hbox12,hbox13, hbox14, hbox15, hbox16, hbox17, hbox18, hbox19
	    			,hbox20, hbox21, hbox22, hbox23, hbox24, hbox25, hbox26, hbox27,hbox28, hbox29
	    			,hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37, hbox38, hbox39
	    			,hbox40, hbox41, hbox42, hbox43, hbox44;
	    
	    @FXML
	    private Label lbl00, lbl001, lbl002 ,lbl003, lbl004 ,lbl005 ,lbl006 ,lbl0010
	    		   	 ,lbl0011, lbl0012 ,lbl0013 ,lbl0014 ,lbl0015,lbl0016 ,lbl0020 
	    		   	 ,lbl0021 ,lbl0022 ,lbl0023 ,lbl0024 ,lbl0025 ,lbl0026 ,lbl0030 
	    		   	 ,lbl0031, lbl0032, lbl0033,lbl0034;
	    
	    @FXML
	    private Button btnAdd;
	    
	    @FXML
	    private Button btnBMonth;

	    @FXML
	    private Button btnToday;

	    @FXML
	    private Button btnNMonth;
	    
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

	    @Override
		public void initialize(URL location, ResourceBundle resources) {
//	    	if(Main.curEmp.getDept_id()!=20) {
//	    		btnAdd.setDisable(true);
//	    	}
	    	hboxList = new HBox[] {
	    			 hbox1,hbox11 ,hbox12,hbox13, hbox14, hbox15, hbox16, hbox17, hbox18, hbox19
		    			,hbox20, hbox21, hbox22, hbox23, hbox24, hbox25, hbox26, hbox27,hbox28, hbox29
		    			,hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37, hbox38, hbox39
		    			,hbox40, hbox41, hbox42, hbox43, hbox44
		    			};
	    	lableList = new Label[] {
	    			 lbl00, lbl001, lbl002 ,lbl003, lbl004 ,lbl005 ,lbl006 ,lbl0010
	    		   	 ,lbl0011, lbl0012 ,lbl0013 ,lbl0014 ,lbl0015,lbl0016 ,lbl0020 
	    		   	 ,lbl0021 ,lbl0022 ,lbl0023 ,lbl0024 ,lbl0025 ,lbl0026 ,lbl0030 
	    		   	 ,lbl0031, lbl0032, lbl0033,lbl0034 };

//	    	service = Calservice.getInstance();
	    	
	    	today();
	    	
	    	changeCalendar(nYear, nMonth);
	        
	    }
	    public void today() {
	    	Calendar calendar = new GregorianCalendar(Locale.KOREA);
	    	nYear = calendar.get(Calendar.YEAR);
	    	nMonth = calendar.get(Calendar.MONTH);
	    	
	    	changeCalendar(nYear, nMonth);
	    }
	    public void changeCalendar(int nYear, int nMonth) {
	    	inputDate=1;
	    	for(int i=0; i<lableList.length; i++) {
	    		lableList[i].setDisable(false);
	    		lableList[i].setText(" ");
	    	}
	    	for(int i=0; i<hboxList.length; i++) {
//	    		hboxList[i].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
	    		hboxList[i].setStyle("-fx-border-color: white;");
	    	}
	    	//현재 날짜 얻어오기
	    	btnToday.setText(nYear + "년 " + (nMonth+1) + "월");
	    	//현재날짜와 달, 1일로 set
	    	cal.set(Calendar.YEAR, nYear);
	    	cal.set(Calendar.MONTH, nMonth);
	    	cal.set(Calendar.DATE, 1);
	    	
	    	startDay = cal.get(Calendar.DAY_OF_WEEK); 
	    	lastDay = cal.getActualMaximum(Calendar.DATE); 
	    	for(int i=0; inputDate<=lastDay; i++){
	    		if(i<startDay-1) {
	    			lableList[i].setText(" ");
	    			lableList[i].setDisable(true);
	    		}else{
	    			lableList[i].setText(inputDate+"");
	    			Map<String, Integer> paramMap = new HashMap<String, Integer>();
	    			
	    			paramMap.put("nYear", nYear);
	    			paramMap.put("nMonth", nMonth+1);
	    			paramMap.put("inputDate", inputDate);
	    			int k = i;
	    			Calservice service = Calservice.getInstance();//추가 
	    			calList = service.getAllCal(paramMap);
	    			if(calList.size()!=0) {
	    				lableList[i].setText(inputDate + "\n" + calList.get(0).getCal_title()); 
	    				lableList[i].setStyle("-fx-font-size: 19px ;");
	    				if(!calList.get(0).getCal_edate().equals(calList.get(0).getCal_sdate())) {
	    					int count = (Integer.parseInt(calList.get(0).getCal_edate())-Integer.parseInt(calList.get(0).getCal_sdate()))+1;
	    					for(int j=0; j<count; j++) {
	    						hboxList[k].setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
	    						k++;
	    					}
	    				}
	    			}
	    			inputDate++;
	    		}
	    	}  
	    }
	    
}
