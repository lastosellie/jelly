package dao;

public interface MemberSql {


	// ��� ���� �� �ҷ�����
	String select_all = "select * from member";

	// ������� �˻��ؼ� 4���� �����ҷ�����
	String select_es = "select * from member where member_id =? ";

	// �̸����� �˻��ؼ� 4���� �����ҷ�����
	String select_ename = "select * from member where member_name= ? ";

	// ������� groupby�ؼ� ename ����
	String select_projectid = "select member_name from member group by ? ";

	String create_table = "{CALL CREATE_TABLE_IF_DOESNT_EXIT(?,?,?)}";
	String create_member = "create table member(MEMBR_NAME VARCHAR2(10), GENDER NUMBER(1), EMPNO VARCHAR2(20), PASSWORD VARCHAR2(20), PROJECT_ID NUMBER(8), DEPTNO NUMBER(8)";
	// �н�����
	
	// name, gender, id, pw, teamid, deptno
	String insert_member = "insert into member values(?,?,?,?,?,?)";
	// empno �� ã�Ƽ� �����?
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
