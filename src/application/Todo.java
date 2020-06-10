package application;

import java.time.LocalDate;

public class Todo {

	private int projectId;
	private String assignee; // ´ã´çÀÚ
	private int id;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int parentId;
	private String content;
	private Double progress;

	public Todo() {
	}

	public Todo(int projectId, String title, LocalDate startDate, LocalDate endDate, String content,
			Double progress) {
		this.projectId = projectId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.progress = progress;
		this.assignee = ClientInfo.UserName;
	}
	
	public Todo(int projectId, int id, String title, LocalDate startDate, LocalDate endDate, String content,
			Double progress) {
		this.projectId = projectId;
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.progress = progress;

		this.assignee = ClientInfo.UserName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

}
