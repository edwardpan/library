/* PermissionLimitJsonAction.java
 * project: EnvirLims
 */
package net.firecoder.actions;

/**
 * @author �˳�
 * create: 2011-9-28
 */
public class PermissionLimitJsonAction extends PermissionAction {
	private boolean permissionLimit = false;
	private String permissionError = "";
	
	public String execute() {
		permissionLimit = true;
		if (loginContext == null || loginContext.getUser() == null) {
			permissionError = "�ỰʧЧ�������µ�¼ϵͳ��";
		} else {
			String realName = loginContext.getUser().getRealName();
			permissionError = "Ȩ�����ޣ��û�" + realName + "û��Ȩ�޷��ʸ���Դ������Ȩ�޷��䣡";
		}
		return SUCCESS;
	}

	public boolean isPermissionLimit() {
		return permissionLimit;
	}

	public void setPermissionLimit(boolean permissionLimit) {
		this.permissionLimit = permissionLimit;
	}

	public String getPermissionError() {
		return permissionError;
	}

	public void setPermissionError(String permissionError) {
		this.permissionError = permissionError;
	}
	
}
