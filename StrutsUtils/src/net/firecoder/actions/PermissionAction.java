/* PermissionAction.java
 * project: EnvirLims
 */
package net.firecoder.actions;

import java.util.Stack;

import net.firecoder.actions.identity.LoginContext;

/**
 * @author ≈À≥¨
 * @created 2011-8-2
 */
public abstract class PermissionAction extends BaseAction implements Permissible {
	
	protected LoginContext loginContext;
	protected int startIndex = 0;
	protected int pageSize = 10;
	
	@Override
	public void setLoginContext(LoginContext context) {
		this.loginContext = context;
	}
	@Override
	public LoginContext getLoginContext() {
		return this.loginContext;
	}
	
	public void pushMessage(Message msg) {
		Stack<Message> messageStack = (Stack<Message>) session.get("messageStack");
		if (messageStack == null) {
			messageStack = new Stack<Message>();
			session.put("messageStack", messageStack);
		}
		messageStack.push(msg);
	}
	
	public Message popMessage() {
		Stack<Message> messageStack = (Stack<Message>) session.get("messageStack");
		if (messageStack == null || messageStack.isEmpty()) {
			return null;
		}
		return messageStack.pop();
	}
	
	public int getMessageCount() {
		Stack<Message> messageStack = (Stack<Message>) session.get("messageStack");
		if (messageStack == null || messageStack.isEmpty()) {
			return 0;
		}
		return messageStack.size();
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setStartIndex(String startIndex) {
		if (startIndex == null || startIndex.equals("")) {
			this.startIndex = 0;
		} else {
			this.startIndex = Integer.parseInt(startIndex);
		}
	}
	public void setPageSize(String pageSize) {
		if (pageSize == null || pageSize.equals("")) {
			this.pageSize = 20;
		} else {
			this.pageSize = Integer.parseInt(pageSize);
		}
	}
	public int getStartIndex() {
		return startIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	
}
