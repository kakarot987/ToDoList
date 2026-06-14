package com.ToDoList.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Setter
@Getter
@Entity
public class ToDoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String taskName;
	
	@Column
	private String label;
	
	@Column
	private String status;
	
	@Column
	private LocalDate dueDate;
	
}
