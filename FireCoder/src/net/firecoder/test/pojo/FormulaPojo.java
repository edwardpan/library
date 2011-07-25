/* FormulaPojo.java
 * project: FireCoder
 */
package net.firecoder.test.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.firecoder.test.dao.Term;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
@Entity
@Table(name="Formula")
public class FormulaPojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Term
	@Column(name="id")
	private int id;
	@Column(name="expression")
	@Term
	private String expression;
	@Column(name="isSetScale")
	@Term
	private String isSetScale;
	@Column(name="scale")
	private int scale;
	//@OneToMany(targetEntity=FormulaElementPojo.class, cascade=CascadeType.ALL, mappedBy="formula")
	//private List<FormulaElementPojo> formulaElement;
	@Column(name="resultUnit")
	@Term
	private String resultUnit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDT_RowId() {
		return id;
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
	public String getResultUnit() {
		return resultUnit;
	}
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	
}
