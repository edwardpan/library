/* UserManagerImpl.java
 * project: FireCoder
 */
package net.firecoder.test.beans.user.impl;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.user.UserManager;
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
	public UserPojo getUser(String loginName) throws BeanException {
		UserPojo user = null;
		try{
			user = userDao.find(loginName);
		} catch (Exception ex) {
			throw new BeanException(ex);
		}
		return user;
	}

	@Override
	public boolean addUser(UserPojo pojo) throws BeanException {
		boolean success = false;
		try {
			success = userDao.add(pojo);
		} catch (Exception ex) {
			throw new BeanException(ex);
		}
		return success;
	}

}
