/* ServiceCallAction.java
 * project: EnvirLims
 */
package net.firecoder.service.web.action;

import net.firecoder.service.Service;
import net.firecoder.service.ServicePoolFactory;
import net.firecoder.service.web.ServiceResultParser;

/**
 * @author �˳�
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
		if (s != null) {// �������
			message = "�������";
			try {
				s.putVariable("httpRequest", httpRequest);
				s.putVariable("httpResponse", httpResponse);
				Object result = s.call();
				if ("json".equals(returnType)) {// ����ΪJSON
					data = ServiceResultParser.parserToJson(result);
					success = true;
					message += "�ɹ���";
					return "json";
				} else if ("text".equals(returnType)) {// ���������� �ɷ����Լ����������ʾ����
					success = true;
					message += "�ɹ���";
					return "text";
				} else if ("html".equals(returnType)) {
					// TODO HTML�е�body���ݣ��Զ�Ϊ�����<head>,<script>,<html>,<body>�ȱ�ǩ
				}
			} catch (Exception e) {
				success = false;
				message += "����" + e.getMessage() + e.getMessage();
			}
		} else {
			success = false;
			message = "���񲻴��ڻ���չ��������δ������";
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
