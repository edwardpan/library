/* Permissible.java
 * project: EnvirLims
 */
package net.firecoder.actions;

import net.firecoder.actions.identity.LoginContext;


/**
 * Ҫ���ṩ��ݲ��ܷ��ʵĽӿڣ�Ӧ����Action��
 * ΪҪ���ṩ��ݵ�Action���������Ϣ
 * @author �˳�
 * @created 2011-8-2
 */
public interface Permissible {
	public void setLoginContext(LoginContext context);
	public LoginContext getLoginContext();
}
