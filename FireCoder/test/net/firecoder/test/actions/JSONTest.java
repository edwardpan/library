package net.firecoder.test.actions;

import java.util.List;

import net.firecoder.expressionhelper.Variable;
import net.sf.json.JSONArray;

import org.junit.Test;


public class JSONTest {
	@Test
	public void test() {
		String expVars = "[{name:\"A\",value:1},{name:\"B\",value:2}]";
		JSONArray json = JSONArray.fromObject(expVars);
		List<Variable> expVarList = JSONArray.toList(json, Variable.class);
		System.out.println(expVarList);
	}
}
