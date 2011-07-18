package net.firecoder.expressionhelper.functions;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>��ѧ������غ���</p>
 * @author �˳�
 * @created 2011-7-15 ����11:08:07
 */
public class MathFunctions {
	private Logger logger = LoggerFactory.getLogger(MathFunctions.class);
	
	/**
	 * <p>double�������������뺯����С�ڻ����4����������ڵ���5���1��</p>
	 * @param value ��Ҫ��������ȡ���ȵĸ�����
	 * @param scale ������С��λ
	 * @return
	 */
	public String roundUp(double value, int scale) {
		BigDecimal d = new BigDecimal(value);
		BigDecimal result = d.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return result.toString();
	}
	
}
