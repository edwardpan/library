/* FormulaDao.java
 * project: FireCoder
 */
package net.firecoder.test.dao;

import java.util.List;

import net.firecoder.test.pojo.FormulaPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
public interface FormulaDao {
	public void addFormula(FormulaPojo pojo) throws Exception;
	
	public List<FormulaPojo> listFormula() throws Exception;
}
