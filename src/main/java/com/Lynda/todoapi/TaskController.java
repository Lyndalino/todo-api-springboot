package com.Lynda.todoapi;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {
    
     private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
        
    }
     @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
