package dao;

public interface TaskSql {

		/////SELECT //////
		String task_all= "select * from TB_TASK" ;
		//?�� �Ű������� ����ϰڴ�.
		String task_vo="SELECT * FROM TB_TASK WHERE TID =?";
//		String task_vo02="SELECT * FROM TB_TASK WHERE TID =? AND =?"; 

		/////////////////INSERT , DELETE , UPDATE /////////////
		String task_insert = "insert into TB_TASK values(?,?,?,?)";
		String task_delete = "delete from TB_TASK where TTITLE =?";
		//�����ȣ�� ã�Ƽ� �̸� �μ���ȣ ����
		String task_update = "update TB_TASK set ename =?, deptno =? where empno=?";
}
