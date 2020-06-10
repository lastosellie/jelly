package server;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class JChatData implements Serializable {
	private String name;
	private String receiver;
	private String message;
	private String timestamp;
	private int state;

	private transient ObjectOutputStream oos;
	private Vector<String> users;

	public static final int FIRST_CONNECTION = 0;
	public static final int DISCONNECTION = -1;
	public static final int CHAT_MESSAGE = 1;
	public static final int CHAT_WHISPER = 2;

	public JChatData() {
	}

	public JChatData(String name, String message, int state, String timestamp, ObjectOutputStream o) {
		super();
		this.name = name;
		this.message = message;
		this.state = state;
		this.oos = o;
		this.timestamp = timestamp;

	}

	public JChatData(String name, String message, int state, String timestamp) {
		this(name, message, state, timestamp, null);
	}

	public JChatData(String name, String receiver, String message, int state, String timestamp) {
		this.name = name;
		this.receiver = receiver;
		this.message = message;
		this.state = state;
		this.timestamp = timestamp;

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

	public Vector<String> getUsers() {
		return users;
	}

	public void setUsers(Vector<String> users) {
		this.users = users;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
