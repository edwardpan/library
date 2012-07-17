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
 * @author �˳�
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
		// TODO ͨ��Cookie��Ự��֤�û��Ƿ��¼
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		LoginContext loginContext = (LoginContext) session.get(HttpContextName.SESSION_USER);
		
		// �ж��Ƿ��з���JSON�Ľ��
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

		// �ж�Session�Ƿ�ʧЧ
		if (loginContext == null || loginContext.getUser() == null) {
			if (!isJson) {// ������Ƿ���JSONʱ�Ŵ���
				return "login";
			} else {
				return jsonPermissionLimit;
			}
		}
		
		// ����¼���û���Ϣ���뵽���޷��ʵ���Դ�У�����ʹ��
		Object o = invocation.getAction();
		if (o instanceof Permissible) {
			Permissible per = (Permissible) o;
			per.setLoginContext(loginContext);
		}
		
		String menuUrl = invocation.getProxy().getConfig().getParams().get("module");
		if (menuUrl != null && !menuUrl.equals("")) {
			// ��֤�û��Ը���Դ�Ƿ���Ȩ�޷���
			if (!authorityManager.validatePermission(menuUrl, loginContext)) {
				log.warn("û��Ȩ�޷��ʸ���Դ��������Դ�Ƿ񱻷������Դ�Ƿ�����Ϊ�˵���");
				if (!isJson) {// ������Ƿ���JSONʱ�Ŵ���
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
