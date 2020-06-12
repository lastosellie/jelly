package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;

import application.ClientInfo;
import application.IClient;
import server.JChatData;

public class JChatClient implements Runnable {

	private static JChatClient instance = new JChatClient();

	private Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private Thread listener;
	private boolean stop = false;

	private String serverName;
	private int port = 5555;
	private HashSet<IClient> subscribers;

	public JChatClient() {
		serverName = "127.0.0.1";
		port = 5555;
		subscribers = new HashSet<>();
		
		start();
	}

	public static JChatClient getInstance() {
		return instance;
	}

	public void start() {
		try {
			socket = new Socket(serverName, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			JChatData d = new JChatData(ClientInfo.UserName, "���� �����Ͽ����ϴ�. ", JChatData.FIRST_CONNECTION, "");
			oos.writeObject(d);
			System.out.println("Client : Server ����");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		listener = new Thread(this);
		listener.start();
	}

	public void run() {
		while (!stop) {
			try {
				Object obj = ois.readObject(); // �������� ���� ���� �ޱ�
				for (IClient subscriber : subscribers) {
					subscriber.receive(obj);
				}
			} catch (IOException e) {
				System.err.println("run method IOException");
				try {
					oos.close();
					ois.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				stop = true;
			} catch (ClassNotFoundException e) {
				System.err.println("Data class NotFound");
			}
		}
		try {
			ois.close();
		} catch (IOException e) {
			System.err.println(" ChatClientThread���� ObjectOutputStream�� Close�ϴ� �߿� IOException�� �߻��Ͽ����ϴ�.");
		}
	}

	public void sendToServer(Object data) {
		try {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendToServer(IClient sender, Object data) {
		try {
			subscribers.add(sender);
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
