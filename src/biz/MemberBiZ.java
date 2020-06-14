package biz;

import static common.JDBCTemplate.Close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import dao.ProjectMemberDAO;
import vo.Member;

public class MemberBiZ {

	public int getInsertVO(Member member) {
		Connection conn = getConnection();
		int res = new MemberDAO(conn).getInsertVO(member);
		Close(conn);
		return res;
	}

	public int getInsertVO(int projectId, String memberId) {
		Connection conn = getConnection();
		int res = new ProjectMemberDAO(conn).getInsertVO(projectId, memberId);
		Close(conn);
		return res;
	}

	public List<Member> getSelectAll() {
		Connection conn = getConnection();
		List<Member> all = new MemberDAO(conn).getSelectAll();
		Close(conn);
		return all;
	}

	public Member getSelectVO(String empno) {
		Connection conn = getConnection();
		Member vo = new MemberDAO(conn).getSelectVO(empno);
		Close(conn);
		return vo;
	}

	public List<String> getSelectAll(int projectId) {
		Connection conn = getConnection();
		List<String> ids = new ProjectMemberDAO(conn).getSelectAll(projectId);
		Close(conn);
		return ids;
	}
}
