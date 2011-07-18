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
 * <p>IK���ʽ�����������ࡣ�ṩʹ�ñ��ʽ��������ͳһ��ڡ�
 * ֧���������ַ�����ʹ�á�[EXP][/EXP]������ʶ���ʽ���ҿɱ�ʶ������ʽ��<br/>
 * �磺�����������Ϊ��[EXP]$dateToString($SYSDATE(), "yyyyMMdd")[/EXP]��315������Ϊ��[EXP]$dateToString([2011-03-15], "yyyyMMdd")[/EXP]����<br/>
 * ������Ϊ�������������Ϊ��20110329��315������Ϊ��20110315��</p>
 * @author �˳�
 * @date 2011-3-29 ����10:08:53
 */
public class ExpressionHelper {
	private Logger logger = LoggerFactory.getLogger(ExpressionHelper.class);
	
	private final String beginIden = "[EXP]";// ���ʽ��ʼ��ʶ
	private final String endIden = "[/EXP]";// ���ʽ������ʶ
	private final String rstBeginIden = "[EXPR]";// ���ʽ��ʱռλ����ʼ��ʶ
	private final String rstEndIden = "[/EXPR]";// ���ʽ��ʱռλ��������ʶ

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
	 * <p>��ȡ���ʽ��������ʵ�������û���򴴽�</p>
	 * @return
	 */
	public static ExpressionHelper getInstance() {
		// ����һ�����ʽ�������ʵ������ʹ��ʱ�����ñ����������ֵ�����Բ��ʺ�ʹ�õ���ģʽ
		return new ExpressionHelper();
	}

	/**
	 * <p>ִ�к����ʽ���ַ���������{����"}"���������ı��ʽ�ַ�������
	 * ���ʽ������ʹ��IKExpression���棬����ѭ��淶��</p>
	 * @return
	 */
	public String executeFullStr() {
		// ������ַ����еı��ʽ
		Map<String, Object> parsedResult = parseExpressionStr(fullExpStr);
		String str = (String) parsedResult.get("str");
		List<String> exps = (List<String>) parsedResult.get("exps");// �õ����ʽ
		logger.debug("������ַ����еı��ʽ��");
		logger.debug("�ַ�����" + str);
		logger.debug("���ʽ��" + exps.toArray());
		
		logger.debug("��ȡ�ַ����б���" + beginIden + endIden + "�������ŵı��ʽ");
		// ������
		List<Object> resultList = executeExpression(exps, variables);
		logger.debug("�����ÿһ�����ʽ�Ľ��");

		// ��ϷǱ��ʽ�ַ�������ʽ�ַ�����������
		for (int i = 0; i < resultList.size(); i++) {
			Object value = resultList.get(i);
			str = str.replace(rstBeginIden + i + rstEndIden, (String) value.toString());
		}
		logger.debug("������Ǳ��ʽ�ַ����ͱ��ʽ�����ϳ������Ľ���ַ���");

		return str.toString();
	}

	/**
	 * <p>ִ�б��ʽ</p>
	 * @param expList ���ʽ�ļ��ϣ����ʽ�в��ٺ��гɶԵ�{}����
	 * @return
	 */
	private List<Object> executeExpression(List<String> expList, List<net.firecoder.expressionhelper.Variable> myVariables){
		List<Variable> variables = createVariables(myVariables);

		// ִ�б��ʽ
		List<Object> resultList = new ArrayList<Object>();
		for (String exp : expList) {
			logger.debug("ִ�б��ʽ��" + exp + "����Ϊ������ֵ����������" + variables.size());
			Object result = ExpressionEvaluator.evaluate(exp, variables);
			logger.debug("���ʽ" + exp + "��ִ�н����" + result);
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * <p>��������</p>
	 * @param myVariables
	 * @return
	 */
	private List<Variable> createVariables(List<net.firecoder.expressionhelper.Variable> myVariables){
		List<Variable> variables = new ArrayList<Variable>();
		if (myVariables != null) {
			for (net.firecoder.expressionhelper.Variable myVariable : myVariables) {
				logger.debug("����������" + myVariable.getName() + " - " +  myVariable.getValue());
				variables.add(Variable.createVariable(myVariable.getName(), myVariable.getValue()));
			}
		}
		return variables;
	}

	/**
	 * <p>�Ӻ����ʽ���ַ����в�ѯ����[EXP]����"[/EXP]"���������ı��ʽ�ַ���</p>
	 * @param expStr
	 * @return <pre>���ָ����Ŀ���ʾ�ַ������ַ�������Ҫ����ı��ʽ����Map�����´�ţ�
	 * 	1����������ʽ�Ŀ���ʾ�ַ�����Map�е�KeyΪ����str����Map�е�ValueΪ�ַ�����
	 * ���а������滻��������ռλ������[EXPR]X[/EXPR]����ռλ���е�X������ʽ��˳�򣬴�0��ʼ��
	 * 	2�������ַ����г�������ı��ʽ��Map�е�KeyΪ����exps����Map�е�ValueΪList&lt;String&gt;��</pre>
	 */
	private Map<String, Object> parseExpressionStr(String expStr){
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer str = new StringBuffer();// ������ַ���
		List<String> exps = new ArrayList<String>();// ����ҵ��ı��ʽ
		
		if (expStr != null && !expStr.equals("")) {
			int expIndex = 0;// �ҵ��ı��ʽ����λ�õ�������
			int beginExp = expStr.indexOf(beginIden);// ���ҵ�һ�����ʽ��ʼ��ʶ
			int endExp = -1;
			while (beginExp > -1) {// ���ʽ��ʼ��ʶ�Ƿ��ҵ�
				// ���ҽ�����ʶ
				endExp = expStr.indexOf(endIden, beginExp);
				if (endExp < 0) {// ���ʽ������ʶδ�ҵ������ʽ��д����ȷ������
					str.append(expStr);
					break;
				}

				// �������ʾ�ַ����еı��ʽ���γ��µ��ַ���
				str.append(expStr.substring(0, beginExp));
				
				// �����ҵ��ı��ʽ
				exps.add(expStr.substring(beginExp, endExp + endIden.length())
						.replace(beginIden, "")
						.replace(endIden, ""));
				// ���ñ��ʽռλ��
				str.append(rstBeginIden);
				str.append(expIndex);
				str.append(rstEndIden);

				// ׼���´�ѭ��
				expStr = expStr.substring(endExp + endIden.length());// �ض�ԭ�����ַ�������ȥ�ѱ�����������
				expIndex++;
				beginExp = expStr.indexOf(beginIden);// �����µı��ʽ��ʼ��ʶ
			}
			str.append(expStr);
		}
		
		result.put("str", str.toString());
		result.put("exps", exps);
		return result;
	}
}
