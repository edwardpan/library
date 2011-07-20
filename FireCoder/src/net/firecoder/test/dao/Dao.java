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
	
	public boolean add(T data) throws Exception;
	
	public boolean modify(T data) throws Exception;
	
	public boolean delete(T data) throws Exception;
}
