package com.ToDoList.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ToDoList.Entities.ToDoBean;

@Repository
public interface ToDoRepository extends CrudRepository<ToDoBean, String> {

}
