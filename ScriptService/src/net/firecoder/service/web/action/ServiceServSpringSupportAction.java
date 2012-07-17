/* ServiceServerAction.java
 * project: EnvirLims
 */
package net.firecoder.service.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import net.firecoder.service.GroovyServiceServerEngine;
import net.firecoder.service.ServiceServer;
import net.firecoder.service.web.SpringBeanUtils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author �˳�
 * create: 2011-9-7
 */
public class ServiceServSpringSupportAction extends BaseAction {

	private ServiceServer server = GroovyServiceServerEngine.getInstance();
	
	public String start() {
		PrintWriter out = null;
		try {
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html);charset=utf-8");
			out = httpResponse.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
					httpRequest.getServletContext());
			server.start(SpringBeanUtils.getSpringBeans(context));
			if (out != null) {
				out.println("��չ�������������ɹ���");
			}
		} catch (Exception e) {
			if (out != null) {
				out.println("��չ����������������" + e.getMessage());
			}
		}
		return SUCCESS;
	}

	public String restart() {
		PrintWriter out = null;
		try {
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html);charset=utf-8");
			out = httpResponse.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
					httpRequest.getServletContext());
			server.restart(SpringBeanUtils.getSpringBeans(context));
			if (out != null) {
				out.println("��չ�������������ɹ���");
			}
		} catch (Exception e) {
			if (out != null) {
				out.println("��չ����������������" + e.getMessage());
			}
		}
		return SUCCESS;
	}

	public String stop() {
		PrintWriter out = null;
		try {
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html);charset=utf-8");
			out = httpResponse.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			server.stop();
			if (out != null) {
				out.println("��չ��������ֹͣ�ɹ���");
			}
		} catch (Exception e) {
			if (out != null) {
				out.println("��չ��������ֹͣ����" + e.getMessage());
			}
		}
		return SUCCESS;
	}
}
