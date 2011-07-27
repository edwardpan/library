package net.firecoder.test.actions.expression;

import java.util.List;

import net.firecoder.expressionhelper.ExpressionHelper;
import net.firecoder.expressionhelper.Variable;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
public class ExpressionAction {

	private final Logger log = Logger.getLogger(ExpressionAction.class);
	
	private String exp;
	private String expVars;
	private List<Variable> expVarList;
	private String expResult;
	
	/**
	 * <p>执行表达式的Action方法，返回执行结果到前端</p>
	 * @return
	 */
	@Action(value="executeExp", 
		results= {
			@Result(type="json")}
	)
	public String executeExpression() {
		log.debug("表达式变量：" + expVars);
		
		JSONArray json = JSONArray.fromObject(expVars);
		expVarList = JSONArray.toList(json, Variable.class);
		
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		helper.setVariables(expVarList);
		expResult = helper.executeFullStr();
		return ActionSupport.SUCCESS;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getExpResult() {
		return expResult;
	}
	
	public void setExpVars(String expVars) {
		this.expVars = expVars;
	}
}
