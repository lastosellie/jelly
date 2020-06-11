package dao;

import java.util.*;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import vo.*; // Emp vo
import static common.JDBCTemplate.*;
// CRUD를 실행하는 db sql 코드

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
				System.out.println("MEMBER 테이블 생성 성공");
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
		Member res = null; // 레코드 한줄 담을 객체
		List<Member> all = new ArrayList<Member>(); // EMP 전체 리턴받을 객체

		// 명령수행객체
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

	// 사번으로 검색
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

	// 이름으로 검색
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

	// 사번으로 그룹바이해서 ename 셀렉
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
			pstm = conn.prepareCall(insert_member); // 쿼리 호출

			pstm.setInt(1, vo.getId());
			pstm.setString(2, vo.getName());
			pstm.setInt(3, vo.getGender());
			pstm.setInt(4, vo.getProject_id());

			pstm.execute();
			Commit(conn);

			// 쿼리를 실행한다.
			/*
			 * res = pstm.executeUpdate(); // insert, delete, update 할때 사용 if(res > 0) {
			 * System.out.println("입력확인했어"); Commit(conn); }
			 */
		} catch (Exception e) {
			Rollback(conn);
		} finally {

			Close(pstm);
		}
		return res;
	}

	public int DeleteAll(Member vo) {
		int res = 0; // 삭제 결과 저장할 변수
		CallableStatement pstm = null; // sql문장에 매개벼수 ? 를 사용하기 때문이다.
		try {
			pstm = conn.prepareCall(delete_member);
			// ?의 매개변수에 값을 전달
			pstm.setInt(1, vo.getId());
			// 삭제 실행 후 결과를 리턴
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
