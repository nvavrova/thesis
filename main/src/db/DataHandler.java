package db;

import model.Class;
import model.Method;
import model.Module;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import db.pojo.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 28-07-2015
 */
public class DataHandler {

	private final String projectName;
	private final Set<Class> classes;

	public DataHandler(String projectName, Set<Class> classes) {
		this.projectName = projectName;
		this.classes = classes;
	}

	public void save() {
		RunInfoEntity runInfoEntity = Converter.createRunInfo(projectName);
		insert(runInfoEntity);

		System.out.println(runInfoEntity.getId());
		Set<Module> modules = getModules(classes);
		Map<Module, ModuleEntity> moduleMap = Converter.convertModules(runInfoEntity, modules);
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

	private void insert(RunInfoEntity runInfoEntity) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Session session = cm.openSession();
		Transaction tx = session.beginTransaction();
		session.save(runInfoEntity);
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
