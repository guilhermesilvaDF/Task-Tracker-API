package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public Page<Task> findAll(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada com o ID " + id));
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public void delete(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Tarefa nao encontrada com o ID " + id);
    }

    public Task update(Long id, Task newTask){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada com o ID " + id));

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
