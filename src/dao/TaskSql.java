package dao;

public interface TaskSql {

		/////SELECT //////
		String task_all= "SELECT * FROM TB_TASK WHERE TSDATE <= ? AND TEDATE >= ? ORDER BY TSDATE" ;
		//?�� �Ű������� ����ϰڴ�.
		//String task_vo="SELECT * FROM TB_TASK WHERE TSDATE <= '?' AND TEDATE >= '?'";
//		String task_vo02="SELECT * FROM TB_TASK WHERE TID =? AND =?"; 

		/////////////////INSERT , DELETE , UPDATE /////////////
		String task_insert = "insert into TB_TASK(PROJECT_ID ,TTITLE,TSDATE ,TEDATE) values(?,?,?,?)";
		String task_delete = "delete from TB_TASK where TaskID=?";
		//�����ȣ�� ã�Ƽ� �̸� �μ���ȣ ����
		String task_update = "update TB_TASK set ename =?, deptno =? where empno=?";
}
