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
}
