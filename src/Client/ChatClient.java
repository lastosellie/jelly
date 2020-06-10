package Client;


import org.omg.CORBA.portable.OutputStream;

import application.ChatController;
import javafx.application.Platform;
import server.Data;


import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;

import Client.Receive;

public class ChatClient implements Runnable {
	
	private static ChatClient instance = new ChatClient();
	public static ChatClient getInstance() {
		return instance;
		
	}
	
	private final String serverName = "127.0.0.1";
	private final int PORT = 5555;
	String userName;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	private Thread listener;
	private boolean flag;

	private Socket socket;
	private Data d;

	private LocalTime startTime;
	private LocalTime endTime;
	
	private ArrayList<Receive> objs = new ArrayList<>();
	

	public void ChatClient(String userName) {
		this.userName = userName;
		start();
	}
	
	public void start() {
		try {
			socket = new Socket(serverName, PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			Data d = new Data(userName, "���� �����Ͽ����ϴ� !!!!! ", Data.FIRST_CONNECTION);
			oos.writeObject(d);
			System.out.println("Server�� ����!!");
		} catch (IOException e) {
			// �̺κ� ���� 1��
			if(!socket.isClosed()) {
				disconnect();
				System.out.println("�������ӽ���");
				Platform.exit();
			}
			e.printStackTrace();
		}

		listener = new Thread(this);
		listener.start();

	}// end start
	
	public void Send(Receive obj, String message) {
		objs.add(obj);
		copyText(message, Data.CHAT_MESSAGE);
	}
	
	//Ŭ���̾�Ʈ ���α׷� ����޼ҵ�
	public void disconnect() {
		try {
			oos.close();
			ois.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		flag = true;
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
				for (Receive obj : objs) {
					obj.receive(d);
				}
				break;

			case Data.DISCONNECTION:
				for (Receive obj : objs) {
					obj.receive(d);
				}
				break;

			case Data.CHAT_MESSAGE:
				for (Receive obj : objs) {
					obj.receive(d);
				}
				break;
			case Data.CHAT_WHISPER:
				for (Receive obj : objs) {
					obj.receive(d);
				break;
				}
			default:
				System.out.println("error");

			}// switch
		}// while
		try {
			ois.close();
		} catch (IOException e) {
			System.err.println(" ChatClientThread���� ObjectOutputStream�� Close�ϴ� �߿� IOException�� �߻��Ͽ����ϴ�.");
		}// catch
	}// run
	
	public void copyText(String message, int state) {
		try {
			oos.writeObject(new Data(userName, message, state));
			
		} catch (IOException e2) {
			System.err.println("��ȭ�� IOException�� �߻��Ͽ����ϴ� ");
		}
	}
	
	
	

/*public void receive() {
	while(true) {
		try {
			ois = socket.getInputStream();
			byte[] buffer = new byte[512];
			int length = ois.read(buffer);
			if(length == -1) throw new IOException();
			String message = new String(buffer,0,length,"UTF-8");
			Platform.runLater(() -> {
				
			});
		}catch (Exception e) {
			disconnect();
		}
	}*/
	
	
	
}
	
	

