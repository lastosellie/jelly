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

	String create_member = "create table member(ID NUMBER, NAME VARCHAR2(10), GENDER NUMBER, PROJECT_ID NUMBER)";
	// �н�����
	
	// empno, ename, gender,project_id
	String insert_member = "insert into member values(?,?,?,?); end;";
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
