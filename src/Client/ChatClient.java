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
	private final int PORT = 5555; //서버의 포트번호
	
	
	private Thread listener;
	private boolean flag;

	private Socket socket; // Client와 데이터 송수신 역활을 함
	private Data d;
	

	public void ChatClient(String userName) {
		this.userName = userName;
		start();
	}
	
	public void start() {
		try {
			socket = new Socket(serverName, PORT); // 소켓생성
			oos = new ObjectOutputStream(socket.getOutputStream()); //출력스트림 Client 로부터 객체를 읽어오는 역활을 하는 객체를 생성
			ois = new ObjectInputStream(socket.getInputStream()); //입력스트림

			Data d = new Data(userName, "님이 접속하였습니다. ",
					Data.FIRST_CONNECTION , timestamp);
			oos.writeObject(d); 
			System.out.println("Server에 접속!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		listener = new Thread(this);
		listener.start();
}
	
	public void run() {
		while (!flag) {
			try {
				d = (Data) ois.readObject(); // 서버에서 보낸 내용 받기
			} catch (IOException e) {
				System.err.println("run method IOException");
				try {
					oos.close(); // 출력닫기
					ois.close(); //입력닫기
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
					.println(" ChatClientThread에의 ObjectOutputStream을 Close하는 중에 IOException이 발생하였습니다.");
		}// catch
	}// run
	
	public void copyText(String message, int state) {
		try {
			oos.writeObject(new Data(userName, message, state, message));
			
		} catch (IOException e2) {
			System.err.println("대화중 IOException이 발생하였습니다 ");
		}
	}
	

	
	}

