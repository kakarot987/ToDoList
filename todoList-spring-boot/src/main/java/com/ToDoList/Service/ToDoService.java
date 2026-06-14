package com.ToDoList.Service;

import com.ToDoList.Entities.ToDoBean;
import com.ToDoList.Repository.ToDoRepository;
import com.ToDoList.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {  // Constructor injection (not @Autowired)
        this.toDoRepository = toDoRepository;
    }

    public ToDoBean addTask(ToDoRequest request) {
        ToDoBean task = new ToDoBean();
        task.setTaskName(request.taskName());
        task.setLabel(request.label());
        task.setStatus(request.status());
        task.setDueDate(request.dueDate());
        return toDoRepository.save(task);
    }

    public List<ToDoBean> getAllTasks() {
        return (List<ToDoBean>) toDoRepository.findAll();
    }

    public ToDoBean updateTask(Long id, ToDoRequest request) {
        ToDoBean task = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        task.setTaskName(request.taskName());
        task.setLabel(request.label());
        task.setStatus(request.status());
        task.setDueDate(request.dueDate());
        return toDoRepository.save(task);
    }

    public void deleteTask(Long id) {
        toDoRepository.deleteById(id);
    }
}
