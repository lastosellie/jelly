package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class JChatServerThread implements Runnable {

	Vector<Object> buffer;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	boolean exit;
	String name;

	public JChatServerThread(Vector<Object> buffer, ObjectInputStream ois, ObjectOutputStream oos) {
		this.buffer = buffer;
		this.ois = ois;
		this.oos = oos;
	}

	public void run() {
		while (!exit) {
			try {
				Object obj = ois.readObject();
				if (obj instanceof JChatData) {
					System.out.println("Server : JChatData 수신");
					JChatData jd = (JChatData) obj;
					int state = jd.getState();
					switch (state) {
					case Data.FIRST_CONNECTION:
						jd.setOos(oos);
						Vector<String> users = new Vector<String>(5, 1);
						buffer.addElement(jd);
						for (int i = 0; i < buffer.size(); i++) {
							if (buffer.elementAt(i) instanceof JChatData) {
								users.addElement(((JChatData) buffer.elementAt(i)).getName());
							}
						}
						jd.setUsers(users);
						broadCasting(jd);
						break;
					case Data.DISCONNECTION:
						name = jd.getName();
						for (int i = 0; i < buffer.size(); i++) {
							if (buffer.elementAt(i) instanceof JChatData) {
								if (((JChatData) buffer.elementAt(i)).getName().equals(name))
									buffer.removeElementAt(i);
							}
						}
						broadCasting(jd);
						try {
							ois.close();
							oos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case Data.CHAT_MESSAGE:
						broadCasting(jd);
						break;
					default:
					}
				}
			} catch (Exception e) {
				System.err.println("IOException이 발생하였습니다.");
			}
			System.out.println("버퍼사이즈" + buffer.size());
		}
	}

	public void broadCasting(JChatData jd) throws IOException {
		for (int i = 0; i < buffer.size(); i++) {
			if (buffer.elementAt(i) instanceof JChatData) {
				((JChatData) buffer.elementAt(i)).getOos().writeObject(jd);
			}
		}
	}

//	public void whisper() {
//		String receiver = d.getReceiver();
//		for (int i = 0; i < buffer.size(); i++) {
//			Data data = (Data) buffer.elementAt(i);
//			if (data.getName().equals(receiver)) {
//				try {
//					data.getOos().writeObject(d);
//				} catch (IOException e) {
//					System.err.println("broadCasting method에서 IOException이 발생하였습니다.");
//				}
//			}
//		}
//	}
}
