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
	
	
	public void copyText(String message, int state) {
		try {
			oos.writeObject(new Data(userName, message, state));
			
		} catch (IOException e2) {
			System.err.println("��ȭ�� IOException�� �߻��Ͽ����ϴ� ");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
	
	

