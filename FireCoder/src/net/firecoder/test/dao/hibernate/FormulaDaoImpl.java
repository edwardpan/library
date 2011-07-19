/* FormulaDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.util.List;

import net.firecoder.test.dao.FormulaDao;
import net.firecoder.test.pojo.FormulaPojo;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
public class FormulaDaoImpl extends HibernateDao implements FormulaDao {

	@Override
	public void addFormula(FormulaPojo pojo) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(pojo);
	}

	@Override
	public List<FormulaPojo> listFormula() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FormulaPojo");
		return query.list();
	}

}
