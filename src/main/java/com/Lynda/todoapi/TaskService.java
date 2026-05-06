package com.Lynda.todoapi;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TaskService {
     private final TaskRepository taskRepository;
       public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task createTask(Task task){
        task.setDateCreation(LocalDateTime.now());
        return taskRepository.save(task);
    }
    public Task getTaskById(Long id ){
        return taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

    }
    public Task updateTask(Long id, Task taskDetails){
        Task task = getTaskById(id);
        task.setTitre(taskDetails.getTitre());
        task.setDescription(taskDetails.getDescription());
        task.setStatut(taskDetails.getStatut());
        return taskRepository.save(task);
    }
}
