package net.firecoder.test.beans.formula;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

public interface FormulaManager {
	public void addFormula(FormulaPojo formula) throws BeanException;
	
	public Pagination<FormulaPojo> listFormulas(int startIndex, int pageSize) throws BeanException;
	
	public void deleteFormulaById(FormulaPojo formula) throws BeanException;
	
	public void modifyFormulaById(FormulaPojo formula) throws BeanException;
	
	public FormulaPojo getFormulaById(FormulaPojo formula) throws BeanException;
}
