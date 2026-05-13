package com.Lynda.todoapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_shouldAddDateCreation() {
        // ARRANGE — prépare les données
        Task task = new Task();
        task.setTitre("Ma tâche test");
        task.setDescription("Description test");
        task.setStatut("TODO");

        when(taskRepository.save(task)).thenReturn(task);

        // ACT — exécute la méthode
        Task result = taskService.createTask(task);

        // ASSERT — vérifie le résultat
        assertNotNull(result.getDateCreation());
        assertEquals("Ma tâche test", result.getTitre());
    }
    @Test
void getTaskById_shouldReturnTask_whenExists() {
    // ARRANGE
    Task task = new Task();
    task.setTitre("Tâche existante");
    when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));

    // ACT
    Task result = taskService.getTaskById(1L);

    // ASSERT
    assertEquals("Tâche existante", result.getTitre());
}

@Test
void getTaskById_shouldThrowException_whenNotExists() {
    // ARRANGE
    when(taskRepository.findById(99L)).thenReturn(java.util.Optional.empty());

    // ASSERT
    assertThrows(RuntimeException.class, () -> {
        taskService.getTaskById(99L);
    });
}
@Test
void deleteTask_shouldCallDeleteById() {
    // ARRANGE
    Long id = 1L;
    doNothing().when(taskRepository).deleteById(id);

    // ACT
    taskService.deleteTask(id);

    // ASSERT
    verify(taskRepository, times(1)).deleteById(id);
}
@Test
void updateTask_shouldUpdateFields() {
    // ARRANGE
    Task existingTask = new Task();
    existingTask.setTitre("Ancien titre");
    existingTask.setDescription("Ancienne description");
    existingTask.setStatut("TODO");

    Task newDetails = new Task();
    newDetails.setTitre("Nouveau titre");
    newDetails.setDescription("Nouvelle description");
    newDetails.setStatut("DONE");

    when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(existingTask));
    when(taskRepository.save(existingTask)).thenReturn(existingTask);

    // ACT
    Task result = taskService.updateTask(1L, newDetails);

    // ASSERT
    assertEquals("Nouveau titre", result.getTitre());
    assertEquals("Nouvelle description", result.getDescription());
    assertEquals("DONE", result.getStatut());
}
}

