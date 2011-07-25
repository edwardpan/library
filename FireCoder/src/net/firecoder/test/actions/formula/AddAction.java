/* FormulaAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaElementPojo;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author 潘超
 * create: 2011-7-19
 */
@ParentPackage("json-default")
public class AddAction extends ActionSupport implements ModelDriven<FormulaPojo> {
	
	private FormulaManager formulaManager;
	
	private FormulaPojo formula = new FormulaPojo();
	private String[] names;
	private String[] values;
	private String[] valueUnits;
	private String[] dataTypes;
	private String[] writables;
	private String[] isFinals;
	private String[] minVals;
	private String[] maxVals;
	
	@Action(value="add",
		results={
			@Result(name="success", type="json")
		}
	)
	public String execute() {
		// 接收计算元素参数
		List<FormulaElementPojo> formulaElList = new ArrayList<FormulaElementPojo>();
		FormulaElementPojo formulaEl = null;
		if (names != null) {
			for (int i = 0; i < names.length; i++) {
				formulaEl = new FormulaElementPojo();
				formulaEl.setName(names[i]);
				// 默认值
				String value = values[i];
				BigDecimal valueDem = null;
				if (value != null && !value.equals("")) {
					valueDem = new BigDecimal(value);
				} else {
					valueDem = new BigDecimal(0);
				}
				formulaEl.setValue(valueDem);
				formulaEl.setValueUnit(valueUnits[i]);
				formulaEl.setDataType(dataTypes[i]);
				formulaEl.setWritable(writables[i]);
				formulaEl.setIsFinal(isFinals[i]);
				// 最小值
				String minVal = minVals[i];
				BigDecimal minValDem = null;
				if (minVal != null && !minVal.equals("")) {
					minValDem = new BigDecimal(minVal);
				} else {
					minValDem = new BigDecimal(0);
				}
				formulaEl.setMinVal(minValDem);
				// 最大值
				String maxVal = maxVals[i];
				BigDecimal maxValDem = null;
				if (maxVal != null && !maxVal.equals("")) {
					maxValDem = new BigDecimal(maxVal);
				} else {
					maxValDem = new BigDecimal(0);
				}
				formulaEl.setMaxVal(maxValDem);
				formulaElList.add(formulaEl);
			}
		}
		
		try {
			formulaManager.addFormula(formula, formulaElList);
		} catch (BeanException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	@Override
	public FormulaPojo getModel() {
		return formula;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public void setValueUnits(String[] valueUnits) {
		this.valueUnits = valueUnits;
	}

	public void setDataTypes(String[] dataTypes) {
		this.dataTypes = dataTypes;
	}

	public void setWritables(String[] writables) {
		this.writables = writables;
	}

	public void setIsFinals(String[] isFinals) {
		this.isFinals = isFinals;
	}

	public void setMinVals(String[] minVals) {
		this.minVals = minVals;
	}

	public void setMaxVals(String[] maxVals) {
		this.maxVals = maxVals;
	}

}
