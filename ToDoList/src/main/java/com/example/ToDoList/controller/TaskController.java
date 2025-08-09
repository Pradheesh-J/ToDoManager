package com.example.ToDoList.controller;

import com.example.ToDoList.model.Task;
import com.example.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public String getTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTasks(@PathVariable("id") Long id, Model model){
        model.addAttribute("tasks", taskService.getById(id));
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title, Model model){
        taskService.createTask(title);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggleById(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}