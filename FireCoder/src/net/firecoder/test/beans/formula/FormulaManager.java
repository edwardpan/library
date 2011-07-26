package net.firecoder.test.beans.formula;

import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaElementPojo;
import net.firecoder.test.pojo.FormulaPojo;

public interface FormulaManager {
	public void addFormula(FormulaPojo formula, List<FormulaElementPojo> formulaElList) throws BeanException;
	
	public Pagination<FormulaPojo> listFormulas(FormulaPojo term, int startIndex, int pageSize) throws BeanException;
	
	public void deleteFormulaById(FormulaPojo formula) throws BeanException;
	
	public void modifyFormulaById(FormulaPojo formula) throws BeanException;
	
	public FormulaPojo getFormulaById(FormulaPojo formula) throws BeanException;
	
	public Pagination<FormulaElementPojo> listElementByFormulaId(FormulaElementPojo term) throws BeanException;
}
