package com.ToDoList.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoList.Entities.ToDoBean;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoBean, Long> {
    List<ToDoBean> findByStatus(String status);
    List<ToDoBean> findByDueDateBefore(LocalDate date);
}
