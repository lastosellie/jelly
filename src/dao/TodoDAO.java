package dao;

import static common.JDBCTemplate.Close;
import static common.JDBCTemplate.Commit;
import static common.JDBCTemplate.Rollback;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

public class TodoDAO implements TodoSql {

	private Connection conn;

	public TodoDAO(Connection conn) {
		this.conn = conn;
	}

	public int createTodoTable() {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = conn.prepareStatement(create_table);
			pstm.setString(1, "TODO");
			pstm.setString(2, create_table_todo);
			pstm.setString(3, create_sequence);
			res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("TODO 테이블 준비 완료");
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

	public List<Todo> getSelectAll() {
		Todo vo = null;
		List<Todo> all = new ArrayList<Todo>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareCall(select_all);
			rs = pstm.executeQuery();
			while (rs.next()) {
				vo = new Todo();
				vo.setProjectId(rs.getInt("PROJECT_ID"));
				vo.setAssignee(rs.getString("ASSIGNEE"));
				vo.setId(rs.getInt("TODO_ID"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setStartDate(rs.getDate("START_DATE").toLocalDate());
				vo.setEndDate(rs.getDate("END_DATE").toLocalDate());
				vo.setParentId(rs.getInt("PARENT_ID"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setProgress(rs.getDouble("PROGRESS"));
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
	
	public int getInsertVO(Todo todo) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(todo_insert);
			pstm.setInt(1, todo.getProjectId());
			pstm.setString(2, todo.getAssignee());
			pstm.setString(3, todo.getTitle());
			pstm.setDate(4, Date.valueOf(todo.getStartDate()));
			pstm.setDate(5, Date.valueOf(todo.getEndDate()));
			pstm.setInt(6, todo.getParentId());
			pstm.setString(7, todo.getContent());
			pstm.setDouble(8, todo.getProgress());
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
}
