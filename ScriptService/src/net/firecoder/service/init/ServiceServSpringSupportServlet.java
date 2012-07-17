package net.firecoder.service.init;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.firecoder.service.GroovyServiceServerEngine;
import net.firecoder.service.ServiceServer;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ServiceServSpringSupportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext springContext = null;
	private ServiceServer server = null;
	private Map<String, Object> springBeans = null;
       
    public ServiceServSpringSupportServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	springContext = 
    		WebApplicationContextUtils.getWebApplicationContext(
    				config.getServletContext());
    	server = GroovyServiceServerEngine.getInstance();
    	springBeans = SpringBeanUtils.getSpringBeans(springContext);
    	try {
			server.start(springBeans);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (action != null) {
			String msg = "扩展服务引擎";
			String actionName = "";
			String success = "成功！";
			try {
				if ("start".equals(action)) {
					server.start(springBeans);
					actionName = "启动";
				} else if ("restart".equals(action)) {
					server.restart(springBeans);
					actionName = "重启";
				} else if ("stop".equals(action)) {
					server.stop();
					actionName = "停止";
				}
				msg += actionName + success;
			} catch (Exception e) {
				success = "失败！";
				msg += actionName + success + e.getMessage();
			}
			out.println(msg);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		server = null;
	}
}
