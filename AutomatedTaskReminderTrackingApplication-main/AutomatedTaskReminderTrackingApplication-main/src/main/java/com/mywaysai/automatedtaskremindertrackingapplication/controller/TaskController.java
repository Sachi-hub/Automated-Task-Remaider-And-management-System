package com.mywaysai.automatedtaskremindertrackingapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mywaysai.automatedtaskremindertrackingapplication.dto.CreateTaskRequest;
import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;
import com.mywaysai.automatedtaskremindertrackingapplication.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody CreateTaskRequest req) {
        Task t = new Task();
        t.setTitle(req.title);
        t.setDescription(req.description);
        t.setDueDate(req.dueDate);
        t.setReminderEmail(req.reminderEmail);
        if (req.reminderMinutesBefore != null) t.setReminderMinutesBefore(req.reminderMinutesBefore);
        Task saved = service.addTask(t);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        Task updated = service.updateTask(task);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Task> complete(@PathVariable Long id) {
        Task t = service.markCompleted(id);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

