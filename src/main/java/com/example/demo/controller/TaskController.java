package com.example.demo.controller;


import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.findAll();
    }

    @GetMapping("/done")
    public List<Task> getDone(){
        return taskService.getDone();
    }

    @GetMapping("notDone")
    public List<Task> notDoneTasks(){
        return taskService.notDoneTasks();
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") Long id,@Valid @RequestBody Task newTask){
        return taskService.update(id, newTask);
    }
}
