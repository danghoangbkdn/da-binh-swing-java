package tcp_ip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import connection.ConfigurationProvider;
import connection.ConfigurationProviderImpl;

public class Server {

	private ServerSocket serverSocket;
	private List<Socket> listSockets;
	private int port;

	private ConfigurationProvider provider;
	private Properties pro;

	public Server() {
		provider = new ConfigurationProviderImpl();
		pro = provider.getProperties();
		port = Integer.parseInt(pro.getProperty("PORT"));
		listSockets = new ArrayList<>();

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for a client ...");
			while (true) {
				Socket socket = serverSocket.accept();
				listSockets.add(socket);
				new Thread(() -> {
					System.out.println("[server] client connecting ...!");
					new ServerHandling(socket);
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}