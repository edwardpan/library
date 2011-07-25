/* UserDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.util.List;

import net.firecoder.test.dao.Pagination;
import net.firecoder.test.dao.UserDao;
import net.firecoder.test.pojo.FormulaElementPojo;
import net.firecoder.test.pojo.UserPojo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author �˳�
 * create: 2011-7-16
 */
public class UserDaoImpl extends HibernateDao<UserPojo> implements UserDao {
	
	@Override
	public UserPojo find(String loginName) throws Exception {
		UserPojo pojo = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserPojo u where u.loginName=?");
		query.setFirstResult(0);// ��ҳ����ʼ��¼��������0��ʼ
		query.setMaxResults(1);// ��ҳ��������¼��
		query.setString(0, loginName);// ����HQL�е�����
		List list = query.list();
		if (list != null && list.size() > 0) {
			pojo = (UserPojo) list.get(0);
		}
		return pojo;
	}

	@Override
	public Pagination<UserPojo> findAll(UserPojo term, int startIndex, int pageSize) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(UserPojo.class);
		c = createCriteria(c, term);
		return findPageByCriteria(c, startIndex, pageSize);
	}
}
