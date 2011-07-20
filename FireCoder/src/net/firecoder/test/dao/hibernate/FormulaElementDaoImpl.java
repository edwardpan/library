/* FormulaElementDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaElementPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-20
 */
public class FormulaElementDaoImpl extends HibernateDao<FormulaElementPojo> {

	@Override
	public Pagination<FormulaElementPojo> findAll(int startIndex, int pageSize)
			throws Exception {
		return null;
	}

}
