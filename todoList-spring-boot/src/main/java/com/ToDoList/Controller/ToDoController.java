package com.ToDoList.Controller;

import com.ToDoList.Service.ToDoRequest;
import com.ToDoList.Service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ToDoList.Entities.ToDoBean;

import java.util.List;

@RestController  // Combines @Controller + @ResponseBody
@RequestMapping(path = "/api/todos")  // Versioned, RESTful path
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {

	private final ToDoService toDoService;

	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	@PostMapping
	public ResponseEntity<ToDoBean> addTask(@RequestBody @Valid ToDoRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.addTask(request));
	}

	@GetMapping
	public ResponseEntity<List<ToDoBean>> getAllTasks() {
		return ResponseEntity.ok(toDoService.getAllTasks());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ToDoBean> updateTask(@PathVariable Long id, @RequestBody @Valid ToDoRequest request) {
		return ResponseEntity.ok(toDoService.updateTask(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		toDoService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}