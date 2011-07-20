package net.firecoder.test.beans.formula.impl;

import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.FormulaDao;
import net.firecoder.test.dao.Pagination;
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
	public Pagination<FormulaPojo> listFormulas(int startIndex, int pageSize) throws BeanException {
		Pagination<FormulaPojo> page = null;
		try {
			page = formulaDao.findAll(startIndex, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
