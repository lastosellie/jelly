package server;

import java.io.Serializable;
import java.sql.ResultSet;

import application.Todo;

public class JData implements Serializable {

	private int command;
	private int result = 0;
	private ResultSet resultSet;
	private Todo todo;
	
	public static final int GET_TODO = 0;
	public static final int ADD_TODO = 1;
	public static final int DEL_TODO = 2;

	public static final int GET_MEMBER = 10;
	public static final int ADD_MEMBER = 11;
	public static final int DEL_MEMBER = 12;

	public JData(int command) {
		this.command = command;
	}
	
	public JData(int command, Todo todo) {
		this.command = command;
		this.todo = todo;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

}
