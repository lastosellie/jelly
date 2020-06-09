package dao;

public interface MemberSql {
	
	String select_all = "select * from member";
	String select_ename = "select ename from emp";
	String select_empno = "select empno from emp";
	String select_es = "select ename, sal from emp";
	String create_member = "create table member(ID NUMBER, NAME VARCHAR2(10), GENDER NUMBER)";

//	BEGIN
//	   EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM TTTT';
//	EXCEPTION
//	   WHEN OTHERS THEN
//	      IF SQLCODE = -942 THEN
//	         EXECUTE IMMEDIATE 'CREATE TABLE TTTT( ID NUMBER)';
//	      END IF;
//	END;

}
