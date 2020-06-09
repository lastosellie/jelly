package application;

import java.util.List;
import java.util.Map;

public interface ICalservice {
	/**
	 * DB�� ����ִ� calendar��ü���� ��ȯ�ϴ� �޼���
	 * @return CalendarVO ��ü�� ����ִ� list
	 */
	public List<TaskVO> getAllCal(Map<String, Integer> calMap);
	/**
	 * 
	 * @param calVo : insert�� ������ ����ִ� CalendarVO��ü�� �־���
	 */
	public void insertCal(TaskVO calVo);
	/**
	 * title�� cont�� �������� �޼���
	 * @param calMap ��¥�� Map�� �־� �����ش�
	 * @return ��¥�� �´� Ÿ��Ʋ�� cont�� �����´�
	 */
//	public List<calDateVO> getDetail(Map<String, Integer> calMap);
	/**
	 * ������ �־��ָ�����Ǵ� �޼���
	 * @param title
	 */
	public void deleteCal(int cal_num);
	/**
	 * 
	 * �Խñ��� �������ִ� �޼���
	 * @param calVo
	 */
	public void updateDetail(TaskVO calVo);
}
