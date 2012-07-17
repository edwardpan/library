/* ServiceCallAction.java
 * project: EnvirLims
 */
package net.firecoder.service.web.action;

import net.firecoder.service.Service;
import net.firecoder.service.ServicePoolFactory;
import net.firecoder.service.web.ServiceResultParser;

/**
 * @author 潘超
 * create: 2011-9-7
 */
public class ServiceCallAction extends BaseAction {
	private String service;
	private String returnType;
	private String data;
	private boolean success = false;
	private String message = "";
	
	public String execute() {
		Service s = ServicePoolFactory.getServicePool().getService(service);
		if (s != null) {// 服务存在
			message = "服务调用";
			try {
				s.putVariable("httpRequest", httpRequest);
				s.putVariable("httpResponse", httpResponse);
				Object result = s.call();
				if ("json".equals(returnType)) {// 解析为JSON
					data = ServiceResultParser.parserToJson(result);
					success = true;
					message += "成功！";
					return "json";
				} else if ("text".equals(returnType)) {// 不作解析， 由服务自己控制输出显示内容
					success = true;
					message += "成功！";
					return "text";
				} else if ("html".equals(returnType)) {
					// TODO HTML中的body内容，自动为其加入<head>,<script>,<html>,<body>等标签
				}
			} catch (Exception e) {
				success = false;
				message += "出错！" + e.getMessage() + e.getMessage();
			}
		} else {
			success = false;
			message = "服务不存在或扩展服务引擎未启动！";
		}
		
		return "json";
	}

	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
}
