package com.example.ToDoList.service;

import com.example.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import com.example.ToDoList.model.Task;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTask(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }
    public Task getById(Long id){
        return taskRepository.findById(id).orElse(new Task());
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
