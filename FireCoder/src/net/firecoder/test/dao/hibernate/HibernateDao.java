/* HibernateDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.util.List;

import net.firecoder.test.dao.Dao;
import net.firecoder.test.dao.Pagination;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
public abstract class HibernateDao<T> implements Dao<T> {
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean add(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(data);
		return true;
	}
	
	@Override
	public boolean delete(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(data);
		return false;
	}
	
	@Override
	public boolean modify(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + data.getClass().getSimpleName());
		query.setEntity(0, data);
		List<T> list = query.list();
		if (list != null && list.size() > 0) {
			T d = list.get(0);
			session.delete(d);
		}
		return false;
	}
	
}
