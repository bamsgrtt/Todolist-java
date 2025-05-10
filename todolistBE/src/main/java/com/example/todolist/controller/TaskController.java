package com.example.todolist.controller;


import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Menandai ini sebagai controller REST
@RequestMapping("/api/tasks") // Endpoint
@CrossOrigin(origins = "*") // izinkan akses dari semua origin
public class TaskController {

    private final TaskService service;

    // Constructor injection: menyambung service
    public TaskController(TaskService service) {
        this.service = service;
    }

    // Endpoint GET untuk semua task
    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAll();
    }

    // Endpoint GET untuk task berdasarkan ID
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getById(id);
    }

    // Endpoint POST untuk membuat taks baru
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.create(task);
    }

    // Endpoint PUT untuk update task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return service.update(id, task);
    }

    // Endpoint DELETE untuk hapus task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.delete(id);
    }
}
