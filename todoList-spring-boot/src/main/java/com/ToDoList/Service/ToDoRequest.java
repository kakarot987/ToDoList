package com.ToDoList.Service;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ToDoRequest(
        @NotBlank(message = "Task name is required")
        @Size(max = 100)
        String taskName,

        String label,

        @NotBlank
        String status,

        @Future(message = "Due date must be in the future")
        LocalDate dueDate
) {}