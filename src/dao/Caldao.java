package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vo.TaskVO;
import static common.JDBCTemplate.*;

public class Caldao implements TaskSql {
	private Connection conn;
	private static Caldao caldao;

	public Caldao() {
		this.conn = getConnection();
	}

	public static Caldao getInstance() {
		if (caldao == null)
			caldao = new Caldao();
		return caldao;
	}

	// DB���� SQL������ Task �������� ������
	// List<TaskVO> Ÿ������ �Ľ��ؼ� return
	public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
		TaskVO vo = null;
		List<TaskVO> all = new ArrayList<>();
		// connection c;
		// ��� ���� ��ü
		PreparedStatement ptmt = null;
		// ��� select ���� �� �����͸� ���Ϲ��� ��ü
		ResultSet rs = null;
		String strDate = String.format("%d-%02d-%02d", calMap.get("nYear"), calMap.get("nMonth"),
				calMap.get("inputDate"));

		try {
			ptmt = conn.prepareStatement(task_all);// ��ɽ��� �غ� ȹ��
			ptmt.setString(1, strDate);
			ptmt.setString(2, strDate);
			rs = ptmt.executeQuery(); // select���� ���� �� ����� ����
			while (rs.next()) {
				// ����� ���پ� �о vo�� ��´� (�����ڷ� �ϳ�������)
				vo = new TaskVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				// List ��ü�� �߰��Ѵ�.
				all.add(vo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Close(rs);
			Close(ptmt);
		}
		return all;
	}
	/*
	 * TaskVO tempTask = new TaskVO(); tempTask.setTask_ID(1);
	 * tempTask.setTask_Title("�׽�Ʈ���� 1"); tempTask.setTask_Progress(1);
	 * tempTask.setTask_sDate("2020-06-09");
	 * 
	 * String sql = "";
	 * 
	 * int year = calMap.get("nYear"); int month = calMap.get("nMonth"); int date =
	 * calMap.get("inputDate");
	 * 
	 * sql.format("SELECT * FROM ???? WHERE sYear = '%d' AND sdkfljasl", year,
	 * month, date); // executesql( ) �ؼ� sQL ������ // ����� ���� �����͵��� TaskList�� ��Ƽ�
	 * //return TaskList; //����
	 */
//	public TaskVO getALLVO(int task_ID) {
//		PreparedStatement pstm= null;
//		ResultSet rs = null;
//		TaskVO vo = null;
//		try {
//			pstm= conn.prepareStatement(task_vo);
//			pstm.setInt(1, task_ID);
//			rs = pstm.executeQuery();
//			while(rs.next()) { //�Ѱ��� if�ᵵ �� 
//				vo = new TaskVO(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5));
//			}
//		}catch(Exception e) {
//			System.out.println(e.toString());
//		}finally {
//			Close(rs);
//			Close(pstm);
//		}
//		return vo;
//	}

	public int getNewPJID() {
		int newID = 1;
		Statement stmt = null;
		PreparedStatement pstm = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(task_getLastID);

			if (rs.next()) {
				newID = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
		} finally {
			Close(pstm);
		}
		return newID;
	}

	public int insertCal(TaskVO vo) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(task_insert); // ���� ȣ��

			// ?�Ű����ڿ� ������
			// (�������! number varchar2 number) ->SQL>desc my_emp; �� Ȯ��

			pstm.setInt(1, vo.getTask_ID());
			pstm.setString(2, vo.getTask_Title());
			pstm.setString(3, vo.getTask_sDate());
			pstm.setString(4, vo.getTask_eDate());
			// ���� ����
			res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("�Է� ����");
				Commit(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Rollback(conn);
		} finally {
			Close(pstm);

		}
		return res;
	}

	public int deleteCal(int ID) {
		int res = 0; // ���� ��� ������ ����
		PreparedStatement pstm = null;// sql���忡 �Ű����� ? �� ����ϱ� ����
		try {
			pstm = conn.prepareStatement(task_delete);
			// ?�� �Ű������� ���� ����
			pstm.setInt(1, ID);
			// ���� ���� �� ��� ����
			res = pstm.executeUpdate();// insert, delete, uppdate query
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
	/*
	 * public void updateDetail(TaskVO calVo) {
	 * 
	 * //���� //�ٵ� ������ ������ Ŭ������ ���� void�� ����..�׷��� ������ int�� �������..��
	 * 
	 * }
	 */

}
