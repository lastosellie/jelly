package application;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import service.Calservice;

import vo.TaskVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CalinController implements Initializable{
	private Calservice service;
	
	// FXML Binding	
	@FXML
	private ResourceBundle resources;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private URL location;
	
	@FXML
	private TextField txtTitle;

    @FXML
    private DatePicker Sdate;

    @FXML
    private DatePicker Edate;

    @FXML
    private TextField TName;
    
    @FXML
    private Button addDate;

    @FXML
    private Button delDate;

    @FXML
    void addClick(MouseEvent event) {
    	//1. 화면에서 값들 가져오기
    	String title = txtTitle.getText();
    	String sdate = Sdate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	String edate = Edate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	String teamName = TName.getText();
  
    	// TaskVO 만들기
    	TaskVO calVo = new TaskVO();
    	calVo.setTask_ID(service.getNewPJID());
    	calVo.setTask_Title(title);
    	calVo.setTask_eDate(edate);
    	calVo.setTask_sDate(sdate);
    	//calVo.setEmp_id(Main.curEmp.getEmp_id()); //지금로그인한 사람의 사원번호
    	
    	service.insertCal(calVo);     	
    	
    	Stage thisForm = (Stage) addDate.getScene().getWindow();
    	thisForm.close();
    }
    //alert창
    public void errorMsg(String head, String msg) {
		Alert info = new Alert(AlertType.ERROR);
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("이니셜 들어옴");
    	service = Calservice.getInstance();
    	
    	Sdate.setValue(LocalDate.now());
    	Edate.setOnAction(e->{
    		if(Sdate.getValue().isAfter(Edate.getValue())) {
    			errorMsg("날짜 입력 오류 !", "종료날짜가 시작날짜보다 빠릅니다.");
    		}
    	});
	}
}
