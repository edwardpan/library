/* FormulaElementPojo.java
 * project: FireCoder
 */
package net.firecoder.test.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
@Entity
@Table(name="FormulaElement")
public class FormulaElementPojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="value")
	private BigDecimal value;
	@Column(name="valueUnit")
	private String valueUnit;
	@Column(name="dataType")
	private String dataType;
	@Column(name="writable")
	private String writable;
	@Column(name="isFinal")
	private String isFinal;
	@Column(name="minVal")
	private BigDecimal minVal;
	@Column(name="maxVal")
	private BigDecimal maxVal;
	//@ManyToOne(targetEntity=FormulaPojo.class)
	//@JoinColumn(name="formulaId", nullable=false, updatable=false)
	//private FormulaPojo formula;
	private int formulaId;
	
	public int getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(int formulaId) {
		this.formulaId = formulaId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getWritable() {
		return writable;
	}
	public void setWritable(String writable) {
		this.writable = writable;
	}
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	public BigDecimal getMinVal() {
		return minVal;
	}
	public void setMinVal(BigDecimal minVal) {
		this.minVal = minVal;
	}
	public BigDecimal getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(BigDecimal maxVal) {
		this.maxVal = maxVal;
	}
	
}
