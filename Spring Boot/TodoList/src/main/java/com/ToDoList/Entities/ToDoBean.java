package com.ToDoList.Entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDoBean {

	@Id
	private int id;
	
	@Column
	private String taskName;
	
	@Column
	private String label;
	
	@Column
	private String status;
	
	@Column
	private Date dueDate;

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

	public ToDoBean(int id, String taskName, String label, String status, Date dueDate) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.label = label;
		this.status = status;
		this.dueDate = dueDate;
	}

	public ToDoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
