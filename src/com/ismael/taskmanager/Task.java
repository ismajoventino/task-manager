package com.ismael.taskmanager;

public class Task {
	
	private String description;
	private Priority priority;
	private Status status;
	
	public Task(String description, Priority priority, Status status) {
		this.description = description;
		this.priority = priority;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return description + "; " + priority + "; " + status + ";";
	}

}
