/* UserManager.java
 * project: FireCoder
 */
package net.firecoder.test.beans.user;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.pojo.UserPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
public interface UserManager {
	public UserPojo getUser(String loginName) throws BeanException;
	
	public boolean addUser(UserPojo pojo) throws BeanException;
}
