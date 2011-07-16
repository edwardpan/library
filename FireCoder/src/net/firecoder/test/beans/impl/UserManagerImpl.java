/* UserManagerImpl.java
 * project: FireCoder
 */
package net.firecoder.test.beans.impl;

import net.firecoder.test.beans.UserManager;
import net.firecoder.test.dao.UserDao;
import net.firecoder.test.pojo.UserPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserPojo getUser(String loginName) {
		return userDao.find(loginName);
	}

}
