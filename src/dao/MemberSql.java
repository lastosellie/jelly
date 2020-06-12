package dao;

public interface MemberSql {


	// 모든 직원 다 불러오기
	String select_all = "select * from member";

	// 사번으로 검색해서 4가지 정보불러오기
	String select_es = "select * from member where member_id =? ";

	// 이름으로 검색해서 4가지 정보불러오기
	String select_ename = "select * from member where member_name= ? ";

	// 사번으로 groupby해서 ename 셀렉
	String select_projectid = "select member_name from member group by ? ";

	String create_table = "{CALL CREATE_TABLE_IF_DOESNT_EXIT(?,?,?)}";
	String create_member = "create table member(MEMBR_NAME VARCHAR2(10), GENDER NUMBER(1), EMPNO VARCHAR2(20), PASSWORD VARCHAR2(20), PROJECT_ID NUMBER(8), DEPTNO NUMBER(8)";
	// 패스워드
	
	// name, gender, id, pw, teamid, deptno
	String insert_member = "insert into member values(?,?,?,?,?,?)";
	// empno 로 찾아서 지우기?
	String delete_member = "delete from member where empno =?";
	
	
	
	
	
//	BEGIN
//	   EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM TTTT';
//	EXCEPTION
//	   WHEN OTHERS THEN
//	      IF SQLCODE = -942 THEN
//	         EXECUTE IMMEDIATE 'CREATE TABLE TTTT( ID NUMBER)';
//	      END IF;
//	END;

}
