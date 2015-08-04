package db;

import db.pojo.*;
import model.Class;
import model.Method;
import model.Module;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 28-07-2015
 */
public class DataHandler {

	private final String projectName;
	private final RunInfoEntity runInfoEntity;

	public DataHandler(String projectName) {
		this.projectName = projectName;
		this.runInfoEntity = Converter.createRunInfo(projectName);
		insert(runInfoEntity);
	}

	public void save(String commitSha, Set<Class> classes) {
		VersionEntity versionEntity = Converter.createVersion(runInfoEntity, commitSha);
		insert(versionEntity);

		Set<Module> modules = getModules(classes);
		Map<Module, ModuleEntity> moduleMap = Converter.convertModules(versionEntity, modules);
		insert(moduleMap.values());

		Map<Class, ClassEntity> classMap = Converter.convertClasses(moduleMap, classes);
		insert(classMap.values());

		List<DependenciesEntity> dependencies = Converter.createDependencies(classMap);
		insert(dependencies);

		Set<Method> methods = getMethods(classes);
		Map<Method, MethodEntity> methodMap = Converter.convertMethods(classMap, methods);
		insert(methodMap.values());
	}

	private Set<Method> getMethods(Set<Class> classes) {
		Set<Method> methods = new HashSet<>();
		classes.stream().forEach(c -> methods.addAll(c.getMethods()));
		return methods;
	}

	private Set<Module> getModules(Set<Class> classes) {
		return classes.stream()
				.map(c -> c.getModule())
				.collect(Collectors.toSet());
	}

	private void insert(Object o) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Session session = cm.openSession();
		Transaction tx = session.beginTransaction();
		session.save(o);
		tx.commit();
		session.close();
	}

	private <T> void insert(Collection<T> objects) {
		insertObjects(objects.stream().map(m -> (Object) m).collect(Collectors.toList()));
	}

	private void insertObjects(List<Object> objects) {
		ConnectionManager cm = ConnectionManager.getInstance();
		StatelessSession session = cm.openStatelessSession();
		Transaction tx = session.beginTransaction();
		objects.forEach(o -> session.insert(o));
		tx.commit();
		session.close();
	}
}
