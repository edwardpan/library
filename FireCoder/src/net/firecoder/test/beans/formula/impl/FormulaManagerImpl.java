package net.firecoder.test.beans.formula.impl;

import org.apache.log4j.Logger;

import net.firecoder.test.actions.expression.ExpressionAction;
import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.Dao;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

public class FormulaManagerImpl implements FormulaManager {

	private final Logger log = Logger.getLogger(ExpressionAction.class);
	private Dao<FormulaPojo> formulaDao;
	
	public void setFormulaDao(Dao<FormulaPojo> formulaDao) {
		this.formulaDao = formulaDao;
	}

	@Override
	public void addFormula(FormulaPojo formula) throws BeanException {
		try {
			String id = formulaDao.add(formula);
			// TODO 添加算术公式中的计算元素
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

	@Override
	public void deleteFormulaById(FormulaPojo formula) throws BeanException {
		try {
			formulaDao.delete(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyFormulaById(FormulaPojo formula) throws BeanException {
		try {
			formulaDao.update(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FormulaPojo getFormulaById(FormulaPojo formula) throws BeanException {
		FormulaPojo pojo = null;
		try {
			pojo = formulaDao.get(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}

}
