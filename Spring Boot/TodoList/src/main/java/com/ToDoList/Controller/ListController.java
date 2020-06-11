package com.ToDoList.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ToDoList.Entities.ToDoBean;
import com.ToDoList.Entities.ToDoModel;
import com.ToDoList.Repository.ToDoRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge=36000)
@Controller
@RequestMapping(path="/todo")
public class ListController {

	@Autowired
	private ToDoRepository toDoRepository;
	
	@PostMapping(path="/addTask")
	public @ResponseBody ToDoBean addTask(@RequestBody ToDoModel toDoModel) {
		ToDoBean toDoBean = new ToDoBean();
		toDoBean.setId(toDoModel.id);
		toDoBean.setTaskName(toDoModel.taskName);
		toDoBean.setLabel(toDoModel.label);
		toDoBean.setStatus(toDoModel.status);
		toDoBean.setDueDate(toDoModel.dueDate);
		toDoRepository.save(toDoBean);
		return new ToDoBean();
	}
	
	@GetMapping(path="/taskInfo")
	public @ResponseBody Iterable<ToDoBean> getTask() {
		return toDoRepository.findAll();
	}
}
