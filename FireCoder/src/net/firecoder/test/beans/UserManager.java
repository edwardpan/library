/* UserManager.java
 * project: FireCoder
 */
package net.firecoder.test.beans;

import net.firecoder.test.pojo.UserPojo;

/**
 * @author �˳�
 * create: 2011-7-16
 */
public interface UserManager {
	public UserPojo getUser(String loginName);
}
