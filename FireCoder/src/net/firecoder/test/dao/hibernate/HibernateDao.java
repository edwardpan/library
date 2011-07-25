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
 * @author �˳�
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
	
	/**
	 * ����ʵ�����е�������������Update��Delete��Query����
	 * @param action ִ�еĶ���
	 * @param termC ����������ʵ�������
	 * @return �����������ڸ���/ɾ��ִ�е�Query����
	 */
	protected Query createUpdateOrDelete(String action, T termC) throws Exception {
		if (action == null || action.equals("") || termC == null) {
			return null;
		}

		StringBuffer hql = new StringBuffer();
		List paramList = new ArrayList();
		
		Class cl = termC.getClass();
		// ��Ӷ����Ͳ�ѯʵ��
		hql.append(action);
		hql.append(" from ");
		hql.append(cl.getSimpleName());
		hql.append(" where 1=1 ");
		// �������
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			Term termAnno = f.getAnnotation(Term.class);
			if (termAnno != null) {// �Զ�����Termע�͵����Դ���HQL����
				String colName = f.getName();
				if (termAnno.field() != null && !termAnno.field().equals("")) {// �Ƿ���ע�͵�field����
					colName = termAnno.field();
				}
				f.setAccessible(true);
				Object value = f.get(termC);// ȡ������ֵ
				if (value == null || value.equals(0)) {// û����������ֵ������Ϊ����
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
	 * ����ʵ�����е������������ڲ�ѯ��Criteria����
	 * @param c Criteria����ʹ��Session����
	 * @param termC ����������ʵ��
	 * @return �ѱ�����������Criteria
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
			if (termAnno != null) {// �Զ�����Termע�͵����Դ���HQL����
				String colName = f.getName();
				if (termAnno.field() != null && !termAnno.field().equals("")) {// �Ƿ���ע�͵�field����
					colName = termAnno.field();
				}
				f.setAccessible(true);
				Object value = f.get(termC);// ȡ������ֵ
				if (value == null || value.equals(0)) {// û����������ֵ������Ϊ����
					continue;
				}

				c.add(Restrictions.eq(colName, value));
			}
		}
		
		return c;
	}
}
