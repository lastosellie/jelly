package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.IniFile;

/*Connection & Close & Commit & rollback*/
public class JDBCTemplate {
	
	// ������ ���·� �������ְڴ�.
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
			// �̷��� �Ҵ��ϰ� ����Ѵ�?
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
		}// PreparedStatement ���� ��Ӱ��� �̱� ������ �����ε� ���������� �ʴ´�.
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
