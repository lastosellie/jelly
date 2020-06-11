package biz;

import static common.JDBCTemplate.Close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import dao.TodoDAO;
import vo.Member;
import vo.Todo;

public class TodoBiZ {

	public int createTodoTable() {
		Connection conn = getConnection();
		int res = new TodoDAO(conn).createTodoTable();
		Close(conn);
		return res;
	}

	public List<Todo> getSelectAll() {
		Connection conn = getConnection();
		List<Todo> all = new TodoDAO(conn).getSelectAll();
		Close(conn);
		return all;
	}
	
	public int getInsertVO(Todo todo) {
		Connection conn = getConnection();
		int res = new TodoDAO(conn).getInsertVO(todo);
		Close(conn);
		return res;
	}

}
