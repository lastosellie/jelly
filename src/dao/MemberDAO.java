package dao;

import java.util.*;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import vo.*; // Emp vo
import static common.JDBCTemplate.*;
// CRUD�� �����ϴ� db sql �ڵ�

public class MemberDAO implements MemberSql {
	
	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}
	
	public int createMemberTable() {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = conn.prepareStatement(create_member);
			res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("MEMBER ���̺� ���� ����");
				Commit(conn);
			}
		} catch (Exception e) {
			System.out.println(e);
			Rollback(conn);
		} finally {
			Close(pstm);
		}
		return res;
	}
	
	public List<Member> getSelectAll() throws Exception {
		Member vo = null; // ���ڵ� ���� ���� ��ü
		List<Member> all = new ArrayList<Member>(); // EMP ��ü ���Ϲ��� ��ü
		Connection con = getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery(select_all);
		while (rs.next()) {
			vo = new Member();
			vo.setId(rs.getInt("ID"));
			vo.setName(rs.getString("NAME"));
			all.add(vo);
		}
		rs.close();
		stmt.close();
		Close(con);
		return all;
	}
	
	public List<Emp> getSelectEmpno() throws Exception {
		Emp vo = null; // ���ڵ� ���� ���� ��ü
		List<Emp> all = new ArrayList<Emp>(); // EMP ��ü ���Ϲ��� ��ü
		Connection con = getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery(select_empno);
		while (rs.next()) {
			vo = new Emp();
			vo.setEmpno(rs.getInt("empno"));
			all.add(vo);
		}
		rs.close();
		stmt.close();
		Close(con);
		return all;
	}
	
	public List<Emp> getSelectEname() throws Exception {
		Emp vo = null; // ���ڵ� ���� ���� ��ü
		List<Emp> all = new ArrayList<Emp>(); // EMP ��ü ���Ϲ��� ��ü
		Connection con = getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery(select_ename);
		while (rs.next()) {
			vo = new Emp();
			vo.setEname(rs.getString("ename"));
			all.add(vo);
		}
		rs.close();
		stmt.close();
		Close(con);
		return all;
	}

}
