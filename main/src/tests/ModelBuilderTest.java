package tests;

import org.junit.Test;
import model.Class;

import java.util.List;
import java.util.Set;

/**
 * Created by Nik on 08-07-2015
 */
public class ModelBuilderTest {

	@Test
	public void collectAttributes() {
		List<Class> classes = TestHelper.getClasses("collect_attributes.py");
		assert (classes.size() == 1);
		Class c = classes.get(0);
		Set<String> vars = c.getVariables();
		assert (vars.size() == 2);
		assert (vars.contains("self.var"));
		assert (vars.contains("TestCls.var"));
	}
}
