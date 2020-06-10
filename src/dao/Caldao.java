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
		//아래서 만들라고 강요해서 만들었음 
	}
	public static Caldao getInstance() {
		if(caldao==null)
			caldao = new Caldao();
		return caldao;
	}

	// DB에서 SQL문으로 Task 정보들을 가져와
	// List<TaskVO> 타입으로 파싱해서 return
	public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
		 TaskVO vo = null;
		 List<TaskVO> all = new ArrayList<>();
		//connection c; 
		//명령 수행 객체
		Statement stmt = null;
		//결과 select 실행 후 데이터를 리턴받을 객체
		ResultSet rs= null;
		try {
		stmt = conn.createStatement();//명령실행 준비 획득
		rs = stmt.executeQuery(task_all); //select문을 살행 후 결과를 리턴
			while(rs.next()) {
				//결과를 한줄씩 읽어서 vo에 담는다 (생성자로 하나씩담음)
				vo = new TaskVO(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5));			
				//List 객체에 추가한다.
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
		tempTask.setTask_Title("테스트일정 1");
		tempTask.setTask_Progress(1);
		tempTask.setTask_sDate("2020-06-09");
		
		String sql = "";
		
		int year = calMap.get("nYear");
		int month = calMap.get("nMonth");
		int date = calMap.get("inputDate");
		
		sql.format("SELECT * FROM ???? WHERE sYear = '%d' AND sdkfljasl", year, month, date);
		// executesql( ) 해서 sQL 돌리기
		// 결과로 나온 데이터들을 TaskList에 담아서
		//return TaskList; //예시
*/		
	public TaskVO getALLVO(int task_ID) {
		PreparedStatement pstm= null;
		ResultSet rs = null;
		TaskVO vo = null;
		try {
			pstm= conn.prepareStatement(task_vo);
			pstm.setInt(1, task_ID);
			rs = pstm.executeQuery();
			while(rs.next()) { //한개라서 if써도 됨 
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
			pstm = conn.prepareStatement(task_insert); //쿼리 호출
			
			//?매개인자에 값대입
			//(순서대로! number varchar2 number) ->SQL>desc my_emp; 로 확인
			pstm.setInt(1, vo.getTask_ID());
			pstm.setInt(2, vo.getTeam_ID());
			pstm.setString(3, vo.getTask_Title());
			pstm.setString(4, vo.getTask_sDate());
			pstm.setString(5, vo.getTask_eDate());
			//쿼리 실행
			res = pstm.executeUpdate();
			if(res >0 ) {
				System.out.println("입력성공 -DB");
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
		int res = 0; //삭제 결과 저장할 변수
		PreparedStatement pstm = null;//sql문장에 매개변수 ? 를 사용하기 때문
		try {
			pstm = conn.prepareStatement(task_delete);
			//?의 매개변수에 값을 전달
			pstm.setInt(1, vo.getTask_ID());
			//삭제 실행 후 결과 리턴
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

		//보류
		//근데 참고한 깃허브는 클래스를 전부 void로 만듦..그러나 수업은 int로 만들었음..ㅠ
	
	}
*/
	
}
