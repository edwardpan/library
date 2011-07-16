/* UserDaoImpl.java
 * project: FireCoder
 */
package net.firecoder.test.dao.hibernate;

import java.util.List;

import net.firecoder.test.dao.UserDao;
import net.firecoder.test.pojo.UserPojo;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author 潘超
 * create: 2011-7-16
 */
public class UserDaoImpl extends HibernateDao implements UserDao {
	public UserPojo find(String loginName) {
		UserPojo pojo = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserPojo u where u.loginName=?");
		query.setFirstResult(0);// 分页，开始记录索引，从0开始
		query.setMaxResults(1);// 分页，结束记录号
		query.setString(0, loginName);// 设置HQL中的条件
		List list = query.list();
		if (list != null && list.size() > 0) {
			pojo = (UserPojo) list.get(0);
		}
		return pojo;
	}
}
