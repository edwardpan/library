/*
 * AuthorityManager.java
 */
package net.firecoder.actions.identity;

/**
 * @author �˳�
 * @date Jul 18, 2012
 */
public interface AuthorityManager {
	public boolean validatePermission(Object module, LoginContext loginContext);
}
