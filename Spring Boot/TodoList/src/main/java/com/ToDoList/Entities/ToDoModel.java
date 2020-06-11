package com.ToDoList.Entities;

import java.sql.Date;

public class ToDoModel {
	
	public int id;
	public String taskName;
	public String label;
	public String status;
	public Date dueDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public ToDoModel(int id, String taskName, String label, String status, Date dueDate) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.label = label;
		this.status = status;
		this.dueDate = dueDate;
	}
	public ToDoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
