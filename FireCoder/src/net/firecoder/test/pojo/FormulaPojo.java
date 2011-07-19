/* FormulaPojo.java
 * project: FireCoder
 */
package net.firecoder.test.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
@Entity
@Table(name="Formula")
public class FormulaPojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="expression")
	private String expression;
	@Column(name="isSetScale")
	private String isSetScale;
	@Column(name="scale")
	private int scale;
	@OneToMany
	private List<FormulaElementPojo> formulaElement;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getIsSetScale() {
		return isSetScale;
	}
	public void setIsSetScale(String isSetScale) {
		this.isSetScale = isSetScale;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public List<FormulaElementPojo> getFormulaElement() {
		return formulaElement;
	}
	public void setFormulaElement(List<FormulaElementPojo> formulaElement) {
		this.formulaElement = formulaElement;
	}
	
}
