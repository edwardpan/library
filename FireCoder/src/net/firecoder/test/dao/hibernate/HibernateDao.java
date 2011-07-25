/* HibernateDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import net.firecoder.test.dao.Dao;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.dao.Term;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @author 潘超
 * create: 2011-7-16
 */
public abstract class HibernateDao<T> implements Dao<T> {
	
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public String add(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return String.valueOf(session.save(data));
	}
	
	@Override
	public boolean delete(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(data);
		return true;
	}
	
	@Override
	public boolean deleteByTerm(T term) throws Exception{
		Query query = createUpdateOrDelete("delete", term);
		int num = query.executeUpdate();
		if (num > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean update(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(data);
		return true;
	}
	
	@Override
	public boolean updateByTerm(T data, T term) throws Exception{
		Query query = createUpdateOrDelete("update", term);
		int num = query.executeUpdate();
		if (num > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public T get(T term) throws Exception{
		T data = null;
		Pagination<T> page = findAll(term, 0, 1);
		List<T> list = page.getItems();
		if (list != null && list.size() > 0) {
			data = list.get(0);
		}
		return data;
	}
	
	/**
	 * 分页查询所有数据
	 * @param c 用于获取数据总数的对象，示例：session.createCriteria(T.class);
	 * @param startIndex 分页开始索引
	 * @param pageSize 分页小大
	 * @return 查询到的一页数据，包括相关分页信息
	 * @throws Exception
	 */
	protected Pagination<T> findPageByCriteria(Criteria c, int startIndex, int pageSize)
			throws Exception {
		long totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		c.setProjection(null);
		List<T> items = c.setFirstResult(startIndex)
				.setMaxResults(pageSize).list();
		Pagination<T> page = new Pagination<T>(items, totalCount, pageSize, startIndex);
		return page;
	}
	
	/**
	 * 根据实体类中的条件创建用于Update和Delete的Query对象
	 * @param action 执行的动作
	 * @param termC 保存条件的实体类对象
	 * @return 可立即被用于更新/删除执行的Query对象
	 */
	protected Query createUpdateOrDelete(String action, T termC) throws Exception {
		if (action == null || action.equals("") || termC == null) {
			return null;
		}

		StringBuffer hql = new StringBuffer();
		List paramList = new ArrayList();
		
		Class cl = termC.getClass();
		// 添加动作和查询实体
		hql.append(action);
		hql.append(" from ");
		hql.append(cl.getSimpleName());
		hql.append(" where 1=1 ");
		// 添加条件
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			Term termAnno = f.getAnnotation(Term.class);
			if (termAnno != null) {// 对定义了Term注释的属性创建HQL条件
				String colName = f.getName();
				if (termAnno.field() != null && !termAnno.field().equals("")) {// 是否定义注释的field属性
					colName = termAnno.field();
				}
				f.setAccessible(true);
				Object value = f.get(termC);// 取得条件值
				if (value == null || value.equals(0)) {// 没有设置条件值，不作为条件
					continue;
				}
				
				hql.append(" and ");
				hql.append(colName);
				hql.append(" =? ");

				paramList.add(value);
			}
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		for (int i = 0; i < paramList.size(); i++) {
			Object param  = paramList.get(i);
			query.setParameter(i, param);
		}
		return query;
	}
	
	/**
	 * 根据实体类中的条件创建用于查询的Criteria对象
	 * @param c Criteria对象，使用Session创建
	 * @param termC 保存条件的实体
	 * @return 已被设置条件的Criteria
	 * @throws Exception
	 */
	protected Criteria createCriteria(Criteria c, T termC) throws Exception{
		if (c == null || termC == null) {
			return c;
		}
		
		Class cl = termC.getClass();
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			Term termAnno = f.getAnnotation(Term.class);
			if (termAnno != null) {// 对定义了Term注释的属性创建HQL条件
				String colName = f.getName();
				if (termAnno.field() != null && !termAnno.field().equals("")) {// 是否定义注释的field属性
					colName = termAnno.field();
				}
				f.setAccessible(true);
				Object value = f.get(termC);// 取得条件值
				if (value == null || value.equals(0)) {// 没有设置条件值，不作为条件
					continue;
				}

				c.add(Restrictions.eq(colName, value));
			}
		}
		
		return c;
	}
}
