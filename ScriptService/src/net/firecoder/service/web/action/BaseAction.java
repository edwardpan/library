/* BaseAction.java
 * project: EnvirLims
 */
package net.firecoder.service.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ≈À≥¨
 * @created 2011-8-15
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ApplicationAware {
	
	protected final Logger log = LoggerFactory.getLogger(BaseAction.class);
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	
	@Override
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
