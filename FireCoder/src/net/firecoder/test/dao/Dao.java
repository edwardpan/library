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
	public Pagination<T> findAll(T term, int startIndex, int pageSize) throws Exception;
	
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
	 * 修改数据，按条件修改
	 * @param data
	 * @param term 修改数据的条件
	 * @return
	 * @throws Exception
	 */
	public boolean updateByTerm(T data, T term) throws Exception;
	
	/**
	 * 删除数据，对象中的编号将被作为删除条件
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean delete(T data) throws Exception;
	
	/**
	 * 删除数据，按条件删除
	 * @param term 删除数据的条件
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByTerm(T term) throws Exception;
	
	/**
	 * 获取数据，按条件获取
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public T get(T term) throws Exception;
	
}
