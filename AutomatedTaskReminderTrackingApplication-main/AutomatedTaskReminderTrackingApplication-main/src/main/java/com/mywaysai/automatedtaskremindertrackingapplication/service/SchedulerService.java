package com.mywaysai.automatedtaskremindertrackingapplication.service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;
import com.mywaysai.automatedtaskremindertrackingapplication.repository.TaskRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SchedulerService {

    private final ThreadPoolTaskScheduler scheduler;
    private final EmailService emailService;
    private final TaskRepository taskRepository;

    // map taskId -> ScheduledFuture so we can cancel
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public SchedulerService(ThreadPoolTaskScheduler scheduler, EmailService emailService, TaskRepository taskRepository){
        this.scheduler = scheduler;
        this.emailService = emailService;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        // on startup schedule reminders for pending tasks in DB
        taskRepository.findAll().stream()
            .filter(t -> !t.isCompleted() && t.getDueDate() != null)
            .forEach(this::scheduleReminderForTask);
    }

    public void scheduleReminderForTask(Task task) {
        // cancel existing
        cancelReminderForTask(task.getId());

        if (task.isCompleted() || task.getDueDate() == null) return;

        LocalDateTime reminderTime = task.getDueDate().minusMinutes(task.getReminderMinutesBefore());
        long delayMillis = Duration.between(LocalDateTime.now(), reminderTime).toMillis();

        if (delayMillis <= 0) {
            // if the reminder time already passed, send immediately
            scheduler.submit(() -> sendReminder(task));
            return;
        }

        ScheduledFuture<?> future = scheduler.schedule(() -> sendReminder(task), java.util.Date.from(reminderTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
        scheduledTasks.put(task.getId(), future);
    }

    public void cancelReminderForTask(Long taskId) {
        ScheduledFuture<?> f = scheduledTasks.remove(taskId);
        if (f != null) f.cancel(false);
    }

    private void sendReminder(Task task) {
        // final check: if completed, do not send
        Task fresh = taskRepository.findById(task.getId()).orElse(null);
        if (fresh == null || fresh.isCompleted()) return;
        String to = fresh.getReminderEmail();
        String subject = "Reminder: " + fresh.getTitle();
        String body = "Task due at: " + fresh.getDueDate() + "\n\n" + (fresh.getDescription() == null ? "" : fresh.getDescription());
        if (to != null && !to.isBlank()) {
            emailService.sendSimpleMail(to, subject, body);
        } else {
            // Optionally log or send to admin if no email provided
            System.out.println("No reminder email for task: " + fresh.getId());
        }
    }
    public void sendInstantEmail(String to, String subject, String body) {
        emailService.sendSimpleMail(to, subject, body);
    }

}
