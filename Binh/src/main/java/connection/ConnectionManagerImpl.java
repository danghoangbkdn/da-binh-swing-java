package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManagerImpl implements ConnectionManager {

	private final ConfigurationProvider provider;

	public ConnectionManagerImpl() {
		provider = new ConfigurationProviderImpl();
	}

	@Override
	public Connection getConnection() {
		Properties pro = provider.getProperties();
		Connection con = null;

		try {
			Class.forName(pro.getProperty("driverClassName"));
			con = DriverManager.getConnection(pro.getProperty("URL"), pro.getProperty("USER"),
					pro.getProperty("PASSWORD"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}