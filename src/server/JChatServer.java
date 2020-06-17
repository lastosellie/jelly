package server;

import java.net.*;
import java.util.*;

import application.IniFile;
import biz.MemberBiZ;
import biz.TodoBiZ;

import java.io.*;

public class JChatServer {

	Vector<Object> buffer;

	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public void service() {
		try {
			System.out.println("접속을 준비하고 있습니다.");
			int port = 9999;
			IniFile ini = IniFile.getInstance();
			if (ini.isLoaded()) {
				port = Integer.parseInt(ini.getPort());
			}
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("서비스 준비중에 오류가 발생했습니다.");
		}
		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + "가 접속하셨습니다.");

				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());

				Thread t = new Thread(new JChatServerThread(buffer, ois, oos));
				t.start();
			} catch (IOException e) {
				System.err.println("IOException이 발생했습니다.");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Start Server Service....");
		JChatServer cs = new JChatServer();
		cs.buffer = new Vector<Object>(5, 1);
		cs.service();
	}

}
