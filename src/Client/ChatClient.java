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

			Data d = new Data(userName, "님이 접속하였습니다 !!!!! ", Data.FIRST_CONNECTION);
			oos.writeObject(d);
			System.out.println("Server에 접속!!");
		} catch (IOException e) {
			// 이부분 수정 1번
			if(!socket.isClosed()) {
				disconnect();
				System.out.println("서버접속실패");
				Platform.exit();
			}
			e.printStackTrace();
		}

		listener = new Thread(this);
		listener.start();

	}// end start
	
	
	
	//클라이언트 프로그램 종료메소드
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
			System.err.println("대화중 IOException이 발생하였습니다 ");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
	
	

