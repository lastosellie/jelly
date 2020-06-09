package biz;
import java.sql.*;
import static common.JDBCTemplate.*;
import java.util.List;

import application.Member;
import dao.MemberDAO;

public class MemberBiZ {
	
	public int createMemberTable() {
		Connection conn = getConnection();
		int res = new MemberDAO(conn).createMemberTable();
		Close(conn);
		return res;
	}

	public List<Member> getAll_MyEmp() {
		Connection conn = getConnection();
		List<Member> all = new MemberDAO(conn).getSelectAll();
		Close(conn);
		return all;
	}
//
//	public MyEmpVO getAll_VO(String name) {
//		Connection con = getConnection();
//		MyEmpVO res = new MyEmpDAO(con).getAll_VO(name);
//		Close(con);
//		return res;
//	}
//
//	public MyEmpVO getAll_VO02(MyEmpVO input_vo) {
//		Connection con = getConnection();
//		MyEmpVO res = new MyEmpDAO(con).getAll_VO02(input_vo);
//		Close(con);
//		return res;
//	}
//	
//	public int getInsertVO(MyEmpVO vo) {
//		Connection con = getConnection();
//		int res = new MyEmpDAO(con).getInsertVO(vo);
//		Close(con);
//		return res;
//	}
//	
//	public int getDeleteVO(MyEmpVO vo) {
//		Connection con = getConnection();
//		int res = new MyEmpDAO(con).getDeleteVO(vo);
//		Close(con);
//		return res;
//	}
//
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
