package net.firecoder.test.actions.manager;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.firecoder.test.beans.StringTest;
import net.firecoder.test.pojo.MyObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
public class TestAction {

	private InputStream inputStream;
	private StringTest strTest;

	@Action(
		value="test1",
		results={
			@Result(name="success", type="stream", params={"contentType", "text/html", "inputName", "inputStream"})}
	)
	public String execute() {
		inputStream = new StringBufferInputStream(strTest.getMessage());
		return ActionSupport.SUCCESS;
	}
	
	private String jsonParam;
	private String strAjaxChannel;
	private Map<String, String> result; 
	private List<MyObject> list;
	
	@Action(
		value="test2",
		results={
			@Result(type="json")}
	)
	public String execute2() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < 2; i++) {
			sb.append("id" + i);
			sb.append(":");
			sb.append("\"");
			sb.append(new ActionSupport().getText("testname", new String[] {String.valueOf(i)}));
			sb.append("\"");
			if (i != (2 - 1)) {
				sb.append(",");
			}
		}
		sb.append("}");

		strAjaxChannel = sb.toString();//返回的数据
		
		result = new HashMap<String, String>();
		result.put("key0", "value0");
		result.put("key1", "value1");
		result.put("key2", "value2");
		result.put("key3", "value3");
		
		list = new ArrayList<MyObject>();
		MyObject obj = null;
		for (int i = 0; i < 3; i++) {
			obj = new MyObject();
			obj.setId(String.valueOf(i));
			obj.setName( new ActionSupport().getText("testname", new String[] {String.valueOf(i)}) );
			obj.setAge("25");
			obj.setBirthday("1986-06-01_" + i);
			list.add(obj);
		}
		
		return ActionSupport.SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setStrTest(StringTest strTest) {
		this.strTest = strTest;
	}

	public String getStrAjaxChannel() {
		return strAjaxChannel;
	}

	public void setStrAjaxChannel(String strAjaxChannel) {
		this.strAjaxChannel = strAjaxChannel;
	}

	public String getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}

	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

	public List<MyObject> getList() {
		return list;
	}

	public void setList(List<MyObject> list) {
		this.list = list;
	}
	
}
