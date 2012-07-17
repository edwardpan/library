/* PermissionLimitJsonAction.java
 * project: EnvirLims
 */
package net.firecoder.actions;

/**
 * @author 潘超
 * create: 2011-9-28
 */
public class PermissionLimitJsonAction extends PermissionAction {
	private boolean permissionLimit = false;
	private String permissionError = "";
	
	public String execute() {
		permissionLimit = true;
		if (loginContext == null || loginContext.getUser() == null) {
			permissionError = "会话失效，请重新登录系统！";
		} else {
			String realName = loginContext.getUser().getRealName();
			permissionError = "权限受限，用户" + realName + "没有权限访问该资源，请检查权限分配！";
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
