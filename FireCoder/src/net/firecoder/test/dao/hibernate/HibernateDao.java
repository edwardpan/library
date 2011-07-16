/* HibernateDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import org.hibernate.SessionFactory;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
public abstract class HibernateDao {
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
