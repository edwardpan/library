/* FormulaDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * @author �˳�
 * create: 2011-7-19
 */
public class FormulaDaoImpl extends HibernateDao<FormulaPojo> {

	@Override
	public Pagination<FormulaPojo> findAll(int startIndex, int pageSize)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(FormulaPojo.class);
		//c.add(Restrictions.eq("id", 4));
		return findPageByCriteria(c, startIndex, pageSize);
	}
	
}
