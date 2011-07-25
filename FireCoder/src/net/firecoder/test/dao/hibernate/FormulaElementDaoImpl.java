/* FormulaElementDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaElementPojo;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * @author ≈À≥¨
 * create: 2011-7-20
 */
public class FormulaElementDaoImpl extends HibernateDao<FormulaElementPojo> {

	@Override
	public Pagination<FormulaElementPojo> findAll(FormulaElementPojo term, int startIndex, int pageSize)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(FormulaElementPojo.class);
		c = createCriteria(c, term);
		return findPageByCriteria(c, startIndex, pageSize);
	}

}
