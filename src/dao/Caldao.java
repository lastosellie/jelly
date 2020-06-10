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
	private Connection conn ;
	private static Caldao caldao;

	public Caldao(Connection conn) {
		this.conn = conn;
	}
	public Caldao() {
		//�Ʒ��� ������ �����ؼ� ������� 
	}
	public static Caldao getInstance() {
		if(caldao==null)
			caldao = new Caldao();
		return caldao;
	}

	// DB���� SQL������ Task �������� ������
	// List<TaskVO> Ÿ������ �Ľ��ؼ� return
	public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
		 TaskVO vo = null;
		 List<TaskVO> all = new ArrayList<>();
		//connection c; 
		//��� ���� ��ü
		Statement stmt = null;
		//��� select ���� �� �����͸� ���Ϲ��� ��ü
		ResultSet rs= null;
		try {
		stmt = conn.createStatement();//��ɽ��� �غ� ȹ��
		rs = stmt.executeQuery(task_all); //select���� ���� �� ����� ����
			while(rs.next()) {
				//����� ���پ� �о vo�� ��´� (�����ڷ� �ϳ�������)
				vo = new TaskVO(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5));			
				//List ��ü�� �߰��Ѵ�.
				all.add(vo);			
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			Close(rs);
			Close(stmt);
		}
		return all;
	}			
/*		TaskVO tempTask = new TaskVO();
		tempTask.setTask_ID(1);
		tempTask.setTask_Title("�׽�Ʈ���� 1");
		tempTask.setTask_Progress(1);
		tempTask.setTask_sDate("2020-06-09");
		
		String sql = "";
		
		int year = calMap.get("nYear");
		int month = calMap.get("nMonth");
		int date = calMap.get("inputDate");
		
		sql.format("SELECT * FROM ???? WHERE sYear = '%d' AND sdkfljasl", year, month, date);
		// executesql( ) �ؼ� sQL ������
		// ����� ���� �����͵��� TaskList�� ��Ƽ�
		//return TaskList; //����
*/		
	public TaskVO getALLVO(int task_ID) {
		PreparedStatement pstm= null;
		ResultSet rs = null;
		TaskVO vo = null;
		try {
			pstm= conn.prepareStatement(task_vo);
			pstm.setInt(1, task_ID);
			rs = pstm.executeQuery();
			while(rs.next()) { //�Ѱ��� if�ᵵ �� 
				vo = new TaskVO(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5));
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			Close(rs);
			Close(pstm);
		}
		return vo;
	}
	public int insertCal(TaskVO vo) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(task_insert); //���� ȣ��
			
			//?�Ű����ڿ� ������
			//(�������! number varchar2 number) ->SQL>desc my_emp; �� Ȯ��
			pstm.setInt(1, vo.getTask_ID());
			pstm.setInt(2, vo.getTeam_ID());
			pstm.setString(3, vo.getTask_Title());
			pstm.setString(4, vo.getTask_sDate());
			pstm.setString(5, vo.getTask_eDate());
			//���� ����
			res = pstm.executeUpdate();
			if(res >0 ) {
				System.out.println("�Է¼��� -DB");
				Commit(conn);
			}
		}catch(Exception e) {
				Rollback(conn);
			}finally {
				Close(pstm);
			
		}
		return res;
	}
	public int deleteCal(TaskVO vo) {
		int res = 0; //���� ��� ������ ����
		PreparedStatement pstm = null;//sql���忡 �Ű����� ? �� ����ϱ� ����
		try {
			pstm = conn.prepareStatement(task_delete);
			//?�� �Ű������� ���� ����
			pstm.setInt(1, vo.getTask_ID());
			//���� ���� �� ��� ����
			res = pstm.executeUpdate();//insert, delete, uppdate query
			if( res > 0) {
				Commit(conn);
			}
		}catch(Exception e) {
				Rollback(conn);
			}finally {
				Close(pstm);
			}
		return res;
		
	}
/*	public void updateDetail(TaskVO calVo) {

		//����
		//�ٵ� ������ ������ Ŭ������ ���� void�� ����..�׷��� ������ int�� �������..��
	
	}
*/
	
}
