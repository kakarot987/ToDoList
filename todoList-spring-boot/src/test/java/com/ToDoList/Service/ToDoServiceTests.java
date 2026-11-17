package com.ToDoList.Service;

import com.ToDoList.Entities.ToDoBean;
import com.ToDoList.Repository.ToDoRepository;
import com.ToDoList.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ToDoServiceTests {
    private final ToDoRepository repository = mock(ToDoRepository.class);
    private final ToDoService service = new ToDoService(repository);

    @Test
    void createsTaskAndReturnsSavedEntity() {
        ToDoRequest request = new ToDoRequest("Prepare demo", "work", "OPEN", LocalDate.now().minusDays(1));
        when(repository.save(any(ToDoBean.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ToDoBean saved = service.addTask(request);
        assertEquals("Prepare demo", saved.getTaskName());
        assertEquals(request.dueDate(), saved.getDueDate());
        verify(repository).save(any(ToDoBean.class));
    }

    @Test
    void updatesOverdueTask() {
        ToDoBean existing = new ToDoBean();
        existing.setTaskName("Old");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(ToDoBean.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ToDoBean updated = service.updateTask(1L, new ToDoRequest("Done", "work", "DONE", LocalDate.now().minusDays(2)));
        assertEquals("DONE", updated.getStatus());
    }

    @Test
    void missingTaskCannotBeDeleted() {
        when(repository.existsById(404L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteTask(404L));
        verify(repository, never()).deleteById(any());
    }
}
