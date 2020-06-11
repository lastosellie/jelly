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

	String create_member = "create table member(ID NUMBER, NAME VARCHAR2(10), GENDER NUMBER, PROJECT_ID NUMBER)";
	// 패스워드
	
	// empno, ename, gender,project_id
	String insert_member = "insert into member values(?,?,?,?); end;";
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
