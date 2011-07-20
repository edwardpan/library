/* FormulaDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.util.List;

import net.firecoder.test.dao.FormulaDao;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
public class FormulaDaoImpl extends HibernateDao<FormulaPojo> implements FormulaDao<FormulaPojo> {

	@Override
	public void addFormula(FormulaPojo pojo) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(pojo);
	}

	@Override
	public List<FormulaPojo> listFormula() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FormulaPojo");
		List<FormulaPojo> list = query.list();
		return list;
	}
	
	@Override
	public Pagination<FormulaPojo> findAll(int startIndex, int pageSize)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(FormulaPojo.class);
		long totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		c.setProjection(null);
		List<FormulaPojo> items = c.setFirstResult(startIndex)
				.setMaxResults(pageSize).list();
		Pagination<FormulaPojo> page = new Pagination<FormulaPojo>(items, totalCount, pageSize, startIndex);
		return page;
	}

}
