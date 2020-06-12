package dao;

import java.util.*;

import Client.JChatClient;
import biz.MemberBiZ;
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
			pstm = conn.prepareStatement(create_table);
			pstm.setString(1, "MEMBER");
			pstm.setString(2, create_member);
			pstm.setString(3, "");
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

	public List<Member> getSelectAll() {
		Member res = null; // ���ڵ� ���� ���� ��ü
		List<Member> all = new ArrayList<Member>(); // EMP ��ü ���Ϲ��� ��ü

		// ��ɼ��ఴü
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			;
			rs = stmt.executeQuery(select_all);
			while (rs.next()) {
				res = new Member(rs.getString(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				res.setId(rs.getString("ID"));
				res.setName(rs.getString("NAME"));
				all.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return all;
	}

	// ������� �˻�
	public Member getSelectES(int id) throws Exception {

		Member vo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(select_es);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				vo = new Member(rs.getString(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return vo;
	}

	// �̸����� �˻�
	public Member getSelectEname(String name) throws Exception {
		Member vo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(select_ename);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				vo = new Member(rs.getString(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return vo;
	}

	// ������� �׷�����ؼ� ename ����
	public Member SelectProject(int id) throws Exception {
		Member vo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(select_projectid);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				vo = new Member(rs.getString(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return vo;
	}

	
	//ȸ������ name, gender, id(���), pw, teamid, deptno
	public int InsertAll(Member vo) throws Exception {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(insert_member); // ���� ȣ��

			pstm.setString(1, vo.getName());
			pstm.setInt(2, vo.getGender());
			pstm.setString(3, vo.getId());
			pstm.setString(4, vo.getPw());
			pstm.setInt(5, vo.getProject_id());
			pstm.setInt(6, vo.getDeptno());
			
			

			res = pstm.executeUpdate();
			if(res>0) {
				System.out.println("����");
				Commit(conn);
			}
			

			// ������ �����Ѵ�.
			/*
			 * res = pstm.executeUpdate(); // insert, delete, update �Ҷ� ��� if(res > 0) {
			 * System.out.println("�Է�Ȯ���߾�"); Commit(conn); }
			 */
		} catch (Exception e) {
			Rollback(conn);
		} finally {

			Close(pstm);
		}
		return res;
	}

	public int DeleteAll(Member vo) {
		int res = 0; // ���� ��� ������ ����
		CallableStatement pstm = null; // sql���忡 �Ű����� ? �� ����ϱ� �����̴�.
		try {
			pstm = conn.prepareCall(delete_member);
			// ?�� �Ű������� ���� ����
			pstm.setString(1, vo.getId());
			// ���� ���� �� ����� ����
			res = pstm.executeUpdate(); // insert, delete, update query
			if (res > 0) {
				Commit(conn);
			}
		} catch (Exception e) {
			Rollback(conn);
		} finally {

			Close(pstm);
		}
		return res;
	}

	
}
