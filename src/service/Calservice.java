package service;

import java.util.List;
import java.util.Map;

import dao.Caldao;
import vo.TaskVO;

public class Calservice {
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

		
		public List<TaskVO> getAllCal(Map<String, Integer> calMap) {
			return calDao.getAllCal(calMap);
		}

		public TaskVO getALLVO(int task_ID) {
			return calDao.getALLVO(task_ID);
		}

		public void insertCal(TaskVO calVo) { //dao에서 int로 했는데void로 만들어도 돼는가
			calDao.insertCal(calVo); 
		}
		
		public void deleteCal(TaskVO vo) {
			calDao.deleteCal(vo);
		}

/*		public void updateDetail(TaskVO calVo) {
			calDao.updateDetail(calVo);
		}
*/
}
