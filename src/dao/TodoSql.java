package dao;

public interface TodoSql {

	String select_all = "SELECT * FROM TODO WHERE PROJECT_ID=? ORDER BY TODO_ID";
	String todo_delete_id = "DELETE FROM TODO WHERE TODO_ID=?";
	String todo_delete_projectid = "DELETE FROM TODO WHERE PROJECT_ID=?";
	String todo_insert = "INSERT INTO TODO VALUES(?,?,TODO_SEQ.NEXTVAL,?,?,?,?,?,?)";
	
	String create_table = "{CALL CREATE_TABLE_IF_DOESNT_EXIT(?,?,?)}";
	String create_table_todo= "CREATE TABLE TODO (\r\n" + 
			"	PROJECT_ID NUMBER(4),\r\n" + 
			"	ASSIGNEE VARCHAR2(10),\r\n" + 
			"	TODO_ID NUMBER(4),\r\n" + 
			"	TITLE VARCHAR(200),\r\n" + 
			"	START_DATE DATE,\r\n" + 
			"	END_DATE DATE,\r\n" + 
			"	PARENT_ID NUMBER,\r\n" + 
			"	CONTENT VARCHAR(500),\r\n" + 
			"	PROGRESS NUMBER\r\n" + 
			")";
	String create_sequence = " CREATE SEQUENCE TODO_SEQ\r\n" + 
			" START WITH 1\r\n" + 
			" MINVALUE 1\r\n" + 
			" MAXVALUE 9999\r\n" + 
			" INCREMENT BY 1";
}
