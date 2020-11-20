package tcp_ip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import entity.Custommer;
import entity.Request;

public class ClientHandling {

	private Socket socket;
	private DataInputStream dis;
	private ObjectInputStream ois;
	private DataOutputStream dos;
	private ObjectOutputStream oos;

	public ClientHandling(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			oos = new ObjectOutputStream(dos);
			dis = new DataInputStream(socket.getInputStream());
			ois = new ObjectInputStream(dis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object[][] getCustommerObj(Request request) {
		Object[][] custommers = null;
		try {
			oos.writeObject(request);
			custommers = (Object[][]) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return custommers;
	}

	@SuppressWarnings("unchecked")
	public List<Custommer> getListCustommers(Request request) {
		List<Custommer> custommers = null;
		try {
			oos.writeObject(request);
			custommers = (List<Custommer>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return custommers;
	}

	public Custommer setStatus(Request request) {
		try {
			oos.writeObject(request);
			Custommer custommers = (Custommer) ois.readObject();
			return custommers;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Custommer getCustommer(Request request) {
		try {
			oos.writeObject(request);
			Custommer custommers = (Custommer) ois.readObject();
			return custommers;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}