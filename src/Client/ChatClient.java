package Client;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import application.ChatController;
import server.Data;

public class ChatClient implements Runnable {
	
	private static ChatClient instance = new ChatClient();
	
	ObjectOutputStream oos;
	String userName;
	ObjectInputStream ois;
	String timestamp;
	
	
	private final String serverName = "127.0.0.1";
	private final int PORT = 5555; //������ ��Ʈ��ȣ
	
	
	private Thread listener;
	private boolean flag;

	private Socket socket; // Client�� ������ �ۼ��� ��Ȱ�� ��
	private Data d;
	

	public void ChatClient(String userName) {
		this.userName = userName;
		start();
	}
	
	public void start() {
		try {
			socket = new Socket(serverName, PORT); // ���ϻ���
			oos = new ObjectOutputStream(socket.getOutputStream()); //��½�Ʈ�� Client �κ��� ��ü�� �о���� ��Ȱ�� �ϴ� ��ü�� ����
			ois = new ObjectInputStream(socket.getInputStream()); //�Է½�Ʈ��

			Data d = new Data(userName, "���� �����Ͽ����ϴ�. ",
					Data.FIRST_CONNECTION , timestamp);
			oos.writeObject(d); 
			System.out.println("Server�� ����!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		listener = new Thread(this);
		listener.start();
}
	
	public void run() {
		while (!flag) {
			try {
				d = (Data) ois.readObject(); // �������� ���� ���� �ޱ�
			} catch (IOException e) {
				System.err.println("run method IOException");
				try {
					oos.close(); // ��´ݱ�
					ois.close(); //�Է´ݱ�
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				flag = true;
			} catch (ClassNotFoundException e1) {
				System.err.println("Data class NotFound");
			}
			int state = d.getState();
			String name = d.getName();
			System.out.println("name : " + name);

			switch (state) {
			case Data.FIRST_CONNECTION:
				
				java.util.Vector UserName = d.getUserName();
				break;

			case Data.DISCONNECTION:
				break;

			case Data.CHAT_MESSAGE:
				break;
			case Data.CHAT_WHISPER:
				break;

			default:
				System.out.println("error");

			}// switch
		}// while
		try {
			ois.close();
		} catch (IOException e) {
			System.err
					.println(" ChatClientThread���� ObjectOutputStream�� Close�ϴ� �߿� IOException�� �߻��Ͽ����ϴ�.");
		}// catch
	}// run
	
	public void copyText(String message, int state) {
		try {
			oos.writeObject(new Data(userName, message, state, message));
			
		} catch (IOException e2) {
			System.err.println("��ȭ�� IOException�� �߻��Ͽ����ϴ� ");
		}
	}
	

	
	}

