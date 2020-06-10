package server;


import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import Client.Receive;

public class Data implements Serializable {
	private String name; 
	private String receiver; 
	private String message; 
	private int state; 
	
	
	
	private transient ObjectOutputStream oos; 
	private Vector<String> userName;
	
	
	
	public static final int FIRST_CONNECTION = 0;
	public static final int DISCONNECTION = -1;
	public static final int CHAT_MESSAGE = 1; 
	public static final int CHAT_WHISPER = 2;
	
	public Data() {}
	
	
	public Data(String name, String message, int state, ObjectOutputStream o) {
		super();
		this.name = name;
		this.message = message;
		this.state = state;
		this.oos = o;
		
		
	}
	
	public Data(String name, String message, int state) {
		this(name, message, state, null);
	}
	public Data (String name, String receiver, String message, int state, String timestamp) {
		this.name = name;
		this.receiver = receiver;
		this.message = message;
		this.state = state;
		
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessange(String message) {
		this.message = message;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ObjectOutputStream getOos() {
		return oos;
	}
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	public Vector getUserName() {
		return userName;
	}
	public void setUserName(Vector userName) {
		this.userName = userName;
	}


}
