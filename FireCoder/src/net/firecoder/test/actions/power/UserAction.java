/* UserAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.power;

import java.util.ArrayList;
import java.util.List;

import net.firecoder.test.actions.expression.ExpressionAction;
import net.firecoder.test.beans.UserManager;
import net.firecoder.test.pojo.UserPojo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	private final Logger log = Logger.getLogger(ExpressionAction.class);
	
	private String loginName;
	private UserManager userManager;
	private String jsonChannel;
	private String message;
	
	@Action(value="login", 
			results= {
				@Result(name="success", type="json"),
				@Result(name="error", type="json")}
		)
	public String executeLogin() {
		UserPojo pojo = userManager.getUser(loginName);
		if (pojo == null) {
			message = getText("login.error", new String[] {loginName});
			return ERROR;
		}
		
		try {
			jsonChannel = JSONUtil.serialize(pojo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getJsonChannel() {
		return this.jsonChannel;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String getMessage() {
		return this.message;
	}
	
}
