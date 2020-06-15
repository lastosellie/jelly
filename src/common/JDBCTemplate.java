package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.IniFile;

/*Connection & Close & Commit & rollback*/
public class JDBCTemplate {
	
	// 연결한 상태로 리턴해주겠다.
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String serverName = "localhost";
		IniFile ini = IniFile.getInstance();
		if (ini.isLoaded()) {
			serverName = ini.getIp();
		}
		String url = "jdbc:oracle:thin:@" + serverName + ":1521:XE";
		String id = "big5";
		String pwd = "admin1234";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
			con.setAutoCommit(false);
			// 이렇게 할당하고 출발한다?
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void Commit(Connection con) {
		try {
			if (con != null & !con.isClosed()) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Rollback(Connection con) {
		try {
			if (con != null & !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}// PreparedStatement 서로 상속관계 이기 때문에 오버로드 재정의하지 않는다.
	}
	
	public static void Close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Close(Connection con) {
		try {
			if (!con.isClosed() && con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
