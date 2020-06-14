package dao;

public interface MemberSql {

	String select_all = "SELECT * FROM MEMBER";

	String select_vo = "SELECT * FROM MEMBER WHERE EMPNO=?";

	String insert_member = "INSERT INTO MEMBER(MEMBR_NAME, GENDER, EMPNO, PASSWORD) VALUES(?,?,?,?)";
	
	String select_all_project_member = "SELECT * FROM PROJECT_MEMBER WHERE TB_TASK_PROJECT_ID=?";
	
	String insert_project_member = "INSERT INTO PROJECT_MEMBER VALUES(?,?)";

}
