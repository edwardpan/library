package net.firecoder.expressionhelper.functions;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>数学计算相关函数</p>
 * @author 潘超
 * @created 2011-7-15 上午11:08:07
 */
public class MathFunctions {
	private Logger logger = LoggerFactory.getLogger(MathFunctions.class);
	
	/**
	 * <p>double浮点数四舍五入函数。小于或等于4则舍掉，大于等于5则进1。</p>
	 * @param value 需要四舍五入取精度的浮点数
	 * @param scale 保留的小数位
	 * @return
	 */
	public String roundUp(double value, int scale) {
		BigDecimal d = new BigDecimal(value);
		BigDecimal result = d.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return result.toString();
	}
	
}
