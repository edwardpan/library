/* UserDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao;

import net.firecoder.test.pojo.UserPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
public interface UserDao extends Dao<UserPojo> {
	public UserPojo find(String loginName) throws Exception;
	
}
