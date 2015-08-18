package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Nik on 28-07-2015
 */
//TODO: refactor this?
public class ConnectionManager {

	private static ConnectionManager instance;

	private SessionFactory sessionFactory;

	private ConnectionManager() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static ConnectionManager getInstance() {
		if (ConnectionManager.instance == null) {
			ConnectionManager.instance = new ConnectionManager();
		}
		return ConnectionManager.instance;
	}

	public Session openSession() {
		return this.sessionFactory.openSession();
	}

}
