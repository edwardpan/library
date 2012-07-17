/* Permissible.java
 * project: EnvirLims
 */
package net.firecoder.actions;

import net.firecoder.actions.identity.LoginContext;


/**
 * 要求提供身份才能访问的接口，应用于Action，
 * 为要求提供身份的Action传入身份信息
 * @author 潘超
 * @created 2011-8-2
 */
public interface Permissible {
	public void setLoginContext(LoginContext context);
	public LoginContext getLoginContext();
}
