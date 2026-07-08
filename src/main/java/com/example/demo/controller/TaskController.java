package com.example.demo.controller;


import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Page<Task> getAllTasks(Pageable pageable){
        return taskService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") Long id){
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
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
    public ResponseEntity<Task> update(@PathVariable("id") Long id,@Valid @RequestBody Task newTask){
        Task task = taskService.update(id, newTask);
        return ResponseEntity.ok(task);
    }
}
