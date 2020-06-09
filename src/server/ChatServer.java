package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer {
	
	Vector <Data> buffer; 
						

	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public void service() {
		try {
			System.out.println("������ �غ��ϰ� �ֽ��ϴ�.");
			serverSocket = new ServerSocket(5555); 
		}catch (IOException e) {
			System.err.println("���� �غ��߿� ������ �߻��߽��ϴ�.");
		}
		while(true) {
			try {
				socket = serverSocket.accept(); 
												
				System.out.println(socket.getInetAddress() + "�� �����ϼ̽��ϴ�.");
				
				ois = new ObjectInputStream (socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream()); 
				
				Thread t = new Thread(new ChatServerThread(buffer,ois,oos));
				t.start();
			}catch (IOException e) {
				System.err.println("IOException�� �߻��߽��ϴ�.");
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
			System.out.println("Start Server Service....");
			ChatServer cs = new ChatServer();
			cs.buffer = new Vector<Data>(5,1);
			cs.service();

	}

}
