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
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }
    @GetMapping("/{id}")
    public Task getTaskByIdTask(@PathVariable Long id ){
        return taskService.getTaskById(id);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        return taskService.updateTask(id, taskDetails);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
