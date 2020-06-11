package dao;

public interface MemberSql {

	String select_all = "select * from member";
	String select_ename = "select ename from emp";
	String select_empno = "select empno from emp";
	String select_es = "select ename, sal from emp";
	
	String create_table = "{CALL CREATE_TABLE_IF_DOESNT_EXIT(?,?)}";
	String create_table_member = "CREATE TABLE MEMBER(MEMBER_ID NUMBER, MEMBER_NAME VARCHAR2(10), PROJECT_ID NUMBER, GENDER NUMBER, IMAGE_URL VARCHAR2(260))";
}
