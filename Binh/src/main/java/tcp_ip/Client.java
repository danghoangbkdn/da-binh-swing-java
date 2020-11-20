package tcp_ip;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import connection.ConfigurationProvider;
import connection.ConfigurationProviderImpl;
import view.UIStart;

public class Client {

	private Socket socket;
	private String ip;
	private int port;

	private ConfigurationProvider provider;
	private Properties pro;

	public Client() {
		provider = new ConfigurationProviderImpl();
		pro = provider.getProperties();
		ip = "192.168.56.1";
		port = Integer.parseInt(pro.getProperty("PORT"));

		try {
			socket = new Socket(ip, port);

			new UIStart("Kết nối thành công!", socket).setVisible(true);
		} catch (IOException e) {
			new UIStart("Kết nối thất bại!", socket).setVisible(true);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}