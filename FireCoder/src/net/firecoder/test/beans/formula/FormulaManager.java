package net.firecoder.test.beans.formula;

import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.pojo.FormulaPojo;

public interface FormulaManager {
	public void addFormula(FormulaPojo formula) throws BeanException;
	
	public List<FormulaPojo> listFormulas() throws BeanException;
}
