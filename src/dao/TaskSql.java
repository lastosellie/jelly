package dao;

public interface TaskSql {

		
		/////SELECT //////
		String task_all= "SELECT * FROM TB_TASK WHERE TSDATE <= ? AND TEDATE >= ? ORDER BY TSDATE" ;
		String task_getLastID = "SELECT MAX(PROJECT_ID) FROM TB_TASK";
		//?에 매개변수를 사용하겠다.
		//String task_vo="SELECT * FROM TB_TASK WHERE TSDATE <= '?' AND TEDATE >= '?'";
//		String task_vo02="SELECT * FROM TB_TASK WHERE TID =? AND =?"; 

		/////////////////INSERT , DELETE , UPDATE /////////////
		String task_insert = "insert into TB_TASK(PROJECT_ID ,TTITLE,TSDATE ,TEDATE) values(?,?,?,?)";
		String task_delete = "delete from TB_TASK where TaskID=?";
		//사원번호를 찾아서 이름 부서번호 수정
		String task_update = "update TB_TASK set ename =?, deptno =? where empno=?";
}
