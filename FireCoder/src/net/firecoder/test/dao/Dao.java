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
	
	/**
	 * 新增数据
	 * @param data
	 * @return 新增数据的ID
	 * @throws Exception
	 */
	public String add(T data) throws Exception;
	
	/**
	 * 修改数据，对象中的编号将被作为修改条件
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(T data) throws Exception;
	
	/**
	 * 删除数据，对象中的编号将被作为删除条件
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean delete(T data) throws Exception;
	
	/**
	 * 获取数据，对象中的编号作为获取条件
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public T get(T data) throws Exception;
}
