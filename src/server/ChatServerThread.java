package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class ChatServerThread implements Runnable {

	Vector <Data> buffer;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Data d; 
	boolean exit;
	String name;
	
	
	public ChatServerThread(Vector<Data> v, ObjectInputStream ois, ObjectOutputStream oos) {
		this.buffer = v;
		this.ois = ois;
		this.oos = oos;
	}
	public void run() {
		while (!exit) {
			try {
				d = (Data)ois.readObject(); 
				int state = d.getState(); 
				switch(state) {
				case Data.DISCONNECTION:
					name = d.getName();
					for(int i = 0; i<buffer.size(); i++) {
						if(((Data)buffer.elementAt(i)).getName().equals(name));
							buffer.removeElementAt(i);
							break;
					}
				
					broadCasting();
				try {
					ois.close();
					oos.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				break;
				case Data.FIRST_CONNECTION:
					
					Vector<String> userName = new Vector<String>(5,1);
					d.setOos(oos);
					buffer.addElement(d); 
					for(int i = 0; i < buffer.size(); i++) {
						userName.addElement(((Data)buffer.elementAt(i)).getName());
					}
					d.setUserName(userName);
					broadCasting();
					break;
				case Data.CHAT_MESSAGE:
					broadCasting();
					break;
					
				case Data.CHAT_WHISPER:
					whisper();
					break;
					default:
			}
			}
		
		
		catch(Exception e) {
			System.err.println("IOException이 발생하였습니다.");
		}
	}
	}
	public void broadCasting() throws IOException {
		
		for(int i = 0; i < buffer.size(); i++) {
			((Data)buffer.elementAt(i)).getOos().writeObject(d);
		}
	}
	public void whisper() {
		String receiver = d.getReceiver();
		for (int i = 0; i < buffer.size(); i++) {
			Data data = (Data) buffer.elementAt(i);
			if (data.getName().equals(receiver)) {
				try {
					data.getOos().writeObject(d);
				}catch (IOException e) {
					System.err.println("broadCasting method에서 IOException이 발생하였습니다.");
				}
			}
		}
		
	}
}
