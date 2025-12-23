package com.mywaysai.automatedtaskremindertrackingapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;
import com.mywaysai.automatedtaskremindertrackingapplication.repository.TaskRepository;
import com.mywaysai.automatedtaskremindertrackingapplication.service.SchedulerService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final SchedulerService schedulerService;
    private final TaskRepository taskRepository;

    public ScheduleController(SchedulerService schedulerService, TaskRepository taskRepository){
        this.schedulerService = schedulerService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/set/{taskId}")
    public ResponseEntity<String> setSchedule(@PathVariable Long taskId){
        Task t = taskRepository.findById(taskId).orElse(null);
        if (t == null) return ResponseEntity.notFound().build();
        schedulerService.scheduleReminderForTask(t);
        return ResponseEntity.ok("Scheduled (or rescheduled) reminder for task " + taskId);
    }

    @GetMapping("/reminders/{taskId}")
    public ResponseEntity<String> getReminderStatus(@PathVariable Long taskId) {
        // For simplicity, we return whether task is scheduled via DB checks or scheduled map
        Task t = taskRepository.findById(taskId).orElse(null);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Task due: " + t.getDueDate() + ", completed: " + t.isCompleted());
    }
}
