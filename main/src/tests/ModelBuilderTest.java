package tests;

import model.Class;
import model.Subroutine;
import model.Variable;
import org.junit.Test;

import java.util.HashMap;
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
		Set<Variable> oneVars = one.getDefinedVariablesSet();
		assert (oneVars.size() == 2); //self.var & ClsOne.var

		Class two = classes.get("ClsTwo");
		Set<String> twoVars = two.getDefinedVariablesSet().stream().map(Variable::getName).collect(Collectors.toSet());
		assert (twoVars.size() == 3); //self.varr, self.co & self.co2
	}

	@Test
	public void collectMethods() {
		Map<String, Class> classes = TestHelper.getClasses("collect_methods/collect_methods.py");
		assert (classes.size() == 2);

		Class one = classes.get("ClsOne");
		assert (one.getDefinedSubroutinesSet().size() == 2);
		assert (one.subroutinesWithNoParamsCount() == 1);

		Class two = classes.get("ClsTwo");
		assert (two.getDefinedSubroutinesSet().size() == 1);
	}

	@Test
	public void collectGlobals() {
		Map<String, Class> classes = TestHelper.getClasses("globals");

		Map<String, Variable> srcCls = this.sortVarsByName(classes.get("SrcCls").getReferencedGlobalsSet());
		Map<String, Variable> usrCls = this.sortVarsByName(classes.get("UsrCls").getReferencedGlobalsSet());
		Map<String, Variable> firstTestCls = this.sortVarsByName(classes.get("FirstTestCls").getReferencedGlobalsSet());
		Map<String, Variable> secondTestCls = this.sortVarsByName(classes.get("SecondTestCls").getReferencedGlobalsSet());

		assert (srcCls.size() == 1);
		assert (srcCls.keySet().contains("non_cls_glob"));
		assert (!srcCls.keySet().contains("var"));

		assert (usrCls.size() == 3);
		assert (usrCls.keySet().contains("var"));
		assert (usrCls.keySet().contains("non_cls_glob"));
		assert (usrCls.keySet().contains("multip2"));

		assert (firstTestCls.size() == 0);

		assert (secondTestCls.size() == 1);
		assert (secondTestCls.keySet().contains("cls_glob"));
	}

	@Test
	public void checkMethodNonClassVarUsage() {
		Map<String, Class> classes = TestHelper.getClasses("method_variables");

		Map<String, Subroutine> userCls = this.sortSubroutinesByName(classes.get("UserCls").getDefinedSubroutinesSet());
		Map<String, Subroutine> userCls2 = this.sortSubroutinesByName(classes.get("UserCls2").getDefinedSubroutinesSet());

		assert (userCls.get("method_one").getReferencedOutsideVariables().size() == 1); //parent_var
		assert (userCls.get("method_two").getReferencedOutsideVariables().size() == 2); //parent_var & child_var
		assert (userCls2.get("method_one").getReferencedOutsideVariables().size() == 1); //parent_var
		assert (userCls2.get("method_two").getReferencedOutsideVariables().size() == 3); //parent_var, parent_var & child_var
		assert (userCls2.get("method_three").getReferencedOutsideVariables().size() == 1); //out_of_cls_var
	}

	@Test
	public void checkMethodClassVarUsage() {
		Map<String, Class> classes = TestHelper.getClasses("method_variables");
		Map<String, Subroutine> userCls = this.sortSubroutinesByName(classes.get("UserCls").getDefinedSubroutinesSet());

		assert (userCls.get("method_three").getReferencedInsideVariables().size() == 1);
		assert (this.sortVarsByName(userCls.get("method_three").getReferencedInsideVariables()).containsKey("child_var"));
	}

	@Test
	public void checkParentDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("parents");

		Class baseCls = classes.get("BaseCls");
		Class subBaseCls = classes.get("SubBaseCls");
		Class libCls = classes.get("LibCls");
		Class derived = classes.get("Derived");

		assert (baseCls.getReferencedClassesSet().size() == 0);
		assert (subBaseCls.getReferencedClassesSet().size() == 1);
		assert (libCls.getReferencedClassesSet().size() == 1);
		assert (derived.getReferencedClassesSet().size() == 2);
	}

	@Test
	public void checkDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("dependencies");

		Class main = classes.get("Main");
		Set<Class> dependencies = main.getReferencedClassesSet();

		assert (dependencies.size() == 3);
		assert (dependencies.contains(classes.get("Base1")));
		assert (dependencies.contains(classes.get("Base2")));
		assert (dependencies.contains(classes.get("TotalBase")));


		Class main2 = classes.get("Main2");
		Set<Class> dependencies2 = main2.getReferencedClassesSet();
		assert (dependencies2.size() == 1);
		assert (dependencies2.contains(classes.get("Base1")));
	}

	@Test
	public void checkCyclicDependencies() {
		Map<String, Class> classes = TestHelper.getClasses("cyclic_dependencies");

		Class cls1 = classes.get("ClassOne");
		Class cls2 = classes.get("ClassTwo");

		assert (cls1.getReferencedClassesSet().size() + cls2.getReferencedClassesSet().size() == 1);
	}

	private Map<String, Subroutine> sortSubroutinesByName(Set<Subroutine> subroutines) {
		Map<String, Subroutine> sorted = new HashMap<>();
		for (Subroutine subroutine : subroutines) {
			sorted.put(subroutine.getName(), subroutine);
		}
		return sorted;
	}

	private Map<String, Variable> sortVarsByName(Set<Variable> vars) {
		Map<String, Variable> sorted = new HashMap<>();
		for (Variable var : vars) {
			sorted.put(var.getName(), var);
		}
		return sorted;
	}
}
