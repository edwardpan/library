package net.firecoder.test.beans.formula.impl;

import java.util.List;

import org.apache.log4j.Logger;

import net.firecoder.test.actions.expression.ExpressionAction;
import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.Dao;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaElementPojo;
import net.firecoder.test.pojo.FormulaPojo;

public class FormulaManagerImpl implements FormulaManager {

	private final Logger log = Logger.getLogger(ExpressionAction.class);
	private Dao<FormulaPojo> formulaDao;
	private Dao<FormulaElementPojo> formulaElementDao;
	
	public void setFormulaDao(Dao<FormulaPojo> formulaDao) {
		this.formulaDao = formulaDao;
	}

	public void setFormulaElementDao(Dao<FormulaElementPojo> formulaElementDao) {
		this.formulaElementDao = formulaElementDao;
	}

	@Override
	public void addFormula(FormulaPojo formula, List<FormulaElementPojo> formulaElList) throws BeanException {
		try {
			String id = formulaDao.add(formula);
			// TODO 添加算术公式中的计算元素
			if (formulaElList != null) {
				for (FormulaElementPojo el : formulaElList) {
					el.setFormulaId(Integer.parseInt(id));
					formulaElementDao.add(el);
				}
			}
		} catch (Exception ex) {
			throw new BeanException(ex);
		}
	}

	@Override
	public Pagination<FormulaPojo> listFormulas(FormulaPojo term, int startIndex, int pageSize) throws BeanException {
		Pagination<FormulaPojo> page = null;
		try {
			page = formulaDao.findAll(term, startIndex, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public void deleteFormulaById(FormulaPojo formula) throws BeanException {
		try {
			formulaDao.delete(formula);
			FormulaElementPojo term = new FormulaElementPojo();
			term.setFormulaId(formula.getId());
			formulaElementDao.deleteByTerm(term);
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

	@Override
	public Pagination<FormulaElementPojo> listElementByFormulaId(
			FormulaElementPojo term) throws BeanException {
		Pagination<FormulaElementPojo> page = null;
		try {
			page = formulaElementDao.findAll(term, 0, 99999);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
