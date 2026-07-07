package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public void delete(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        throw new RuntimeException("Tarefa nao encontrada com o ID " + id);
    }

    public Task update(Long id, Task newTask){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa nao encontrada com o ID " + id));

        task.setDescription(newTask.getDescription());
        task.setTitle(newTask.getTitle());
        task.setCompleted(newTask.isCompleted());

        return taskRepository.save(task);
    }

    public List<Task> getDone(){
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> notDoneTasks(){
        return taskRepository.findByCompletedFalse();
    }
}
