/* Dao.java
 * project: FireCoder
 */
package net.firecoder.test.dao;


/**
 * @author �˳�
 * create: 2011-7-20
 */
public interface Dao<T> {
	/**
	 * ����ҳ���ò�ѯ��������
	 * @param page ��ҳ����
	 * @return
	 * @throws Exception
	 */
	public Pagination<T> findAll(int startIndex, int pageSize) throws Exception;
	
	/**
	 * ��������
	 * @param data
	 * @return �������ݵ�ID
	 * @throws Exception
	 */
	public String add(T data) throws Exception;
	
	/**
	 * �޸����ݣ������еı�Ž�����Ϊ�޸�����
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(T data) throws Exception;
	
	/**
	 * ɾ�����ݣ������еı�Ž�����Ϊɾ������
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean delete(T data) throws Exception;
	
	/**
	 * ��ȡ���ݣ������еı����Ϊ��ȡ����
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public T get(T data) throws Exception;
}
