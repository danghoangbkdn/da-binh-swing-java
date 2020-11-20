package tcp_ip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import controller.ListCustommerController;
import controller.ListCustommerControllerImpl;
import entity.Custommer;
import entity.Request;

public class ServerHandling {

	private DataInputStream dis;
	private ObjectInputStream ois;
	private DataOutputStream dos;
	private ObjectOutputStream oos;

	private final ListCustommerController controller;

	public ServerHandling(Socket socket) {
		controller = new ListCustommerControllerImpl();

		try {
			dos = new DataOutputStream(socket.getOutputStream());
			oos = new ObjectOutputStream(dos);
			dis = new DataInputStream(socket.getInputStream());
			ois = new ObjectInputStream(dis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				Request request = (Request) ois.readObject();
				if (request.getRequest().equals("getObj")) {
					oos.writeObject(getCustommerObj());
				} else if (request.getRequest().equals("getList")) {
					oos.writeObject(getListCustommers());
				} else if (request.getRequest().equals("setStatus")) {
					oos.writeObject(setStatus(request.getCustommer()));
				} else if (request.getRequest().equals("getCustommer")) {
					oos.writeObject(getCustommer(request.getCustommer().getId()));
				}
			} catch (ClassNotFoundException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			} catch (IOException e) {
				try {
					System.out.println("[server] the client has exited  ... !");
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	}

	private Object[][] getCustommerObj() {
		return controller.getCustommerObj();
	}

	private List<Custommer> getListCustommers() {
		return controller.getListCustommers();
	}

	private Custommer setStatus(Custommer custommer) {
		return controller.setStatus(custommer);
	}

	private Custommer getCustommer(String id) {
		return controller.getCustommer(id);
	}
}