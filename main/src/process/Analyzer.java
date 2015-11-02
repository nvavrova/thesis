package process;

import model.Class;
import model.Subroutine;
import model.Project;
import util.LexicalHelper;

/**
 * Created by Nik on 15-10-2015
 */
//TODO: decide on the constants, limits, etc.
public class Analyzer {

	private final Project project;

	public Analyzer(Project project) {
		this.project = project;
	}

	public void analyze() {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + project.getName());
		System.out.println("Location: " + project.getPath());

		//TODO

		System.out.println("-----------------------------------------------------------------");
	}

//	public void analyze(Module module) {}

	public void analyze(Class cls) {}

	public void analyze(Subroutine subroutine) {}

	public boolean isBlob(Class cls) {
		return (this.isLargeClass(cls) || this.hasLowCohesion(cls)) &&
				(this.hasControllerName(cls.getName()) || this.hasControllerMethods(cls))
				;
		//&& this.relatedDataClassesCount() > 1;
	}

	public boolean isSwissArmyKnife(Class cls) {
		return this.hasTooManyParents(cls);
	}

	public boolean isFunctionalDecomposition(Class cls) {
		return (this.relatedPrivateFieldsWithOneMethodCount(cls) > 2) && (this.hasProceduralName(cls.getName()) && this.noInheritance(cls)); //TODO
	}

	public boolean isSpaghettiCode(Class cls) {
		return this.noInheritance(cls) && this.hasProceduralName(cls.getName()) && this.hasLongMethod(cls)
				&& this.hasTooManyMethodsWithNoParams(cls) && this.usesGlobals(cls);
	}

	public boolean usesGlobals(Class cls) {
		return cls.getReferencedGlobalsSet().size() > 1;
	}

	private Boolean hasControllerName(String name) {
		return LexicalHelper.isControllerName(name);
	}

	private Boolean hasProceduralName(String name) {
		return LexicalHelper.isProceduralName(name);
	}


	private boolean isDataClass(Class cls) {
		Long publicVarCount = cls.getDefinedVariablesSet().stream().filter(v -> v.isPrivate()).count();
		return publicVarCount > 11;
//		return cls.getNumberOfAccessors() > 5;
	}

	private boolean hasLowCohesion(Class cls) {
		return cls.getLcom() > 564;
	}

	private boolean isLargeClass(Class cls) {
		return cls.getLoc() > 186;
	}


	private boolean noInheritance(Class cls) {
		return cls.getParentNames().size() == 0;
	}

	private boolean hasTooManyParents(Class cls) {
		return cls.parentsCount() > 1;
	}

	private boolean hasLongMethod(Class cls) {
		for (Subroutine m : cls.getDefinedSubroutinesSet()) {
			if (this.isLong(m)) {
				return true;
			}
		}
		return false;
	}

	private Integer relatedDataClassesCount(Class cls) {
		//TODO
		Long count = cls.getReferencedClassesSet().stream()
//				.filter(c -> c.isDataClass())
				.count();
		return count.intValue();
	}

	private boolean hasTooManyMethodsWithNoParams(Class cls) {
		return cls.subroutinesWithNoParamsCount() > 7;
	}

	private Boolean hasControllerMethods(Class cls) {
		for (Subroutine m : cls.getDefinedSubroutinesSet()) {
			if (this.isController(m)) {
				return true;
			}
		}
		return false;
	}

	private Integer relatedPrivateFieldsWithOneMethodCount(Class cls) {
		//TODO
		Long count = cls.getReferencedClassesSet().stream()
//				.filter(Class::privateFieldsWithOnePublicMethod)
				.count();
		return count.intValue();
	}


	//                   METHODS
	public boolean isLong(Subroutine m) {
		return m.getLoc() > 100;
	}

	public boolean isController(Subroutine m) {
		return this.hasControllerName(m.getName());
	}



}
