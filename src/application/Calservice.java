package application;

import java.util.List;
import java.util.Map;

public class Calservice implements ICalservice{
		private static Calservice calService;
		private Caldao calDao;
		
		public static Calservice getInstance() {
			if(calService == null)
				calService = new Calservice();
			return calService;
		}
		public Calservice() {
			calDao = Caldao.getInstance();
		}
		@Override
		public void insertCal(TaskVO calVo) {
			calDao.insertCal(calVo);
		}
		@Override
		public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
			return calDao.getAllCal(calMap);
		}

		public List<TaskVO> getDetail(Map<String, Integer> calMap) {
			return calDao.getDetail(calMap);
		}
		@Override
		public void deleteCal(int cal_num) {
			calDao.deleteCal(cal_num);
		}
		@Override
		public void updateDetail(TaskVO calVo) {
			calDao.updateDetail(calVo);
		}
}
