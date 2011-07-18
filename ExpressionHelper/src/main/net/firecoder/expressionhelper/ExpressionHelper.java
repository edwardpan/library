package net.firecoder.expressionhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

/**
 * <p>IK表达式解析器帮助类。提供使用表达式解析器的统一入口。
 * 支持在任意字符串中使用“[EXP][/EXP]”来标识表达式，且可标识多个表达式。<br/>
 * 如：“今天的日期为：[EXP]$dateToString($SYSDATE(), "yyyyMMdd")[/EXP]，315的日期为：[EXP]$dateToString([2011-03-15], "yyyyMMdd")[/EXP]”。<br/>
 * 运算结果为：“今天的日期为：20110329，315的日期为：20110315”</p>
 * @author 潘超
 * @date 2011-3-29 上午10:08:53
 */
public class ExpressionHelper {
	private Logger logger = LoggerFactory.getLogger(ExpressionHelper.class);
	
	private final String beginIden = "[EXP]";// 表达式开始标识
	private final String endIden = "[/EXP]";// 表达式结束标识
	private final String rstBeginIden = "[EXPR]";// 表达式临时占位符开始标识
	private final String rstEndIden = "[/EXPR]";// 表达式临时占位符结束标识

	private String fullExpStr = "";
	private List<net.firecoder.expressionhelper.Variable> variables = null;

	public String getFullExpStr() {
		return fullExpStr;
	}

	public void setFullExpStr(String fullExpStr) {
		this.fullExpStr = fullExpStr;
	}

	public List<net.firecoder.expressionhelper.Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<net.firecoder.expressionhelper.Variable> variables) {
		this.variables = variables;
	}

	/**
	 * <p>获取表达式帮助工具实例，如果没有则创建</p>
	 * @return
	 */
	public static ExpressionHelper getInstance() {
		// 产生一个表达式帮助类的实例，因使用时会设置变量或参数的值，所以不适合使用单例模式
		return new ExpressionHelper();
	}

	/**
	 * <p>执行含表达式的字符串（被“{”和"}"包括起来的表达式字符串）。
	 * 表达式解析器使用IKExpression引擎，并遵循其规范。</p>
	 * @return
	 */
	public String executeFullStr() {
		// 抽离出字符串中的表达式
		Map<String, Object> parsedResult = parseExpressionStr(fullExpStr);
		String str = (String) parsedResult.get("str");
		List<String> exps = (List<String>) parsedResult.get("exps");// 得到表达式
		logger.debug("抽离出字符串中的表达式：");
		logger.debug("字符串：" + str);
		logger.debug("表达式：" + exps.toArray());
		
		logger.debug("获取字符串中被“" + beginIden + endIden + "”包含着的表达式");
		// 运算结果
		List<Object> resultList = executeExpression(exps, variables);
		logger.debug("运算出每一个表达式的结果");

		// 组合非表达式字符串与表达式字符串的运算结果
		for (int i = 0; i < resultList.size(); i++) {
			Object value = resultList.get(i);
			str = str.replace(rstBeginIden + i + rstEndIden, (String) value.toString());
		}
		logger.debug("将多个非表达式字符串和表达式结果组合成完整的结果字符串");

		return str.toString();
	}

	/**
	 * <p>执行表达式</p>
	 * @param expList 表达式的集合，表达式中不再含有成对的{}符号
	 * @return
	 */
	private List<Object> executeExpression(List<String> expList, List<net.firecoder.expressionhelper.Variable> myVariables){
		List<Variable> variables = createVariables(myVariables);

		// 执行表达式
		List<Object> resultList = new ArrayList<Object>();
		for (String exp : expList) {
			logger.debug("执行表达式：" + exp + "，并为变量赋值，变量数：" + variables.size());
			Object result = ExpressionEvaluator.evaluate(exp, variables);
			logger.debug("表达式" + exp + "的执行结果：" + result);
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * <p>创建变量</p>
	 * @param myVariables
	 * @return
	 */
	private List<Variable> createVariables(List<net.firecoder.expressionhelper.Variable> myVariables){
		List<Variable> variables = new ArrayList<Variable>();
		if (myVariables != null) {
			for (net.firecoder.expressionhelper.Variable myVariable : myVariables) {
				logger.debug("创建变量：" + myVariable.getName() + " - " +  myVariable.getValue());
				variables.add(Variable.createVariable(myVariable.getName(), myVariable.getValue()));
			}
		}
		return variables;
	}

	/**
	 * <p>从含表达式的字符串中查询被“[EXP]”和"[/EXP]"包括起来的表达式字符串</p>
	 * @param expStr
	 * @return <pre>被分隔开的可显示字符串和字符串中需要运算的表达式。在Map中如下存放：
	 * 	1、被抽离表达式的可显示字符串，Map中的Key为：“str”，Map中的Value为字符串，
	 * 其中包括待替换运算结果的占位符：“[EXPR]X[/EXPR]”，占位符中的X代表表达式的顺序，从0开始。
	 * 	2、被从字符串中抽离出来的表达式，Map中的Key为：“exps”，Map中的Value为List&lt;String&gt;。</pre>
	 */
	private Map<String, Object> parseExpressionStr(String expStr){
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer str = new StringBuffer();// 存放新字符串
		List<String> exps = new ArrayList<String>();// 存放找到的表达式
		
		if (expStr != null && !expStr.equals("")) {
			int expIndex = 0;// 找到的表达式所处位置的索引号
			int beginExp = expStr.indexOf(beginIden);// 查找第一个表达式开始标识
			int endExp = -1;
			while (beginExp > -1) {// 表达式开始标识是否找到
				// 查找结束标识
				endExp = expStr.indexOf(endIden, beginExp);
				if (endExp < 0) {// 表达式结束标识未找到，表达式编写不正确，忽略
					str.append(expStr);
					break;
				}

				// 抽离可显示字符串中的表达式，形成新的字符串
				str.append(expStr.substring(0, beginExp));
				
				// 保存找到的表达式
				exps.add(expStr.substring(beginExp, endExp + endIden.length())
						.replace(beginIden, "")
						.replace(endIden, ""));
				// 放置表达式占位符
				str.append(rstBeginIden);
				str.append(expIndex);
				str.append(rstEndIden);

				// 准备下次循环
				expStr = expStr.substring(endExp + endIden.length());// 截断原完整字符串，剪去已被搜索的区域
				expIndex++;
				beginExp = expStr.indexOf(beginIden);// 查找新的表达式开始标识
			}
			str.append(expStr);
		}
		
		result.put("str", str.toString());
		result.put("exps", exps);
		return result;
	}
}
