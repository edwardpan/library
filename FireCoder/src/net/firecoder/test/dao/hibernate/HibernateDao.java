/* HibernateDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;

import net.firecoder.test.dao.Dao;
import net.firecoder.test.dao.Pagination;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

/**
 * @author �˳�
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
	public boolean update(T data) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(data);
		return false;
	}
	
	@Override
	public T get(T data) throws Exception {
		Class cl = data.getClass();
		Field[] fields = cl.getDeclaredFields();
		Field idField = null;
		for (Field f : fields) {
			Id idAnnoCl = f.getAnnotation(Id.class);
			if (idAnnoCl != null) {
				idField = f;
				break;
			}
		}
		if (idField == null) {
			return null;
		}
		
		T d = null;
		String colName = idField.getName();
		idField.setAccessible(true);
		Object id = idField.get(data);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + cl.getSimpleName() + " where " + colName + "=?");
		query.setParameter(0, id);
		List<T> list = query.list();
		if (list != null && list.size() > 0) {
			d = list.get(0);
		}
		return d;
	}
	
	/**
	 * ��ҳ��ѯ��������
	 * @param c ���ڻ�ȡ���������Ķ���ʾ����session.createCriteria(T.class);
	 * @param startIndex ��ҳ��ʼ����
	 * @param pageSize ��ҳС��
	 * @return ��ѯ����һҳ���ݣ�������ط�ҳ��Ϣ
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
}
