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
				res = new Member(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				res.setId(rs.getInt("ID"));
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
				vo = new Member(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));

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
				vo = new Member(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));

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
				vo = new Member(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return vo;
	}

	public int InsertAll(Member vo) throws Exception {
		int res = 0;
		CallableStatement pstm = null;
		try {
			pstm = conn.prepareCall(insert_member); // ���� ȣ��

			pstm.setInt(1, vo.getId());
			pstm.setString(2, vo.getName());
			pstm.setInt(3, vo.getGender());
			pstm.setInt(4, vo.getProject_id());

			pstm.execute();
			Commit(conn);

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
			pstm.setInt(1, vo.getId());
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
