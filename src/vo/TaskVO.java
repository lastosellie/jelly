package vo;

public class TaskVO {
	
	public TaskVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	// ??
	private String clickTitle,clickCont;
	private int calnum;
	
	// Task info
	private int Task_ID;
	private String Task_Title;
	private String Task_sDate, Task_eDate;
	private int Task_Du;
		
	public String getClickTitle() {
		return clickTitle;
	}
	public void setClickTitle(String clickTitle) {
		this.clickTitle = clickTitle;
	}
	public String getClickCont() {
		return clickCont;
	}
	public void setClickCont(String clickCont) {
		this.clickCont = clickCont;
	}
	public int getCalnum() {
		return calnum;
	}
	public void setCalnum(int calnum) {
		this.calnum = calnum;
	}
	
	public TaskVO(int task_ID, String task_Title, String task_sDate, String task_eDate, int task_du) {
		super();
		Task_ID = task_ID;
		Task_Title = task_Title;
		Task_sDate = task_sDate;
		Task_eDate = task_eDate;
		Task_Du = task_du;
	}
	public int getTask_ID() {
		return Task_ID;
	}
	public void setTask_ID(int task_ID) {
		Task_ID = task_ID;
	}
	public String getTask_Title() {
		return Task_Title;
	}
	public void setTask_Title(String task_Title) {
		Task_Title = task_Title;
	}
	public String getTask_sDate() {
		return Task_sDate;
	}
	public void setTask_sDate(String task_sDate) {
		Task_sDate = task_sDate;
	}
	public String getTask_eDate() {
		return Task_eDate;
	}
	public void setTask_eDate(String task_eDate) {
		Task_eDate = task_eDate;
	}
	public int getTask_Du() {
		return Task_Du;
	}
	public void setTask_Du(int task_Du) {
		Task_Du = task_Du;
	}
		
	
}
