package tests;

import model.Class;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 08-07-2015
 */
public class ModelBuilderTest {

	@Test
	public void collectClasses() {
		Map<String, Class> classes = TestHelper.getClasses("collect_classes");
		assert (classes.size() == 10);

		assert (classes.keySet().contains("First"));
		assert (classes.keySet().contains("Second"));
		assert (classes.keySet().contains("Second.Third"));
		assert (classes.keySet().contains("Second.Third.Fourth"));
		assert (classes.keySet().contains("Second.Third.Fourth.Fifth"));
		assert (classes.keySet().contains("Second.Third.Sixth"));
		assert (classes.keySet().contains("Seventh"));
		assert (classes.keySet().contains("First2"));
		assert (classes.keySet().contains("First2.Second2"));
		assert (classes.keySet().contains("Third2"));
	}

	@Test
	public void collectVariables() {
		Map<String, Class> classes = TestHelper.getClasses("collect_variables/collect_variables.py");

		Class one = classes.get("ClsOne");
		Set<String> oneVars = one.getVariables();
		assert (oneVars.size() == 2);
		assert (oneVars.contains("self.var"));
		assert (oneVars.contains("ClsOne.var"));

		Class two = classes.get("ClsTwo");
		Set<String> twoVars = two.getVariables();
		assert (twoVars.size() == 3);
		assert (twoVars.contains("self.varr"));
		assert (twoVars.contains("self.co"));
		assert (twoVars.contains("self.co2"));
		assert (!twoVars.contains("self.co.var"));
	}

	@Test
	public void collectMethods() {
		Map<String, Class> classes = TestHelper.getClasses("collect_methods/collect_methods.py");
		assert (classes.size() == 2);

		Class one = classes.get("ClsOne");
		assert (one.methodCount() == 2);
		assert (one.methodsWithNoParamsCount() == 1);

		Class two = classes.get("ClsTwo");
		assert (two.methodCount() == 1);
	}

	@Test
	public void collectGlobals() {
		Map<String, Class> classes = TestHelper.getClasses("globals");

		Set<String> usrCls = classes.get("UsrCls").getUsedGlobals();
		Set<String> firstTestCls = classes.get("FirstTestCls").getUsedGlobals();
		Set<String> secondTestCls = classes.get("SecondTestCls").getUsedGlobals();

		assert (usrCls.size() == 2);
		assert (usrCls.contains("s.var"));
		assert (usrCls.contains("s.non_cls_glob"));

		assert (firstTestCls.size() == 0);
		assert (!firstTestCls.contains("sc.first"));

		assert (secondTestCls.size() == 1);
		assert (secondTestCls.contains("sc.cls_glob"));
		assert (!secondTestCls.contains("sc.first"));
		assert (!secondTestCls.contains("sc.third"));
		assert (!secondTestCls.contains("sc.met"));
	}

	@Test
	public void checkParentDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("parents");

		Class baseCls = classes.get("BaseCls");
		Class subBaseCls = classes.get("SubBaseCls");
		Class libCls = classes.get("LibCls");
		Class derived = classes.get("Derived");

		assert (baseCls.getDependencies().size() == 0);
		assert (subBaseCls.getDependencies().size() == 1);
		assert (libCls.getDependencies().size() == 1);
		assert (derived.getDependencies().size() == 2);
	}

	@Test
	public void checkDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("dependencies");

		Class main = classes.get("Main");
		Set<Class> dependencies = main.getDependencies();

		assert (dependencies.size() == 3);
		assert (dependencies.contains(classes.get("Base1")));
		assert (dependencies.contains(classes.get("Base2")));
		assert (dependencies.contains(classes.get("TotalBase")));


		Class main2 = classes.get("Main2");
		Set<Class> dependencies2 = main2.getDependencies();
		assert (dependencies2.size() == 1);
		assert (dependencies2.contains(classes.get("Base1")));
	}
}
