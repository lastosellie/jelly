package application;

import java.util.List;
import java.util.Map;


public class Caldao implements ICaldao {
//	static Logger logger = Logger.getLogger(CalendarDao.class);
	private static Caldao caldao;
//	private SqlMapClient smc;
	public Caldao() {
//		smc = IbatisUtil.getSqlMapClient();
	}
	public static Caldao getInstance() {
		if(caldao==null)
			caldao = new Caldao();
		return caldao;
	}
	@Override
	public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCal(TaskVO calVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TaskVO> getDetail(Map<String, Integer> calMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCal(int cal_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDetail(TaskVO calVo) {
		// TODO Auto-generated method stub
		
	}
	

}
