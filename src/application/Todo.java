package application;

import java.time.LocalDate;

public class Todo {
	
	private int teamId;
	private String assignee;
	private int id;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int parentId;
	private String content;

	
	public Todo() {
		super();
	}

	public Todo(String assignee, String title, LocalDate startDate, LocalDate endDate, String content) {
		super();
		this.assignee = assignee;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
	}
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
