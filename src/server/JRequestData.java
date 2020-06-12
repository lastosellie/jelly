package server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import vo.Todo;

public class JRequestData implements Serializable {

	private int command;
	private Todo todo;
	private List<Todo> todoList;
	private int result;

	private int projectId;
	private int todoId;

	public static final int GET_TODO = 0;
	public static final int ADD_TODO = 1;
	public static final int DEL_TODO = 2;
	public static final int DEL_TODO_All = 3;

	public JRequestData(int command) {
		this.command = command;
	}

	public JRequestData(int command, Todo todo) {
		this.command = command;
		this.todo = todo;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

}
