package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Nik on 28-07-2015
 */
public class ConnectionManager {

	private static ConnectionManager instance;

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;

	private ConnectionManager() {
		Configuration configuration = new Configuration();
		configuration.configure();
		this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		this.sessionFactory = configuration.buildSessionFactory(this.serviceRegistry);
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

	public StatelessSession openStatelessSession() {
		return this.sessionFactory.openStatelessSession();
	}


//	private final String URL = "jdbc:postgresql://localhost/thesis";
//	private final String USER = "postgres";
//	private final String PASSWORD = "root";
//
//	private Connection con;
//
//	public DatabaseHelper() throws SQLException {
//		this.con = DriverManager.getConnection(URL, USER, PASSWORD);
//	}
//
//	public void saveResults(String projectName, List<Class> classes) throws SQLException {
//		Statement st = con.createStatement();
//
//
//
//		Map<String, Module> modules = new HashMap<>();
//		for (Class c : classes) {
//			modules.put(c.getModule().getFilePath(), c.getModule());
//		}
//		PreparedStatement moduleStmt = this.getModuleInsertStmt(modules.values().stream().collect(Collectors.toList()));
//		ResultSet rs = moduleStmt.executeQuery();
//
//
//
//
////		ResultSet rs = st.executeQuery(this.getClassInsertStmt(classes));
//	}
//
//	private PreparedStatement getModuleInsertStmt(List<Module> modules) throws SQLException {
//		String moduleStm = "INSERT INTO module(run_info, path) VALUES "
//				+ StringHelper.implode(StringHelper.replicate("(?, ?)", modules.size()), ",");
//		PreparedStatement prepStmt = this.con.prepareStatement(moduleStm);
//		for (int i = 0; i < modules.size(); i++) {
//			this.fillModuleValues(i*2, modules.get(i), prepStmt);
//		}
//		return prepStmt;
//	}
//
//	private void fillModuleValues(int i, Module m, PreparedStatement pst) throws SQLException {
//		pst.setInt(i + 1, run_info);
//		pst.setString(i + 2, m.getFilePath());
//	}
//
////	private PreparedStatement getClassInsertStmt(List<Class> classes) throws SQLException {
////		String classStm = "INSERT INTO class(module_id, name, loc, lcom, nr_parents, nr_private_vars, nr_public_vars, nr_used_globals) VALUES "
////				+ StringHelper.implode(StringHelper.replicate("(?, ?, ?, ?, ?, ?, ?, ?)", classes.size()), ",");
////		PreparedStatement prepStmt = this.con.prepareStatement(classStm);
////		for (int i = 0; i < classes.size(); i++) {
////			this.fillClassValues(i * 9, classes.get(i), prepStmt);
////		}
////		return prepStmt;
////	}
////
////	private void fillClassValues(int i, Class c, PreparedStatement pst) throws SQLException {
////		pst.setInt(i + 1, module_id);
////		pst.setString(i + 2, c.getName());
////		pst.setString(i + 3, c.getName());
////		pst.setInt(i + 4, c.getLoc());
////		pst.setInt(i + 5, c.getLcom());
////		pst.setInt(i + 6, c.parentsCount());
////		pst.setInt(i + 7, c.privateVariablesCount());
////		pst.setInt(i + 8, c.publicVariablesCount());
////		pst.setInt(i + 9, c.usedGlobalsCount());
////	}
//
////	try {
////		con = DriverManager.getConnection(url, user, password);
////
////		if (rs.next()) {
////			System.out.println(rs.getString(1));
////		}
////
////	}
////	catch (SQLException ex) {
////		Logger lgr = Logger.getLogger(DbMain.class.getName());
////		lgr.log(Level.SEVERE, ex.getMessage(), ex);
////
////	}
////	finally {
////		try {
////			if (rs != null) {
////				rs.close();
////			}
////			if (st != null) {
////				st.close();
////			}
////			if (con != null) {
////				con.close();
////			}
////
////		}
////		catch (SQLException ex) {
////			Logger lgr = Logger.getLogger(DbMain.class.getName());
////			lgr.log(Level.WARNING, ex.getMessage(), ex);
////		}
////	}
}
