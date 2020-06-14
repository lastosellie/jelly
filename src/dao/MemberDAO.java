package dao;

import static common.JDBCTemplate.Close;
import static common.JDBCTemplate.Commit;
import static common.JDBCTemplate.Rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;
import vo.Todo;

public class MemberDAO implements MemberSql {

	protected Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Member> getSelectAll() {
		Member vo = null;
		List<Member> all = new ArrayList<Member>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareCall(select_all);
			rs = pstm.executeQuery();
			while (rs.next()) {
				vo = new Member();
				vo.setName(rs.getString("MEMBR_NAME"));
				vo.setGender(rs.getInt("GENDER"));
				vo.setId(rs.getString("EMPNO"));
				all.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(pstm);
		}
		return all;
	}

	public Member getSelectVO(String empno) {
		Member vo = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareCall(select_vo);
			pstm.setString(1, empno);
			rs = pstm.executeQuery();
			if (rs.next()) {
				vo = new Member();
				vo.setName(rs.getString("MEMBR_NAME"));
				vo.setGender(rs.getInt("GENDER"));
				vo.setId(rs.getString("EMPNO"));
				vo.setpw(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(pstm);
		}
		return vo;
	}

	public int getInsertVO(Member member) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(insert_member);
			pstm.setString(1, member.getName());
			pstm.setInt(2, member.getGender());
			pstm.setString(3, member.getId());
			pstm.setString(4, member.getPw());
			res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("입력 성공");
				Commit(conn);
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			// 이미 등록된 아이디 처리
			res = -1;
			Rollback(conn);
		} catch (Exception e) {
			System.out.println(e);
			Rollback(conn);
		} finally {
			Close(pstm);
		}
		return res;
	}

}
