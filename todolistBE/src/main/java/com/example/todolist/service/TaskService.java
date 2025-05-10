package com.example.todolist.service;

import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    // Constructor Injection
    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    //Mengambil semua task
    public List<Task> getAll() {
        return repo.findAll();
    }

    // Ambil berdasarkan ID
    public Task getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Buat task baru
    public Task create(Task task) {
        return repo.save(task);
    }

    // Update task berdasarkan ID
    public Task update(Long id, Task newTask) {
        Task task = repo.findById(id).orElse(null);
        if (task != null) {
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setCompleted(newTask.isCompleted());
            return repo.save(task);
        }
        return null; // jika tidak ditemukan
    }

    // Hapus task berdasarkan ID
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
