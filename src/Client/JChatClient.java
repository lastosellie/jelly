package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;

import application.IClient;
import application.IniFile;

public class JChatClient implements Runnable {

	private static JChatClient instance = new JChatClient();

	private Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private Thread listener;
	private boolean stop = false;
	private boolean isLoaded = false;
	private String serverName;
	private int port = 5555;
	private HashSet<IClient> subscribers;

	public JChatClient() {
		
		IniFile ini = IniFile.getInstance();
		if (ini.isLoaded()) {
			serverName = ini.getIp();
			port = Integer.parseInt(ini.getPort());
		}

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
			System.out.println("Client : Server 접속");

		} catch (IOException e) {
			e.printStackTrace();
			isLoaded = false;
			return;
		}

		isLoaded = true;
		listener = new Thread(this);
		listener.start();
	}

	public void run() {
		while (!stop) {
			try {
				Object obj = ois.readObject(); // 서버에서 보낸 내용 받기
				System.out.println("subscribers수 : " + subscribers.size());
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
			System.err.println(" ChatClientThread에의 ObjectOutputStream을 Close하는 중에 IOException이 발생하였습니다.");
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

	public HashSet<IClient> getSubscribers() {
		return subscribers;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

}
