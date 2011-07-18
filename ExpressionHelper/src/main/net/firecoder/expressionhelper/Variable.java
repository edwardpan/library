package net.firecoder.expressionhelper;

/**
 * <p>表达式的参数</p>
 *
 * @author 潘超
 * @date 2011-3-23 上午10:55:55
 */
public class Variable {
	private String name;
	private Object value;
	
	public Variable() {
	}
	
	public Variable(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
