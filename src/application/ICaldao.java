package application;

import java.util.List;
import java.util.Map;

public interface ICaldao {
	/**
	 * DB�� ����ִ� calendar��ü���� ��ȯ�ϴ� �޼���
	 * @param year, month, day�� ����ִ� map�� �Ѱ��ְ� �׿� �´� ���� �������� �޼���
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
	public List<TaskVO> getDetail(Map<String, Integer> calMap);
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
