/* Dao.java
 * project: FireCoder
 */
package net.firecoder.test.dao;


/**
 * @author 潘超
 * create: 2011-7-20
 */
public interface Dao<T> {
	/**
	 * 按分页设置查询所有数据
	 * @param page 分页设置
	 * @return
	 * @throws Exception
	 */
	public Pagination<T> findAll(int startIndex, int pageSize) throws Exception;
	
	public boolean add(T data) throws Exception;
	
	public boolean modify(T data) throws Exception;
	
	public boolean delete(T data) throws Exception;
}
