/* LoginAuthInterceptor.java
 * project: EnvirLims
 */
package net.firecoder.actions;

import java.util.Collection;
import java.util.Map;

import net.firecoder.actions.identity.AuthorityManager;
import net.firecoder.actions.identity.LoginContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author 潘超
 * @created 2011-8-2
 */
public class PermissionInterceptor implements Interceptor {

	private final static Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);
	private AuthorityManager authorityManager;
	private final String jsonPermissionLimit = "jsonPermissionLimit";
	
	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO 通过Cookie或会话验证用户是否登录
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		LoginContext loginContext = (LoginContext) session.get(HttpContextName.SESSION_USER);
		
		// 判断是否有返回JSON的结果
		boolean isJson = false;
		Collection<ResultConfig> reConfigList = 
			invocation.getProxy().getConfig().getResults().values();
		for (ResultConfig reConfig : reConfigList) {
			String className = reConfig.getClassName();
			if (className.indexOf("json") > -1 || className.indexOf("JSON") > -1) {
				isJson = true;
				break;
			}
		}

		// 判断Session是否失效
		if (loginContext == null || loginContext.getUser() == null) {
			if (!isJson) {// 结果不是返回JSON时才处理
				return "login";
			} else {
				return jsonPermissionLimit;
			}
		}
		
		// 将登录的用户信息传入到受限访问的资源中，供其使用
		Object o = invocation.getAction();
		if (o instanceof Permissible) {
			Permissible per = (Permissible) o;
			per.setLoginContext(loginContext);
		}
		
		String menuUrl = invocation.getProxy().getConfig().getParams().get("module");
		if (menuUrl != null && !menuUrl.equals("")) {
			// 验证用户对该资源是否有权限访问
			if (!authorityManager.validatePermission(menuUrl, loginContext)) {
				log.warn("没有权限访问该资源，检查该资源是否被分配或资源是否被配置为菜单！");
				if (!isJson) {// 结果不是返回JSON时才处理
					return "securityerror";
				} else {
					return jsonPermissionLimit;
				}
			}
		}

		return invocation.invoke();
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}
}
