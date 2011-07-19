/* UserAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.power;

import net.firecoder.test.actions.expression.ExpressionAction;
import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.user.UserManager;
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
public class UserAction extends ActionSupport{
	private final Logger log = Logger.getLogger(ExpressionAction.class);
	
	private String loginName;
	private String realName;
	private String password;
	private String jsonChannel;
	private String message;
	
	private UserManager userManager;
	
	@Action(value="login", 
			results= {
				@Result(name="success", type="json"),
				@Result(name="error", type="json")}
		)
	public String executeLogin() {
		UserPojo pojo = null;
		
		try {
			pojo = userManager.getUser(loginName);
			if (pojo == null) {
				message = getText("login.error", new String[] {loginName});
				return ERROR;
			}
			jsonChannel = JSONUtil.serialize(pojo);
		} catch (BeanException ex) {
			message = ex.getMessage();
			return ERROR;
		} catch (JSONException ex) {
			message = ex.getMessage();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	@Action(value="register",
		results= {
			@Result(name="success", type="json"),
			@Result(name="error", type="json")
		}
	)
	public String registerUser() {
		UserPojo user = new UserPojo();
		user.setLoginName(loginName);
		user.setRealName(realName);
		user.setPassword(password);
		
		try {
			userManager.addUser(user);
		} catch (BeanException ex) {
			message = ex.getMessage();
			return ERROR;
		}
		return SUCCESS;
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

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
