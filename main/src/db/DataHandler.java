package db;

import db.pojo.*;
import mining.Bugs;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 28-07-2015
 */
public class DataHandler {

	private final ProjectEntity projectEntity;

	public DataHandler(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public void save(Bugs bugs) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Session session = cm.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Map<BugEntity, List<BugFixCommitEntity>> bugRecords = Converter.createBugs(this.projectEntity, bugs);
			bugRecords.keySet().forEach(session::save);
			bugRecords.values().forEach(l -> l.forEach(session::save));
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public void save(Project project) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Session session = cm.openSession();
		try {
			Transaction tx = session.beginTransaction();
			this.save(project, session);
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public static List<ProjectEntity> loadProjects() {
		ConnectionManager cm = ConnectionManager.getInstance();
		Session session = cm.openSession();
		List<ProjectEntity> projects = session.createQuery("from ProjectEntity where process = true").list();
		session.close();
		return projects;
	}

	private void save(Project project, Session session) {
		//TODO
//		RunInfoEntity runInfoEntity = Converter.createRunInfo(this.projectEntity);
//		session.save(runInfoEntity);
//
//		VersionEntity versionEntity = Converter.createVersion(runInfoEntity, project.getVersion());
//		session.save(versionEntity);
//
//		Map<Module, ModuleEntity> moduleMap = Converter.convertModules(versionEntity, project.getModules());
//		moduleMap.values().forEach(session::save);
//
//		Map<model.Class, ClassEntity> classMap = Converter.convertClasses(moduleMap, project.getClasses());
//		classMap.values().forEach(session::save);
//
//		List<DependenciesEntity> dependencies = Converter.createDependencies(classMap);
//		dependencies.forEach(session::save);
//
//		Map<Method, MethodEntity> methodMap = Converter.convertMethods(classMap, project.getMethods());
//		methodMap.values().forEach(session::save);
	}
}
