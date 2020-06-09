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
			System.out.println("접속을 준비하고 있습니다.");
			serverSocket = new ServerSocket(5555); 
		}catch (IOException e) {
			System.err.println("서비스 준비중에 오류가 발생했습니다.");
		}
		while(true) {
			try {
				socket = serverSocket.accept(); 
												
				System.out.println(socket.getInetAddress() + "가 접속하셨습니다.");
				
				ois = new ObjectInputStream (socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream()); 
				
				Thread t = new Thread(new ChatServerThread(buffer,ois,oos));
				t.start();
			}catch (IOException e) {
				System.err.println("IOException이 발생했습니다.");
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
