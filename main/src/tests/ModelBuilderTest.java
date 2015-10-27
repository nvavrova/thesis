package tests;

import model.Class;
import model.Subroutine;
import model.Variable;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
		Set<Variable> oneVars = one.getDefinedVariables();
		assert (oneVars.size() == 2);
//		assert (oneVars.contains("self.var"));
//		assert (oneVars.contains("ClsOne.var"));

		Class two = classes.get("ClsTwo");
		Set<String> twoVars = two.getDefinedVariables().stream().map(v -> v.getName()).collect(Collectors.toSet());
		assert (twoVars.size() == 3);
//		assert (twoVars.contains("self.varr"));
//		assert (twoVars.contains("self.co"));
//		assert (twoVars.contains("self.co2"));
//		assert (!twoVars.contains("self.co.var"));
	}

	@Test
	public void collectMethods() {
		Map<String, Class> classes = TestHelper.getClasses("collect_methods/collect_methods.py");
		assert (classes.size() == 2);

		Class one = classes.get("ClsOne");
		assert (one.subroutineCount() == 2);
		assert (one.subroutinesWithNoParamsCount() == 1);

		Class two = classes.get("ClsTwo");
		assert (two.subroutineCount() == 1);
	}

	@Test
	public void collectGlobals() {
		Map<String, Class> classes = TestHelper.getClasses("globals");

		Set<String> srcCls = classes.get("SrcCls").getReferencedGlobals().stream().map(v -> v.getName()).collect(Collectors.toSet());
		Set<String> usrCls = classes.get("UsrCls").getReferencedGlobals().stream().map(v -> v.getName()).collect(Collectors.toSet());
		Set<String> firstTestCls = classes.get("FirstTestCls").getReferencedGlobals().stream().map(v -> v.getName()).collect(Collectors.toSet());
		Set<String> secondTestCls = classes.get("SecondTestCls").getReferencedGlobals().stream().map(v -> v.getName()).collect(Collectors.toSet());

		assert (srcCls.size() == 1);
		assert (srcCls.contains("non_cls_glob"));
		assert (!srcCls.contains("var"));

		assert (usrCls.size() == 3);
		assert (usrCls.contains("s.var"));
		assert (usrCls.contains("s.non_cls_glob"));
		assert (usrCls.contains("Multiplier.multip2"));
		assert (!usrCls.contains("Multiplier.multiplier"));
		assert (!usrCls.contains("UsrCls.multiplier"));

		assert (firstTestCls.size() == 0);
		assert (!firstTestCls.contains("sc.first"));

		assert (secondTestCls.size() == 1);
		assert (secondTestCls.contains("sc.cls_glob"));
		assert (!secondTestCls.contains("sc.first"));
		assert (!secondTestCls.contains("sc.third"));
		assert (!secondTestCls.contains("sc.met"));
	}

	@Test
	public void checkMethodNonClassVarUsage() {
		Map<String, Class> classes = TestHelper.getClasses("method_variables");

		//TODO: add check for a method using inherited var!
		Map<String, Subroutine> userCls = classes.get("UserCls").getDefinedSubroutinesByName();
		Map<String, Subroutine> userCls2 = classes.get("UserCls2").getDefinedSubroutinesByName();

		assert (userCls.get("method_one").getUsedNonInstanceVars().size() == 1);
		assert (userCls.get("method_one").getUsedNonInstanceVars().contains("sc.parent_var"));

		assert (userCls.get("method_two").getUsedNonInstanceVars().size() == 2);
		assert (userCls.get("method_two").getUsedNonInstanceVars().contains("sec.parent_var"));
		assert (userCls.get("method_two").getUsedNonInstanceVars().contains("sec.child_var"));

//		assert (userCls2.get("method_three").getUsedNonInstanceVars().size() == 1);
//		assert (userCls2.get("method_three").getUsedNonInstanceVars().contains("s.out_of_cls_var"));

		assert (userCls2.get("method_one").getUsedNonInstanceVars().size() == 1);
		assert (userCls2.get("method_one").getUsedNonInstanceVars().contains("sc.parent_var"));

		assert (userCls2.get("method_two").getUsedNonInstanceVars().size() == 3);
		assert (userCls2.get("method_two").getUsedNonInstanceVars().contains("s.SrcClass.parent_var"));
		assert (userCls2.get("method_two").getUsedNonInstanceVars().contains("s.SrcExtendedClass.parent_var"));
		assert (userCls2.get("method_two").getUsedNonInstanceVars().contains("s.SrcExtendedClass.child_var"));
	}

	@Test
	public void checkMethodClassVarUsage() {
		Map<String, Class> classes = TestHelper.getClasses("method_variables");
		Map<String, Subroutine> userCls = classes.get("UserCls").getDefinedSubroutinesByName();

		assert (userCls.get("method_three").getReferencedInstanceVariables().size() == 1);
		assert (userCls.get("method_three").getReferencedInstanceVariables().contains("self.child_var"));
	}

	@Test
	public void checkParentDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("parents");

		Class baseCls = classes.get("BaseCls");
		Class subBaseCls = classes.get("SubBaseCls");
		Class libCls = classes.get("LibCls");
		Class derived = classes.get("Derived");

		assert (baseCls.getReferencedClasses().size() == 0);
		assert (subBaseCls.getReferencedClasses().size() == 1);
		assert (libCls.getReferencedClasses().size() == 1);
		assert (derived.getReferencedClasses().size() == 2);
	}

	@Test
	public void checkDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("dependencies");

		Class main = classes.get("Main");
		Set<Class> dependencies = main.getReferencedClasses();

		assert (dependencies.size() == 3);
		assert (dependencies.contains(classes.get("Base1")));
		assert (dependencies.contains(classes.get("Base2")));
		assert (dependencies.contains(classes.get("TotalBase")));


		Class main2 = classes.get("Main2");
		Set<Class> dependencies2 = main2.getReferencedClasses();
		assert (dependencies2.size() == 1);
		assert (dependencies2.contains(classes.get("Base1")));
	}
}
