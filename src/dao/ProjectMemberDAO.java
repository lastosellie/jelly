package dao;

import static common.JDBCTemplate.Close;
import static common.JDBCTemplate.Commit;
import static common.JDBCTemplate.Rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;

public class ProjectMemberDAO extends MemberDAO implements MemberSql {

	public ProjectMemberDAO(Connection conn) {
		super(conn);
	}

	public int getInsertVO(int projectId, String memberId) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(insert_project_member);
			pstm.setInt(1, projectId);
			pstm.setString(2, memberId);
			res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("입력 성공");
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

	public List<String> getSelectAll(int projectId) {
		List<String> all = new ArrayList<String>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareCall(select_all_project_member);
			pstm.setInt(1, projectId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				all.add(rs.getString("MEMBER_EMPNO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(pstm);
		}
		return all;
	}

	
}
