package biz;
import java.sql.*;
import static common.JDBCTemplate.*;
import java.util.List;

import Client.JChatClient;
import dao.MemberDAO;
import vo.Member;

public class MemberBiZ {
	
	
	public int createMemberTable() {
		Connection conn = getConnection();
		int res = new MemberDAO(conn).createMemberTable();
		Close(conn);
		return res;
	}

	public List<Member> getSelectAll() {
		Connection conn = getConnection();
		List<Member> all = new MemberDAO(conn).getSelectAll();
		Close(conn);
		return all;
	}

	public Member getSelectES(int id) throws Exception {
		Connection conn = getConnection();
		Member res = new MemberDAO(conn).getSelectES(id);
		Close(conn);
		return res;
	}

	public Member SelectProject(int id) throws Exception {
		Connection conn = getConnection();
		Member res = new MemberDAO(conn).SelectProject(id);
		Close(conn);
		return res;
	}
	
	public Member getSelectEname(String name) throws Exception {
		Connection conn = getConnection();
		Member res = new MemberDAO(conn).getSelectEname(name);
		Close(conn);
		return res;
	}
	
	
	public int getInsertVO(Member vo) throws Exception {
		Connection conn = getConnection();
		int res = new MemberDAO(conn).InsertAll(vo);
		Close(conn);
		return res;
}

	public int DeleteAll(Member vo) {
		Connection conn = getConnection();
		int res = new MemberDAO(conn).DeleteAll(vo);
		Close(conn);
		return res;
	}

	private static MemberBiZ instance = new MemberBiZ();
	public static MemberBiZ getInstance() {
		
		return instance;
	}

//	public int getUpdateVO(MyEmpVO vo) {
//		Connection con = getConnection();
//		int res = new MyEmpDAO(con).getUpdateVO(vo);
//		Close(con);
//		return res;
//	}
//
//	public MyEmpVO getAll_VO02_v2(MyEmpVO input_vo) {
//		Connection con = getConnection();
//		MyEmpVO res = new MyEmpDAO(con).getAll_VO02_V2(input_vo);
//		Close(con);
//		return res;
//	}

}
