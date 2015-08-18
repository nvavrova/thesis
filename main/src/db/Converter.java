package db;

import db.pojo.*;
import mining.Bug;
import mining.Bugs;
import model.Class;
import model.Method;
import model.Module;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Nik on 28-07-2015
 */
public class Converter {

	public static Map<BugEntity, List<BugFixCommitEntity>> createBugs(ProjectEntity projectEntity, Bugs bugs) {
		Map<BugEntity, List<BugFixCommitEntity>> bugEntityListMap = new HashMap<>();
		for (Bug bug : bugs) {
			BugEntity bugEntity = new BugEntity();
			bugEntity.setProjectEntity(projectEntity);
			bugEntity.setGitId(String.valueOf(bug.getId()));

			List<BugFixCommitEntity> fixCommits = new ArrayList<>();
			for (String commit : bug.getFixCommits()) {
				BugFixCommitEntity bfce = new BugFixCommitEntity();
				bfce.setCommitSha(commit);
				bfce.setBugEntity(bugEntity);
				fixCommits.add(bfce);
			}
			bugEntityListMap.put(bugEntity, fixCommits);
		}
		return bugEntityListMap;
	}

	public static RunInfoEntity createRunInfo(ProjectEntity projectEntity) {
		RunInfoEntity ri = new RunInfoEntity();
		ri.setRuntime(Timestamp.valueOf(LocalDateTime.now()));
		ri.setProjectEntity(projectEntity);
		return ri;
	}

	public static VersionEntity createVersion(RunInfoEntity runInfoEntity, String commitSha) {
		VersionEntity v = new VersionEntity();
		v.setRunInfoEntity(runInfoEntity);
		v.setCommitSha(commitSha);
		return v;
	}

	public static Map<Module, ModuleEntity> convertModules(VersionEntity versionEntity, Set<Module> modules) {
		Map<Module, ModuleEntity> moduleMap = new HashMap<>();
		for (Module m : modules) {
			moduleMap.put(m, convert(m, versionEntity));
		}
		return moduleMap;
	}

	public static Map<Class, ClassEntity> convertClasses(Map<Module, ModuleEntity> modules, Set<Class> classes) {
		Map<Class, ClassEntity> classMap = new HashMap<>();
		for (Class c : classes) {
			classMap.put(c, convert(c, modules.get(c.getModule())));
		}
		return classMap;
	}

	public static Map<Method, MethodEntity> convertMethods(Map<Class, ClassEntity> classes, Set<Method> methods) {
		Map<Method, MethodEntity> methodMap = new HashMap<>();
		for (Method m : methods) {
			methodMap.put(m, convert(m, classes.get(m.getCls())));
		}
		return methodMap;
	}

	public static List<DependenciesEntity> createDependencies(Map<Class, ClassEntity> classMap) {
		List<DependenciesEntity> dependencies = new ArrayList<>();
		for (Class dependentCls : classMap.keySet()) {
			ClassEntity dependent = classMap.get(dependentCls);
			dependentCls.getDependencies().stream()
					.filter(classMap::containsKey)
					.forEach(c -> dependencies.add(createDependency(dependent, classMap.get(c))));
		}
		return dependencies;
	}

	private static DependenciesEntity createDependency(ClassEntity dependent, ClassEntity target) {
		DependenciesEntity depEnt = new DependenciesEntity();
		depEnt.setDependent(dependent);
		depEnt.setTarget(target);
		return depEnt;
	}

	private static MethodEntity convert(Method method, ClassEntity classEntity) {
		MethodEntity mthdEnt = new MethodEntity();
		mthdEnt.setName(method.getName());
		mthdEnt.setClassEntity(classEntity);
		mthdEnt.setLoc(method.getLoc());
		mthdEnt.setIsAccessor(method.isAccessor());
		mthdEnt.setNrParams(method.paramCount().shortValue());
		return mthdEnt;
	}

	private static ClassEntity convert(Class c, ModuleEntity moduleEntity) {
		ClassEntity clsEnt = new ClassEntity();
		clsEnt.setModuleEntity(moduleEntity);
		clsEnt.setLcom(c.getLcom());
		clsEnt.setLoc(c.getLoc());
		clsEnt.setName(c.getName());
		clsEnt.setNrParents(c.parentsCount().shortValue());
		clsEnt.setNrPrivateVars(c.privateVariablesCount().shortValue());
		clsEnt.setNrPublicVars(c.publicVariablesCount().shortValue());
		clsEnt.setNrUsedGlobals(c.usedGlobalsCount().shortValue());
		return clsEnt;
	}

	private static ModuleEntity convert(Module module, VersionEntity versionEntity) {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setVersionEntity(versionEntity);
		moduleEntity.setPath(module.getFilePath());
		moduleEntity.setErrorMessages(module.getError());
		return moduleEntity;
	}
}
