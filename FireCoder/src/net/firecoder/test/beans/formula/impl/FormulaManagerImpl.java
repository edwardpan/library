package net.firecoder.test.beans.formula.impl;

import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.FormulaDao;
import net.firecoder.test.pojo.FormulaPojo;

public class FormulaManagerImpl implements FormulaManager {

	private FormulaDao formulaDao;
	
	public void setFormulaDao(FormulaDao formulaDao) {
		this.formulaDao = formulaDao;
	}

	@Override
	public void addFormula(FormulaPojo formula) throws BeanException {
		try {
			formulaDao.addFormula(formula);
		} catch (Exception ex) {
			throw new BeanException(ex);
		}
	}

	@Override
	public List<FormulaPojo> listFormulas() throws BeanException {
		List<FormulaPojo> list = null;
		try {
			list =  formulaDao.listFormula();
		} catch (Exception ex) {
			throw new BeanException(ex);
		}
		return list;
	}

}
